package com.edge.s;

import com.drive.n.Collect;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author EinIce
 * @Date 2024/7/2 16:23
 **/
@Service
public class SouthManager implements InitializingBean {
    @Autowired
    private List<Collect> collects;

    @Override
    public void afterPropertiesSet() throws Exception {
        if(!ObjectUtils.isEmpty(collects))
            collects.forEach(collect -> collect.collect("开始采集"));
    }
}
