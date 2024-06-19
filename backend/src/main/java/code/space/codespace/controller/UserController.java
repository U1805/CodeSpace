package code.space.codespace.controller;

import code.space.codespace.pojo.Result;
import code.space.codespace.pojo.User;
import code.space.codespace.service.UserServer;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserServer userServer;

    @GetMapping("/users/{username}")
    public Result info(@PathVariable("username") String username){
        log.info(username);
        User user = userServer.info(username);
        if (user==null) {
            return Result.error("用户不存在");
        }
        else {
            return Result.success(user);
        }
    }

    @GetMapping("/users/all")
    public Result getAll(){
        List<User> users = userServer.getList();
        return Result.success(users);
    }

    @PutMapping("/users")
    public Result edit(@RequestBody User user){
        log.info(user.toString());
        try {
            userServer.edit(user);
            return Result.success();
        }catch (Exception e) {
            return Result.error("编辑信息错误");
        }
    }

    @DeleteMapping("/users/{username}")
    public Result delete(@PathVariable("username") String username){
        User user = userServer.info(username);
        if (user == null) return Result.error("用户不存在");
        userServer.delete(username);
        return Result.success();
    }
}
