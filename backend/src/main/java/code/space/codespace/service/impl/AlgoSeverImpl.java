package code.space.codespace.service.impl;

import code.space.codespace.mapper.AlgoMapper;
import code.space.codespace.pojo.Algorithm;
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
}
