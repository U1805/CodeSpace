package code.space.codespace.service.impl;

import code.space.codespace.mapper.AlgoMapper;
import code.space.codespace.mapper.UserMapper;
import code.space.codespace.pojo.Algorithm;
import code.space.codespace.pojo.UploadInfo;
import code.space.codespace.service.AlgoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AlgoSeverImpl implements AlgoServer{
    @Autowired
    private AlgoMapper algoMapper;
    @Autowired
    private UserMapper userMapper;

    private Map<String, Double> weight = Map.of(
            "c", 1.0,
            "cpp", 1.1,
            "cs", 1.2,
            "java", 1.2,
            "js", 1.5,
            "py", 1.8,
            "go", 1.6
    );

    @Override
    public List<Algorithm> info(String keyword, String author) {
        List<Algorithm> algorithms = algoMapper.searchByKeyword(keyword, author);
        return algorithms;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @Override
    public Integer upload(UploadInfo uploadInfo) {
        if (isEmpty(uploadInfo.getTitle())
                || isEmpty(uploadInfo.getContent())
                || isEmpty(uploadInfo.getAuthor())
                || uploadInfo.getLine() == null
                || uploadInfo.getLanguage() == null
                || uploadInfo.getDesc() == null
                || uploadInfo.getTags() == null) {
            return 0;
        } else {
            Integer authorId = algoMapper.findAuthor(uploadInfo.getAuthor());
            if (authorId == null) {
                return 0;
            }

            // Insert new algorithm record
            algoMapper.upload(uploadInfo.getTitle(), uploadInfo.getContent(), authorId,
                    uploadInfo.getDesc(), uploadInfo.getLine(), uploadInfo.getLanguage(), uploadInfo.getOrigin());

            // Fetch the last inserted algo_id
            Integer algoId = algoMapper.getLastInsertId();

            // Insert tags associated with the new algo_id
            String[] tags = uploadInfo.getTags();
            for (String tag : tags) {
                algoMapper.uploadTag(algoId, tag);
            }

            // Calculate and update user score
            int score = (int) (10 + uploadInfo.getLine() * weight.getOrDefault(uploadInfo.getLanguage(), 1.0));
            userMapper.updateScore(uploadInfo.getAuthor(), score);

            return score;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Algorithm[] algo = algoMapper.searchById(id);
        if (algo.length == 0) return false;
        int score = (int) (10 + algo[0].getLine() * weight.getOrDefault(algo[0].getLanguage(), 1.0));
        userMapper.updateScore(algo[0].getAuthor(), -score);
        algoMapper.delete(id);
        return true;
    }

    @Override
    public void edit(Algorithm algorithm) {
        Integer res = algoMapper.setInfo(algorithm);
        if (res > 0){
            Integer algoId = algorithm.getId();
            algoMapper.deleteTagById(algoId);
            List<String> tags = algorithm.getTags();
            for (String tag : tags) {
                algoMapper.uploadTag(algoId, tag);
            }
        }
    }
}
