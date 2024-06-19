package code.space.codespace.service;

import code.space.codespace.pojo.User;

import java.util.List;

public interface UserServer {
    User info(String username);

    void edit(User user);

    List<User> getList();

    void delete(String username);
}
