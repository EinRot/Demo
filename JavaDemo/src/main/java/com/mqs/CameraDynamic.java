package com.mqs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 算法动态
 * 　　* @date 2021/10/27
 *
 */
@Getter
@Setter
@ToString
@Slf4j
public class CameraDynamic implements Cloneable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//只序列化
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonProperty(value = "task_id")
    private String taskId;
    @JsonProperty(value = "message_id")
    private String messageId;
    private String detectBox;
    @JsonProperty(value = "event_type")
    private String eventType;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//只序列化
    private String dataType;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//只序列化
    private String dataName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//只序列化
    private String englishName;
    private String imageUrl;
    @JsonProperty(value = "stream_id")
    private String cameraId;
    @JsonProperty(value = "timestamp")
    private LocalDateTime eventTime;
    private String berthNo;
    private String flightId;
    private Boolean isCount;
    private Boolean sendStatus;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//只序列化
    private LocalDateTime createTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//只序列化
    private LocalDateTime updateTime;

    public Object clone(){
        Object o=null;
        try{
            o=super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return o;
    }
}
