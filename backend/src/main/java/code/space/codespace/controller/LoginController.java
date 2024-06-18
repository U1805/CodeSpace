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
        log.info("管理员? " + loginInfo.getAdmin().toString());
        String res = loginServer.login(loginInfo);
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
        if(res == "username-repeat"){
            return Result.error("该用户名已被注册，换一个试试吧");
        } else if (res == "password-weak") {
            return Result.error("密码安全性不足，强度太弱");
        } else if (res == "username-empty") {
            return Result.error("用户名为空");
        } else if (res == "password-empty") {
            return Result.error("密码为空");
        } else{
            return Result.success(res);
        }
    }
}
