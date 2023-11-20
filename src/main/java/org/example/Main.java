package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Aquarium aquarium = new Aquarium();
        aquarium.fillAquarium();
        for (Fish fish : aquarium.fishList) {
            fish.start();
        }
        aquarium.giveBirth();
        System.out.println(aquarium.fishList.size() == 0
                ? "All fish are dead"
                : "Program stopped with " + aquarium.fishList.size() + " same gender fish");
    }
}
