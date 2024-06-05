package code.space.codespace.service.impl;

import code.space.codespace.mapper.UploadMapper;
import code.space.codespace.pojo.UploadInfo;
import code.space.codespace.service.UploadServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServerImpl implements UploadServer{
    @Autowired
    private UploadMapper uploadMapper;

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
            Integer author_id=uploadMapper.find_author(uploadInfo.getAuthor());
            if (author_id==null) {
                return 0;
            }
            Integer algo_id=uploadMapper.max_algo_id();
            if (algo_id==null) {
                algo_id=0;
            }
            uploadMapper.upload(algo_id+1, uploadInfo.getTitle(), uploadInfo.getContent(), author_id,
                    uploadInfo.getDesc(), uploadInfo.getLine(), uploadInfo.getLanguage());
            String[] tags=uploadInfo.getTags();
            for (int i=0; i<tags.length; i++) {
                uploadMapper.upload_tag(algo_id+1, tags[i]);
            }
            return 1;
        }
    }
}
