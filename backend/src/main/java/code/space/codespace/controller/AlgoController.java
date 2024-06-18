package code.space.codespace.controller;

import code.space.codespace.pojo.Result;
import code.space.codespace.pojo.Algorithm;
import code.space.codespace.pojo.UploadInfo;
import code.space.codespace.service.AlgoServer;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AlgoController {
    @Autowired
    private AlgoServer algoServer;

    @GetMapping("/algos")
    public Result info(@PathParam("keyword") String keyword, @PathParam("author") String author){
        if (keyword==null && author == null) {
            return Result.error("Without keyword or author.");
        }

        Algorithm[]  algorithm = algoServer.info(keyword, author);
        if (algorithm==null) {
            return Result.error("No algorithms.");
        }
        return Result.success(algorithm);
    }

    @PostMapping("/algos")
    public Result upload(@RequestBody UploadInfo uploadInfo){
        Integer res = algoServer.upload(uploadInfo);
        if(res==0){
            return Result.error("缺少必要信息");
        }
        else{
            return Result.success(res);
        }
    }
}
