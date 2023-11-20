package org.example;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Aquarium {
    public static List<Fish> fishList = new CopyOnWriteArrayList<>();

    private static final int MAX_LIFE_TIME = 60_000;
    private static final int MAX_FISH_NUMBER = 10;

    public void fillAquarium() {
        System.out.println("Filling started");
        int n = getRandomNumber(MAX_FISH_NUMBER);
        int m = getRandomNumber(MAX_FISH_NUMBER);

        for (int i = 0; i < n; i++) {
            fishList.add(new Fish(getRandomNumber(MAX_LIFE_TIME), true));
        }
        for (int i = 0; i < m; i++) {
            fishList.add(new Fish(getRandomNumber(MAX_LIFE_TIME), false));
        }
        System.out.println("Aquarium is filled with " + n + " male and " + m + " female fish");
    }

    public void giveBirth() throws InterruptedException {
        while (fishList.size() >= 2
                && fishList.stream().anyMatch(Fish::isGender)
                && fishList.stream().filter(Fish::isGender).count() != fishList.size()) {
            System.out.println("##### Trying to find fish couples #####");
            Fish fish1 = fishList.get(getRandomNumber(fishList.size()) - 1);
            Fish fish2 = fishList.get(getRandomNumber(fishList.size()) - 1);
            if (fish1.isGender() != fish2.isGender()) {
                Fish fish = new Fish(getRandomNumber(MAX_LIFE_TIME), getRandomBoolean());
                fishList.add(fish);
                System.out.println(fish.getName() + " is born");
                fish.start();
            }
            Thread.sleep(getRandomNumber(10_000));
        }
    }

    public static int getRandomNumber(int bound) {
        return new Random().nextInt(bound) + 1;
    }

    public static boolean getRandomBoolean() {
        return new Random().nextBoolean();
    }
}