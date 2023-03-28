package com.mapstruct;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 机位匹配表
 * 　　* @date 2021/10/28
 */
@Getter
@Setter
@ToString
public class BerthMaster {
    private String craftSite;
    private String flightId;
    private LocalDateTime startRealTime;
    private LocalDateTime endRealTime;
    private String realTimeType;
    private String lastFlightId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//只序列化
    private LocalDateTime createTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//只序列化
    private LocalDateTime updateTime;
}
