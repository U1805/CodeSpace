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
    public int login(LoginInfo loginInfo) {
        int admin_ = loginInfo.getAdmin()? 1 : 0;
        return userMapper.login(loginInfo.getUsername(), loginInfo.getPassword(), admin_);
    }

    @Override
    public Integer register(RegisterInfo registerInfo) {
//        if (registerInfo.getUsername().length()<20) {
//
//        }
        if (userMapper.find_username(registerInfo.getUsername())>0) {
            return 0;//username repeat
        }
        else {
            Integer user_id=userMapper.max_user_id();
            if (user_id==null){
                userMapper.register(1, registerInfo.getUsername(), registerInfo.getPassword());
            }
            else {
                userMapper.register(user_id+1, registerInfo.getUsername(), registerInfo.getPassword());
            }
            return 1;
        }
    }
}
