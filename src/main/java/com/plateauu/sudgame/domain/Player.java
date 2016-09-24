package com.plateauu.sudgame.domain;

import java.util.Random;

import com.plateauu.sudgame.monsters.Npc;

public class Player {

    private final String playerName;
    private Location currentLocation;
    private int playerHealth;
    private int playerStrenght;
    private FightableStrategy fightableInterface;
    private int playerAgility;

    public Player(String name, Location currentLocation) {
        this.playerName = name;
        this.currentLocation = currentLocation;
        this.playerHealth = 10000;
        this.playerStrenght = 2;
        this.playerAgility = 10;
        this.fightableInterface = new FightBySwordStrategy();
        System.out.println("Welcome, " + this.playerName);
        System.out.println(currentLocation.getDescription());
    }

    public Location getCurrentLocation() {
        return currentLocation;
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

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerStrenght() {
        return playerStrenght;
    }

    public void setPlayerStrenght(int playerStrenght) {
        this.playerStrenght = playerStrenght;
    }
    
    public int getPlayerHealth() {
        return playerHealth;
    }

    public int getPlayerAgility() {
        return playerAgility;
    }

    public void setPlayerAgility(int playerAgility) {
        this.playerAgility = playerAgility;
    }
    
    
    
    public String getPlayerStatistics(){
        return "Name: " + this.playerName
                + "\nHealth: " + this.playerHealth 
                + "\nStrenght: " + this.playerStrenght 
                + "\nAgility: " + this.playerAgility;       
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
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

    public int takeAShot() {
        return fightableInterface.fight(this.playerStrenght, this.playerName);
    }

    public String getMonsterStatistics(String NpcName) {
        return this.currentLocation.getSingleNpcStats(NpcName);
    }
    
    


}
