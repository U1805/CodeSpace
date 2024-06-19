package code.space.codespace.service.impl;

import code.space.codespace.mapper.UserMapper;
import code.space.codespace.pojo.User;
import code.space.codespace.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServerImpl implements UserServer {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User info(String username) {
        return userMapper.getUserInfo(username);
    }

    @Override
    public void edit(User user) {
        userMapper.setInfo(user);
    }

    @Override
    public List<User> getList() {
        return userMapper.getList();
    }

    @Override
    public void delete(String username) {
        userMapper.delete(username);
    }
}
