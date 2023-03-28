package com.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@ToString
@XmlRootElement(name = "INFO")
@XmlAccessorType(XmlAccessType.FIELD)
public class AcdmInfo {
    /** 航班标识 **/
    @XmlElement(name = "FFID")
    private String FFID="";
    /** 保障批次 **/
    @XmlElement(name = "BATCH")
    private String BATCH="";
    /** 当前保障环节的状态 **/
    @XmlElement(name = "STATUS")
    private String STATUS="";
    /** 保障环节开始时间 **/
    @XmlElement(name = "BEGINTM")
    private String BEGINTM="";
    /** 保障环节结束时间 **/
    @XmlElement(name = "ENDTM")
    private String ENDTM="";
}
