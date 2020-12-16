package com.company;

import org.eclipse.paho.client.mqttv3.*;

public class Main extends SubController {
    //Used to run all the modules at the same time.

    public static void main(String[] args) throws MqttException, InterruptedException {

        SubController x = new SubController();
        TempLog y = new TempLog();

        y.logToFile();

        do {
            TempClients.client();
            x.subscriber();
        } while (true);
    }
}



