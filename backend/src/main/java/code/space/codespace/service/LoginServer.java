package code.space.codespace.service;

import code.space.codespace.pojo.LoginInfo;
import org.springframework.stereotype.Service;

@Service
public interface LoginServer {
    int login(LoginInfo loginInfo);
}
