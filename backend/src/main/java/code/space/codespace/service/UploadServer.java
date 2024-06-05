package code.space.codespace.service;

import code.space.codespace.pojo.UploadInfo;
import org.springframework.stereotype.Service;

@Service
public interface UploadServer {
    Integer upload(UploadInfo uploadInfo);
}
