package code.space.codespace.service;

import code.space.codespace.pojo.User;

public interface UserServer {
    User info(String username);

    void edit(User user);
}
