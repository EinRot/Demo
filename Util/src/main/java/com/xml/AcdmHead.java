package com.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@ToString
@XmlRootElement(name = "META")
@XmlAccessorType(XmlAccessType.FIELD)
public class AcdmHead {
    /** 发送者 **/
    @XmlElement(name = "SNDR")
    private String SNDR="HWBZJD";
    /** 所发送的消息类型 **/
    @XmlElement(name = "TYPE")
    private String TYPE="GSSP";
    /** 所发送的消息子类型 **/
    @XmlElement(name = "STYP")
    private String STYP="";
    /** 时间 **/
    @XmlElement(name = "DTTM")
    private String DTTM= DateTime.now().toString("yyyyMMddHHmmss");
    /** 序号 **/
    @XmlElement(name = "SEQN")
    private String SEQN="";
    /** 航班所属航空公司二字码 **/
    @XmlElement(name = "AIRL")
    private String AIRL="";
    /** 航班进港出港属性 **/
    @XmlElement(name = "AORD")
    private String AORD="";
    /** 航班出发航站 **/
    @XmlElement(name = "DEPS")
    private String DEPS="";
    /** 航班到达航站 **/
    @XmlElement(name = "ARVS")
    private String ARVS="";
    /** 航班唯一ID **/
    @XmlElement(name = "FLID")
    private String FLID="";

    /** 廊桥-时间 **/
    @XmlElement(name = "DDTM")
    private String DDTM;
    @XmlElement(name = "RCVR")
    private String RCVR;
    @XmlElement(name = "MGID")
    private String MGID;
    @XmlElement(name = "RMID")
    private String RMID;
    @XmlElement(name = "APOT")
    private String APOT;
}
