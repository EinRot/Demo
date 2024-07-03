package com.edge.n;

import com.drive.s.Report;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author EinIce
 * @Date 2024/7/2 16:22
 **/
@Service
public class NorthManager implements InitializingBean {
    @Autowired
    private List<Report> reports;

    @Override
    public void afterPropertiesSet() throws Exception {
        reports.forEach(report -> report.report("开始发送"));
    }
}
