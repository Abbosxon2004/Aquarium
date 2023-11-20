package org.example;

import lombok.Getter;

@Getter
public class Fish extends Thread {
    private static int counter = 0;
    private int lifeTime;
    private boolean gender;

    @Override
    public void run() {
        while (lifeTime > 0) {
            try {
                Thread.sleep(1000);
                lifeTime -= 1000;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Aquarium.fishList.remove(this);
        System.out.println(this.getName() + " is dead.💀💀💀");
    }

    public Fish(int lifeTime, boolean gender) {
        super.setName((gender ? "Male" : "Female") + " fish_" + ++counter);
        this.lifeTime = lifeTime;
        this.gender = gender;
        super.setDaemon(true);
    }
}