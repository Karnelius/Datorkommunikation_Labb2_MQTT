package com.company;

import org.eclipse.paho.client.mqttv3.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TempLog implements MqttCallback {

    MqttClient client;

    public void logToFile() throws MqttException {

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



        //För att skriva. Vi kan bryta ut i metod.
        try {

            FileWriter myWriter = new FileWriter("TempLog111.txt");
            BufferedWriter buffW = new BufferedWriter(myWriter);
            myWriter.write("Datetime: " + " " + "xxx" + " "  + "//" + " "  + "Source: " + "yyyy" + " "  + "//" + " " + "Value: " + mqttMessage.toString());
            buffW.newLine();
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

}


