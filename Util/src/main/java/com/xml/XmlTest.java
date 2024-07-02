package com.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO xml测试
 * 　　* @date 2021/11/12
 *
 */
public class XmlTest {
    public static void main(String[] args) throws JAXBException {
        XmlMsgsBean<AcdmInfo> msg = new XmlMsgsBean();
        AcdmHead acdmHead = new AcdmHead();
        AcdmInfo acdmInfo = new AcdmInfo();
        msg.setMETA(acdmHead);
        msg.setINFO(acdmInfo);

        StringWriter sw = new StringWriter();
        StringBuffer xml = new StringBuffer();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(msg.getClass());

            Marshaller marshaller = context.createMarshaller();
            //不加<xml>头
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            //格式化输出
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            // 将对象转换成输出流形式的xml
            marshaller.marshal(msg, sw);
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xml.append(sw);
            System.out.println(xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public void ObjectToXml(Object obj){
        StringWriter sw = new StringWriter();
        StringBuffer xml = new StringBuffer();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            //不加<xml>头
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            //格式化输出
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xml.append(sw);
            System.out.println(xml);
            sw.close();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
