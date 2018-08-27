package com.gk.proxy;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Car implements Moveable {
    private String name;
    private double value;
    public double size;

    public Car() {
    }

    public Car(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void move() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            //ignore
        }
        log.info("汽车行驶中");
    }

    @Override
    public void speedUp(int level) {
        log.info("speed up to:{}", level);
    }

}
