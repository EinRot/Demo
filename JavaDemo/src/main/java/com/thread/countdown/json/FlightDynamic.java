package com.thread.countdown.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class FlightDynamic implements Serializable {
    /** 航班ID  */
    private long flightId;
    /** 关联航班ID  */
    private Long associatedFlightId;
    /** 业务日期 */
    private DateTime date;
    /** 执行日期 */
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime execDate;
    /** 任务 */
    private String task;
    /** 航空公司二字码 */
    private String airlinesTwocharcode;
    /**  航班号 */
    private String flightNo;
    /** 进出标志 */
    private String isOffIn;
    /** 属性 */
    private Integer attribute;
    /** 机号编码 */
    private String aerocraftNo;
    /** 始发站三字码 */
    private String startStationThreecharcode;
    /** 始发计划起飞时间 */
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime startSchemeTakeoffTime;
    /** 始发预计起飞时间 */
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime startAlterateTakeoffTime;
    /** 始发实际起飞时间  */
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime startRealTakeoffTime;
    /** 目的站三字码 */
    private String terminalStationThreecharcode;
    /** 目的站计划降落时间 */
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime terminalSchemeLandinTime;
    /** 目的站预计降落时间 */
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime terminalAlterateLandinTime;
    /** 目的站实际降落时间 */
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime terminalRealLandinTime;
    /** 是否虚拟航班 */
    @JsonProperty("isVirtual")
    private Boolean virtual;
    /** 是否要客 */
    @JsonProperty("isVip")
    private Boolean vip;
    /** 代理 */
    private Integer agency;
    /** 完整航线 */
    private String airlineFull;
    /** 单段航线 */
    private String airlineShort;
    //TODO
    /** 单段航线中文名 */

    /** 机型 */
    private String craftType;
    /** 航班状态 */
    private String abnormalState;
    /** 计算起飞时间 */
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime ctot;
    /** 计算撤轮挡时间 */
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime cobt;
    /** 航班发布状态国内部分 */
    private String fltNormalStatus;
    /** 航班发布状态国际部分 */
    private String fltNormalStatus2;
    /** 航班不正常状态国内部分 */
    private String fltExceptionStatus;
    /** 航班不正常状态国际部分 */
    private String fltExceptionStatus2;
    /** 航班不正常状态原因国内部分 */
    private String fltExceptionReason;
    /** 航班不正常状态原因国际部分 */
    private String fltExceptionReason2;
    /** 实际到岗时间 */
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime aibt;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime aldt;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime aobt;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime arst;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime atot;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime codt;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime ddtm;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime dfdl;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime eldt;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime eobt;
    private String gateCode;
    private String heading;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime inseattime;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime pushtime;
    private String ista;
    private String mist;
    private String poa;
    private String poa3;
    private String pod;
    private String pod3;
    private String route;
    @JsonDeserialize(using = IocDateTimeDeserializer.class)
    private DateTime tobt;
    private String runway;
    private String apot;
}
