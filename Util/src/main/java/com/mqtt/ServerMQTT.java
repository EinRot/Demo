package com.mqtt; /**
 * Created by Administrator on 17-2-10.
 */

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Title:Server
 * Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 * @author admin
 * 2019年12月8日下午17:50:15
 */
public class ServerMQTT {
 
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public static final String HOST = "tcp://127.0.0.1:18834";
    //定义一个主题
    public static final String TOPIC = "einice";
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "server";
 

    private void connect() throws MqttException {
        MqttClient client = new MqttClient(HOST, clientid, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
            MqttTopic topic = client.getTopic(TOPIC);

            MqttMessage mqttMessage = new MqttMessage();
            //发生机制：0=最多一次；1=最少一次；2=只有一次，Sender和Receiver进行双向确认
            mqttMessage.setQos(1);
            mqttMessage.setRetained(true);
            mqttMessage.setPayload("ServerTest".getBytes());

            MqttDeliveryToken token = topic.publish(mqttMessage);
            token.waitForCompletion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        ServerMQTT server = new ServerMQTT();
//        server.connect();
        ArrayList<String> src = new ArrayList<>();
        List<String> strings = Arrays.asList("a", "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k");
        while (true){
//            src.add(strings.);
        }

    }
}