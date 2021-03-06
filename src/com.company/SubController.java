package com.company;

import org.eclipse.paho.client.mqttv3.*;

public class SubController implements MqttCallback {

    MqttClient client;
    String topic = "Daze/Control";

    public void subscriber() throws MqttException {

        try {
            client = new MqttClient("tcp://broker.hivemq.com:1883", "DazeControl");
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            client.connect(connOpts);
            client.setCallback(this);
            client.subscribe("Daze/Temperature", 2);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
    }


    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        MqttMessage reply = new MqttMessage(mqttMessage.getPayload());
        new MqttMessage(reply.getPayload());

        new MqttMessage("+".getBytes());
        new MqttMessage("-".getBytes());

        MqttMessage reply1 = new MqttMessage("+".getBytes());
        MqttMessage reply2 = new MqttMessage("-".getBytes());

        String content = new String(reply.getPayload());

        int temp = Integer.parseInt(content);

        if (temp >= 22) {
            client.publish(this.topic, reply2);
            //System.out.println(reply1 + " +");
        } else {
            client.publish(this.topic, reply1);
            //System.out.println(reply1 + " -");
        }
    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}


