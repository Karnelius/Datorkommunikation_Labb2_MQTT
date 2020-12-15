package com.company;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClients extends MqttSub {


    public static void mqtt() throws InterruptedException {

        String topic = "Tempa1";
        String content = String.valueOf(TempSensor.tempRandomizer());
        int qos = 2;
        String broker = "tcp://broker.hivemq.com:1883";
        String clientId = "Sensor";

        MemoryPersistence persistence = new MemoryPersistence();


        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);
            //System.out.println("Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);


        } catch (MqttException me) {
            me.printStackTrace();
        }
    }
}




