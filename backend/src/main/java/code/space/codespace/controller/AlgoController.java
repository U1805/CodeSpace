package code.space.codespace.controller;

import code.space.codespace.pojo.Result;
import code.space.codespace.pojo.Algorithm;
import code.space.codespace.pojo.UploadInfo;
import code.space.codespace.service.AlgoServer;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlgoController {
    @Autowired
    private AlgoServer algoServer;

    @GetMapping("/algo")
    public Result info(@PathParam("keyword") String keyword, @PathParam("author") String author){
//        System.out.println(keyword+","+ author);
        Algorithm[] algorithm;
        if (keyword==null) {
            if (author==null) {
                return Result.error("Without keyword or author.");
            }
        }
        algorithm = algoServer.info(keyword, author);
        if (algorithm==null) {
            return Result.error("No algorithms.");
        }
        return Result.success(algorithm);
    }

    @PostMapping("/algo")
    public Result upload(@RequestBody UploadInfo uploadInfo){
        Integer res = algoServer.upload(uploadInfo);
        if(res==0){
            return Result.error("缺少必要信息");
        }
        else{
            return Result.success();
        }
    }
}
