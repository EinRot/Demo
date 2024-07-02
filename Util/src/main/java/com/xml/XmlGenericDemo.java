package com.xml;

import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 数据转XML对象
 * 　　* @date 2021/11/25
 *
 */
public class XmlGenericDemo {
    public static void main(String[] args) throws Exception {
//        test();
//        if(true)return;
        Reader reader = new FileReader("JavaDemo/src/main/resources/廊桥测试1.xml");
//        Reader reader = new FileReader("D:\\迅雷下载\\DwnlData\\截图\\test1.json");
        int ch = reader.read();
        StringBuffer buffer = new StringBuffer();
        while (ch != -1) { // 读取成功
            buffer.append((char) ch);
            ch = reader.read();
        }
        reader.close();

        String xml = buffer.toString();
        JAXBContext context = JAXBContext.newInstance(XmlMsgsBean.class , AcdmInfo.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        XmlMsgsBean<AcdmInfo> msgBean = (XmlMsgsBean<AcdmInfo>) unmarshaller.unmarshal(new StringReader(xml));
        System.out.println("--"+msgBean.toString());
    }
    @Test
    public void test(){
        XmlMsgsBean<BridgeInfo> msg = new XmlMsgsBean();
        AcdmHead acdmHead = new AcdmHead();
        BridgeInfo acdmInfo = new BridgeInfo();
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
}
