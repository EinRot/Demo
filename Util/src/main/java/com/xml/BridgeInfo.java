package com.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 廊桥数据
 * 　　* @date 2021/11/25
 *
 */
@Getter
@Setter
@ToString
@XmlRootElement(name = "DBAS")
@XmlAccessorType(XmlAccessType.FIELD)
public class BridgeInfo {
    @XmlElement(name = "MSID")
    private String MSID="";
    @XmlElement(name = "FLID")
    private String FLID="";
    @XmlElement(name = "FFID")
    private String FFID="";
    @XmlElement(name = "FLNO")
    private String FLNO="";
    @XmlElement(name = "SDAT")
    private String SDAT="";
    @XmlElement(name = "SODT")
    private String SODT="";
    @XmlElement(name = "RENO")
    private String RENO="";
    @XmlElement(name = "DBNO")
    private String DBNO="";
    @XmlElement(name = "DBID")
    private String DBID="";
    @XmlElement(name = "DBST")
    private String DBST="";
    @XmlElement(name = "DBET")
    private String DBET="";
}
