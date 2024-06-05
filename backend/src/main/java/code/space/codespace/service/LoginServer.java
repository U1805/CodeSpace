package code.space.codespace.service;

import code.space.codespace.pojo.LoginInfo;
import code.space.codespace.pojo.RegisterInfo;
import org.springframework.stereotype.Service;

public interface LoginServer {
    int login(LoginInfo loginInfo);
    Integer register(RegisterInfo registerInfo);
}
