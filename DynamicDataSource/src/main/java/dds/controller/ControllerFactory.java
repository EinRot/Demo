package dds.controller;

import dds.service.DevgeoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
  * @author EinIce
  * @description TODO 定时器工厂
  * @date 2022/3/29
  */
@Slf4j
@Configuration
public class ControllerFactory {
    @Autowired
    private DevgeoService devgeoService;

    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void cleanBerthGroupExcess(){
        devgeoService.query();
        devgeoService.query2();
    }
}
