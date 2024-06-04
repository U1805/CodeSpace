package code.space.codespace.service;

import code.space.codespace.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserServer {
    User info(String username);
}
