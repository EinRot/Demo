package com.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PushCallback implements MqttCallback {
 
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }
 
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
 
    public void messageArrived(String topic, MqttMessage msg) throws MqttException {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + msg.getQos());
        System.out.println("接收消息内容 : " + new String(msg.getPayload()));

//        MqttMessage mqttMessage = new MqttMessage();
//        //发生机制：0=最多一次；1=最少一次；2=只有一次，Sender和Receiver进行双向确认
//        mqttMessage.setQos(1);
//        mqttMessage.setRetained(true);
//        mqttMessage.setPayload("ServerTest".getBytes());
//        MqttTopic mqttTopic = client.getTopic(TOPIC);
//        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
//        token.waitForCompletion();
    }
}