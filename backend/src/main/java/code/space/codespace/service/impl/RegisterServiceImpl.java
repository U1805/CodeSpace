package code.space.codespace.service.impl;

import code.space.codespace.mapper.RegisterMapper;
import code.space.codespace.pojo.RegisterInfo;
//import code.space.codespace.service.LoginServer;
import code.space.codespace.service.RegisterServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterServer {
    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public Integer register(RegisterInfo registerInfo) {
//        if (registerInfo.getUsername().length()<20) {
//
//        }
        if (registerMapper.find_username(registerInfo.getUsername())>0) {
            return 0;//username repeat
        }
        else {
            int user_id=registerMapper.max_user_id();
            registerMapper.register(user_id+1, registerInfo.getUsername(), registerInfo.getPassword());
            return 1;
        }
    }
}
