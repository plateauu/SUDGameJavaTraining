package com.plateauu.sudgame.domain;

import java.util.Random;

import com.plateauu.sudgame.monsters.Npc;

public class Player {

    private final String playerName;
    private Location currentLocation;
    private int playerHealth;
    private final int playerStrenght;
    private Fightable fightableInterface;

    public Player(String name, Location currentLocation) {
        this.playerName = name;
        this.currentLocation = currentLocation;
        this.playerHealth = 10000;
        this.playerStrenght = 2;
        this.fightableInterface = new FightBySword();
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

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
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

    public void setWeapon(Fightable fightinhWay) {
        this.fightableInterface = fightinhWay;
    }

}
