package com.xml;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;

/**
  * @author EinIce
  * @description: TODO 发送ACDM消息体
  * @date 2021/11/12
  */
@Getter
@Setter
@XmlRootElement(name = "MSG")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlSeeAlso({AcdmInfo.class,BridgeInfo.class})
public class XmlMsgsBean<T> {
    private AcdmHead META;

    @XmlAnyElement(lax=true)
    private T INFO;
}