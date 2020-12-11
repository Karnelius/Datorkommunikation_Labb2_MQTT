package com.company;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;




/*Ni skall:
1. Skriva en simulerad temperatursensor som varje minut publicerar en "temperatur" mellan 15 och 25 grader på en topic på t.ex. HiveMQ.



2. Skriva ett program som prenumererar på temperaturer och publicerar styrdata på en separat topic(kanal). <--- Skapa ett eget fattigt MQTT HOST CLIENT ....
   Om temperaturen är under 22 skall + publiceras, om temperaturen är 22 eller högre skall - publiceras

3. Skriva ett program som prenumererar både på temperaturer och styrdata och loggar dessa i en textfil på formatet <datetime>, <source>, <value>
Exempel:
2020-12-04 15:25:20,  temperature, 20
2020-12-04 15:25:21, ctrl, +
*/




public class Main {

    public static void main(String[] args) {

        timerTimer();
}

    private static void mqtt() {
        String topic = "TempTestzzzz";
        String content = tempRandomizer();
        int qos = 2;
        String broker = "tcp://broker.hivemq.com:1883";
        String clientId = "DaZeTest";

        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            //sampleClient.disconnect();
            //System.out.println("Disconnected");
            //System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    static void timerTimer() {
        TimerTask task = new TimerTask() {
            public void run() {
                mqtt();
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 6000;
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);

    }
          public static String tempRandomizer() {
        Random rand = new Random();
        int random = (rand.nextInt(25 - 15) + 15);
        //if(random>=22)
          //  return "-" + " " +  "(" + random + "'c)";
        //else return "+" + " " + "(" + random + "'c)";
              return String.valueOf(random);
          }
    }
