package code.space.codespace.service.impl;

import code.space.codespace.mapper.DemoMapper;
import code.space.codespace.pojo.Demo;
import code.space.codespace.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<Demo> list() {
        return demoMapper.list();
    }
}
