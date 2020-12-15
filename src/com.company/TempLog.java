package com.company;

import org.eclipse.paho.client.mqttv3.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TempLog implements MqttCallback {

    MqttClient client;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public void logToFile() throws MqttException {

        try {
            client = new MqttClient("tcp://broker.hivemq.com:1883", "DazeLog");
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            client.connect(connOpts);
            client.setCallback(this);
            client.subscribe("Daze/Temperature", 2);
            client.subscribe("Daze/Control", 2);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void writeToLog(String date, String topic, String message) {
        String logLine = date + ", " + topic + ", " + message + "\n";
        try {
            FileWriter myWriter = new FileWriter("logger.txt", true);
            myWriter.write(logLine);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Metod för att returnera en clean sträng. Behövs eller inte?

    private String topicToCleanString (String s){
        if(s.equals("Daze/Temperature")){
            return "Temperature";
        }else return "Controller";
    }

    @Override
    public void connectionLost(Throwable throwable) {
    }


    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String date = LocalDateTime.now().format(formatter);
        writeToLog(date, topicToCleanString(s), mqttMessage.toString());

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

}


