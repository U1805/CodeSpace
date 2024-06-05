package code.space.codespace.service;

import code.space.codespace.pojo.RegisterInfo;
import org.springframework.stereotype.Service;

@Service
public interface RegisterServer {
    Integer register(RegisterInfo registerInfo);
}
