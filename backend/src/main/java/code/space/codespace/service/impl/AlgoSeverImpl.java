package code.space.codespace.service.impl;

import code.space.codespace.mapper.AlgoMapper;
import code.space.codespace.pojo.Algorithm;
import code.space.codespace.pojo.UploadInfo;
import code.space.codespace.service.AlgoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgoSeverImpl implements AlgoServer{
    @Autowired
    private AlgoMapper algoMapper;

    @Override
    public Algorithm[] info(String keyword, String author) {
        Algorithm[] algorithm;
        Integer user_id;
        if (keyword==null) {
            user_id=algoMapper.find_author(author);
//            System.out.println(user_id);
            algorithm = algoMapper.search_1(user_id);
            for (int i=0; i<algorithm.length; i++) {
                algorithm[i].setAuthor(author);
            }
            System.out.println(algorithm[0].getAlgo_id());
        }
        else { //still not tested.
            if (author==null) {
                Integer[] algo_id= algoMapper.search_algo_id(keyword);
                algorithm = new Algorithm[algo_id.length];
                for (int i=0; i<algorithm.length; i++) {
                    algorithm[i]=algoMapper.search_2(algo_id[i]);
                }
            }
            else {
                algorithm = null; //still need to be completed
            }
        }
        if (algorithm==null) {
            return null;
        }
        else {
            for (int i=0; i<algorithm.length; i++) {
                Integer id=algorithm[i].getAlgo_id();
                String[] tags= algoMapper.search_tag(id);
                System.out.println(tags[0]);
                algorithm[i].setTag(tags);
            }
            return algorithm;
        }
    }

    @Override
    public Integer upload(UploadInfo uploadInfo) {
        if (uploadInfo.getTitle()==null) {
            return 0;
        }
        else if (uploadInfo.getContent()==null) {
            return 0;
        }
        else if (uploadInfo.getAuthor()==null) {
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
            return 1;
        }
    }
}
