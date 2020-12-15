package com.company;

import org.eclipse.paho.client.mqttv3.*;

public class MqttSub implements MqttCallback {

    MqttClient client;

    public void subTest() throws MqttException {

        try {
            client = new MqttClient("tcp://broker.hivemq.com:1883", "twrtwrtwrt555");
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            client.connect(connOpts);
            client.setCallback(this);
            client.subscribe("Tempa1", 2);

        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
    }
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        MqttMessage reply = new MqttMessage(mqttMessage.getPayload());

        String content = new String(reply.getPayload());

        int temp = Integer.parseInt(content);


        if(temp > 22){
            client.publish("Tempa",);
        }else{
            client.publish("Tempa",reply);
        }











       // String message = new String(mqttMessage.getPayload());
        //System.out.println("Got temp: " + new String(mqttMessage.getPayload()));
       // int temperature = Integer.parseInt(message);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
    private String setTemperatureControl(String temp) {
        int check = Integer.parseInt(temp);
        if(check < 22.0) {
            return  "+";
        } else {
            return  "-";
        }
    }

}


