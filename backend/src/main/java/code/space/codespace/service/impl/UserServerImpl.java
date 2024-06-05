package code.space.codespace.service.impl;

import code.space.codespace.mapper.UserMapper;
import code.space.codespace.pojo.User;
import code.space.codespace.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServerImpl implements UserServer {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User info(String username) {
        User user = userMapper.search(username); //When user is null, need to return null to make UserController return 0
        if (user==null) {
            return null;
        }
        else {
            Integer repo = userMapper.count_algo(username);
            user.setRepo(repo != null?repo:0);
            Integer line = userMapper.count_line(username);
            user.setLine(line != null?line:0);
            return user;
        }
    }
}
