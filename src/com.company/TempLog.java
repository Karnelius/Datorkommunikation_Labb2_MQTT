package com.company;

import org.eclipse.paho.client.mqttv3.*;

public class TempLog implements MqttCallback {

    MqttClient client;

    public void run() throws MqttException {

        try {
            client = new MqttClient("tcp://broker.hivemq.com:1883", "dfsdfZache");
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            client.connect(connOpts);
            client.setCallback(this);
            client.subscribe("Tempa1", 2);
            client.subscribe("Tempa", 2);

        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
    }


    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

}


