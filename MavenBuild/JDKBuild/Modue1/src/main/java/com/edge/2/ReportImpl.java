package com.edge.n;

import com.drive.s.Report;
import com.sun.tools.javac.Main;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author EinIce
 * @Date 2024/7/3 18:26
 **/
@Component
public class ReportImpl implements Report {
    @Override
    public void report(String str) {
        System.out.println("1:"+str);
    }
}
