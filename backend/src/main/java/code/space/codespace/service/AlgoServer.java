package code.space.codespace.service;

import code.space.codespace.pojo.Algorithm;
import org.springframework.stereotype.Service;

@Service
public interface AlgoServer {
    Algorithm[] info(String keyword, String author);

}
