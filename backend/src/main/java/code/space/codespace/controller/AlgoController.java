package code.space.codespace.controller;

import code.space.codespace.pojo.Result;
import code.space.codespace.pojo.Algorithm;
import code.space.codespace.service.AlgoServer;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
