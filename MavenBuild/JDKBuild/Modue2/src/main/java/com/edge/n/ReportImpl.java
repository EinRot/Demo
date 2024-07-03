package com.edge.n;

import com.drive.s.Report;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author EinIce
 * @Date 2024/7/3 18:26
 **/
@Component
public class ReportImpl implements Report {
    @Override
    public void report(String str) {
        System.out.println("2:"+str);
    }
}
