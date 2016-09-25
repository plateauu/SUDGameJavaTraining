package com.plateauu.sudgame;

public class Statistics {

    private int health;
    private int strenght;
    private int agility;

    public Statistics(int health, int strenght, int agility) {
        this.health = health;
        this.strenght = strenght;
        this.agility = agility;
    }

    public int getHealth() {
        return health;
    }

    public int getStrenght() {
        return strenght;
    }

    public int getAgility() {
        return agility;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Health: " + health
                + "\nStrenght: " + strenght
                + "\nAgility: " + agility;
    }

}
