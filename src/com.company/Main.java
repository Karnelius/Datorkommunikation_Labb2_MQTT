package com.company;


import org.eclipse.paho.client.mqttv3.*;


/*Ni skall:
1. Skriva en simulerad temperatursensor som varje minut publicerar en "temperatur" mellan 15 och 25 grader på en topic på t.ex. HiveMQ.

2. Skriva ett program som prenumererar på temperaturer och publicerar styrdata på en separat topic(kanal).
   Om temperaturen är under 22 skall + publiceras, om temperaturen är 22 eller högre skall - publiceras


3. Skriva ett program som prenumererar både på temperaturer och styrdata och loggar dessa i en textfil på formatet <datetime>, <source>, <value>
Exempel:
2020-12-04 15:25:20,  temperature, 20
2020-12-04 15:25:21, ctrl, +
*/




public class Main extends MqttSub {


    public static void main(String[] args) throws MqttException, InterruptedException {

        MqttSub x = new MqttSub();
        TempLog y = new TempLog();

        y.logToFile();


        do {
           MqttClients.mqtt();
           x.subTest();
        }while(true);


    }
    }



