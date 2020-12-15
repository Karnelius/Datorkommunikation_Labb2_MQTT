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