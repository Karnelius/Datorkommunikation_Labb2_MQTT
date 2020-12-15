package com.company;

import org.eclipse.paho.client.mqttv3.*;

public class Main extends MqttSub {


    public static void main(String[] args) throws MqttException, InterruptedException {

        MqttSub x = new MqttSub();
        TempLog y = new TempLog();

        y.logToFile();

        do {
            MqttClients.mqtt();
            x.subTest();
        } while (true);

    }
}



