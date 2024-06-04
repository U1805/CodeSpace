package code.space.codespace.service.impl;

import code.space.codespace.mapper.LoginMapper;
import code.space.codespace.pojo.LoginInfo;
import code.space.codespace.service.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServerImpl implements LoginServer {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public int login(LoginInfo loginInfo) {
        int admin_ = loginInfo.getAdmin()? 1 : 0;
        return loginMapper.login(loginInfo.getUsername(), loginInfo.getPassword(), admin_);
    }
}
