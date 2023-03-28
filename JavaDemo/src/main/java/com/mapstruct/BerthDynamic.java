package com.mapstruct;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 机位动态信息
 * 　　* @date 2021/10/20
 *
 */
@Getter
@Setter
@ToString
public class BerthDynamic {
    /** 航班ID */
    private Long flightId;
    /** 机位编号 */
    private String craftNo;
    /** 机位 */
    private String craftSite;
    /** 预计进机位时间 */
    private LocalDateTime startTime;
    /** 预计出机位时间 */
    private LocalDateTime endTime;
    /** 机位实际开始使用时间 */
    private LocalDateTime startRealTime;
    /** 机位实际结束使用时间 */
    private LocalDateTime endRealTime;
    private String realTimeType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//只序列化
    private LocalDateTime createTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//只序列化
    private LocalDateTime updateTime;
}










