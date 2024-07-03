package com.edge.s;

import com.drive.n.Collect;
import com.sun.tools.javac.Main;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author EinIce
 * @Date 2024/7/3 18:31
 **/
@Component
public class CollectImpl implements Collect {
    @Override
    public void collect(String str) {
        System.out.println("1:"+str);
    }
}
