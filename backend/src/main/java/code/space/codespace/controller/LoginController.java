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
        int res = loginServer.login(loginInfo);
        if(res == 1){
            return Result.success("cswaerasd");
        }
        else {
           return Result.error("用户名不存在或密码错误");
        }
    }


    @PostMapping("/register")
    public Result register(@RequestBody RegisterInfo registerInfo){
        Integer res = loginServer.register(registerInfo);
        if(res==0){
            return Result.error("用户名重复");
        }
        else{
            return Result.success("cswaerasd");
        }
    }
}
