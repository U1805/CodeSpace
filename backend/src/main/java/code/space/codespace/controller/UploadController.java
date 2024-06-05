package code.space.codespace.controller;

import code.space.codespace.pojo.UploadInfo;
import code.space.codespace.pojo.Result;
import code.space.codespace.service.UploadServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UploadController {
    @Autowired
    private UploadServer uploadServer;

    @PostMapping("/algo")
    public Result upload(@RequestBody UploadInfo uploadInfo){
        Integer res = uploadServer.upload(uploadInfo);
        if(res==0){
            return Result.error("缺少必要信息");
        }
        else{
            return Result.success();
        }
    }
}
