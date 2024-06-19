package code.space.codespace.service;

import code.space.codespace.pojo.Algorithm;
import code.space.codespace.pojo.UploadInfo;

import java.util.List;

public interface AlgoServer {
    List<Algorithm> info(String keyword, String author);

    Integer upload(UploadInfo uploadInfo);

    Boolean delete(Integer id);

    void edit(Algorithm algorithm);
}
