package dds.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 多数据源服务测试
 * 　　* @date 2022/3/29
 */
@Service
public class DevgeoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DS("traffic2")
    public void query(){
        System.out.println(jdbcTemplate.queryForList("select count(*) from acdm_msg"));
    }

    @DS("traffic")
    public void query2(){
        System.out.println(jdbcTemplate.queryForList("select count(*) from devgeo"));
    }
}
