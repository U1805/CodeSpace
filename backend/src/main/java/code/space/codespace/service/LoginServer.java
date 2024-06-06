package code.space.codespace.service;

import code.space.codespace.pojo.LoginInfo;
import code.space.codespace.pojo.RegisterInfo;

public interface LoginServer {
    String login(LoginInfo loginInfo);
    String register(RegisterInfo registerInfo);
}
