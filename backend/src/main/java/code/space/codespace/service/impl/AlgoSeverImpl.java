package code.space.codespace.service.impl;

import code.space.codespace.mapper.AlgoMapper;
import code.space.codespace.mapper.UserMapper;
import code.space.codespace.pojo.Algorithm;
import code.space.codespace.pojo.UploadInfo;
import code.space.codespace.service.AlgoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgoSeverImpl implements AlgoServer{
    @Autowired
    private AlgoMapper algoMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Algorithm[] info(String keyword, String author) {
        Algorithm[] algorithms = {};
        if (keyword == "" && author != "") {
            algorithms = algoMapper.search_by_author(author);
            for (Algorithm algorithm : algorithms) {
                System.out.println(algorithm.toString());
                algorithm.setTags(algoMapper.search_tag(algorithm.getAlgoId()));
            }
        }
        else if(keyword != "" && author == ""){
            algorithms = algoMapper.search_by_tag(keyword);
            for (Algorithm algorithm : algorithms) {
                algorithm.setTags(algoMapper.search_tag(algorithm.getAlgoId()));
            }
        }
        else if(keyword != "" && author != ""){
            algorithms = algoMapper.search_by_author_tag(author, keyword);
            for (Algorithm algorithm : algorithms) {
                algorithm.setTags(algoMapper.search_tag(algorithm.getAlgoId()));
            }
        }
        return algorithms;
    }

    @Override
    public Integer upload(UploadInfo uploadInfo) {
        if (uploadInfo.getTitle()==null || uploadInfo.getTitle()=="") {
            return 0;
        }
        else if (uploadInfo.getContent()==null || uploadInfo.getContent()=="") {
            return 0;
        }
        else if (uploadInfo.getAuthor()==null || uploadInfo.getAuthor()=="") {
            return 0;
        }
        else if (uploadInfo.getLine()==null) {
            return 0;
        }
        else if (uploadInfo.getLanguage()==null) {
            return 0;
        }
        else if (uploadInfo.getDesc()==null) {
            return 0;
        }
        else if (uploadInfo.getTags()==null) {
            return 0;
        }
        else {
            Integer author_id=algoMapper.find_author(uploadInfo.getAuthor());
            if (author_id==null) {
                return 0;
            }
            Integer algo_id=algoMapper.max_algo_id();
            if (algo_id==null) {
                algo_id=0;
            }
            algoMapper.upload(algo_id+1, uploadInfo.getTitle(), uploadInfo.getContent(), author_id,
                    uploadInfo.getDesc(), uploadInfo.getLine(), uploadInfo.getLanguage());
            String[] tags=uploadInfo.getTags();
            for (int i=0; i<tags.length; i++) {
                algoMapper.upload_tag(algo_id+1, tags[i]);
            }
            userMapper.updateScore(uploadInfo.getAuthor());
            return 1;
        }
    }
}
