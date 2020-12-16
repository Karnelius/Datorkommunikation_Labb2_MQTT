package com.company;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class TempClients extends SubController {


    public static void client() throws InterruptedException {

        String topic = "Daze/Temperature";
        String content = String.valueOf(TempSensor.tempRandomizer());
        int qos = 2;
        String broker = "tcp://broker.hivemq.com:1883";
        String clientId = "DazeTempSensor";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            sampleClient.connect(connOpts);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);


        } catch (MqttException me) {
            me.printStackTrace();
        }
    }
}




