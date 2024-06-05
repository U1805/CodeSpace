package code.space.codespace.service;

import code.space.codespace.pojo.Algorithm;
import code.space.codespace.pojo.UploadInfo;
import org.springframework.stereotype.Service;

public interface AlgoServer {
    Algorithm[] info(String keyword, String author);

    Integer upload(UploadInfo uploadInfo);

}
