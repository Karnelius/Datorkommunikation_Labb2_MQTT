package com.company;

import java.util.Random;

public class TempSensor {

    public static int tempRandomizer() throws InterruptedException {
        Random rand = new Random();
        Thread.sleep(6000);
        do {
            return (rand.nextInt(25 - 15) + 15);
        } while (true);

    }
}











/* mqtt.client.publish -->

if(random>=22)
return "-" + " " +  "(" + random + "'c)";
else return "+" + " " + "(" + random + "'c)";



    static void timerTimer() {
        TimerTask task = new TimerTask() {
            public void run() {
                MqttClients.mqtt();
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intervalPeriod = 1000;
        timer.scheduleAtFixedRate(task, delay, intervalPeriod);

    }*/