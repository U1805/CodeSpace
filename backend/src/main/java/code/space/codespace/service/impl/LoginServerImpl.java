package code.space.codespace.service.impl;

import code.space.codespace.mapper.UserMapper;
import code.space.codespace.pojo.LoginInfo;
import code.space.codespace.pojo.RegisterInfo;
import code.space.codespace.service.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServerImpl implements LoginServer {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(LoginInfo loginInfo) {
        int admin_ = loginInfo.getAdmin()? 1 : 0;
        if (userMapper.login(loginInfo.getUsername(), loginInfo.getPassword(), admin_) == 1){
            return "cswaerasd"; // TODO: 计算 userToken 并返回
        }
        else {
            return "";
        }
    }

    @Override
    public String register(RegisterInfo registerInfo) {
        if (userMapper.find_username(registerInfo.getUsername())>0) {
            return ""; //username repeat
        }
        else {
            userMapper.register(registerInfo.getUsername(), registerInfo.getPassword());
            return "cswaerasd"; // TODO: 计算 userToken 并返回
        }
    }
}
