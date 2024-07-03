package com.edge.s;

import com.drive.n.Collect;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author EinIce
 * @Date 2024/7/3 18:31
 **/
@Component
public class CollectImpl2 implements Collect {
    @Override
    public void collect(String str) {
        System.out.println("2:"+str);
    }
}
