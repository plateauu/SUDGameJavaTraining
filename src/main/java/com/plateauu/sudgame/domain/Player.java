package com.plateauu.sudgame.domain;

import com.plateauu.sudgame.Statistics;
import java.util.Random;

import com.plateauu.sudgame.monsters.Npc;

public class Player {

    private final String playerName;
    private Location currentLocation;
    private FightableStrategy fightableInterface;
    private Statistics stats;
    private boolean isAlive;

    public Player(String name, Location currentLocation) {
        this.playerName = name;
        this.currentLocation = currentLocation;
        this.fightableInterface = new FightBySwordStrategy();
        this.isAlive = true;
        stats = new Statistics(10000, 2, 10);
        welcomeMessage();
    }

    public Player(String name, Location currentLocation, int playerStrenght, int playerAgility) {
        this.playerName = name;
        this.currentLocation = currentLocation;
        this.isAlive = true;
        stats = new Statistics(10000, playerStrenght, playerAgility);
        this.fightableInterface = new FightBySwordStrategy();
        welcomeMessage();
    }

    public void welcomeMessage() {
        System.out.println("Welcome, " + this.playerName);
        System.out.println(currentLocation.getDescription());
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public String getName() {
        return playerName;
    }

    public int getStrenght() {
        return stats.getStrenght();
    }

    public int getHealth() {
        return stats.getHealth();
    }

    public void setHealth(int playerHealth) {
        stats.setHealth(playerHealth);
    }
    
    public int getAgility() {
        return stats.getAgility();
    }

    public String getLocationDescription() {
        return currentLocation.getDescription();
    }

    public boolean ifMonsterNearby(String name) {
        return this.currentLocation.isMonsterExists(name);
    }

    public Npc prepareMonster(String name) {
        return getCurrentLocation().getMonster(name);
    }

    public String getStatistics() {
        return "Name: " + this.playerName + "\n" + stats.toString();
    }


    public void setWeapon(FightableStrategy fightinhWay) {
        this.fightableInterface = fightinhWay;
    }

    public boolean move(Direction direction) {
        Location nextLocation = this.currentLocation.getNextLocation(direction);
        if (nextLocation != null) {
            this.currentLocation = nextLocation;
            return true;
        } else {
            return false;
        }
    }

    public int calculateHitStrenght() {
        return fightableInterface.fight(this.getStrenght(), this.playerName);
    }

    public String getMonsterStatistics(String NpcName) {
        return this.currentLocation.getSingleNpcStats(NpcName);
    }
    
    public boolean isAlive(){
        return stats.getHealth() > 0;
    }

}
