package code.space.codespace.controller;

import code.space.codespace.pojo.RegisterInfo;
import code.space.codespace.pojo.Result;
import code.space.codespace.pojo.LoginInfo;
import code.space.codespace.service.LoginServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private LoginServer loginServer;

    @PostMapping("/login")
    public Result login(@RequestBody LoginInfo loginInfo){
        String res = loginServer.login(loginInfo);
        log.info(res);
        if(res.length() == 0){
            return Result.error("用户名不存在或密码错误");
        }
        else {
            return Result.success(res);
        }
    }


    @PostMapping("/register")
    public Result register(@RequestBody RegisterInfo registerInfo){
        String res = loginServer.register(registerInfo);
        if(res.length() == 0){
            return Result.error("用户名重复");
        }
        else{
            return Result.success(res);
        }
    }
}
