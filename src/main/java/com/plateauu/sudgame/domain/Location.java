package com.plateauu.sudgame.domain;

import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plateauu.sudgame.monsters.Npc;

public class Location {

    private final String shortDescription;
    private final String longDescription;
    private final Map<Direction, Location> exits;
    private final List<Npc> monsters;

    public Location(String shortDescription, String longDescription) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.exits = new HashMap<>();
        this.monsters = new ArrayList<>();
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getDescription() {
        return "Now, you are at: " + this.shortDescription + "\n"
                + this.longDescription + "\n" + "Visible exits: "
                + getExitString() + "\n" + "Encountered monsters: "
                + getMonsterString();
    }

    private String getExitString() {
        List<Direction> locationsExits = new ArrayList<>(exits.keySet());
        Collections.sort(locationsExits);
        return Joiner.on(", ").join(locationsExits);
    }
    
    public String getMonsterString() {
        if (monsters.isEmpty()) {
            return "None";
        } else {
            return Joiner.on(", ").join(monsters);
        }
    }

    public boolean isMonsterExists(String name) {
        boolean isPresent = false;

        for (Npc monster : monsters) {
            if (monster.getName().equalsIgnoreCase(name)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    public int getMonsterIndex(String name) {

        int indexOfMonster = -1;

        for (Npc monster : monsters) {
            if (monster.getName().equalsIgnoreCase(name)) {
                indexOfMonster = monsters.indexOf(monster);
            }
        }
        return indexOfMonster;
    }

    public Npc getMonster(String name) {

        int indexOfMonster;
        indexOfMonster = getMonsterIndex(name);

        if (indexOfMonster != -1) {
            return monsters.get(indexOfMonster);
        } else {
            return null;
        }
    }

    public void addLocation(Direction exit, Location loc) {
        this.exits.put(exit, loc);
    }

    public void addMonster(Npc monster) {
        this.monsters.add(monster);
    }

    public void removeMonster(Npc monster) {
        this.monsters.remove(monster);
    }

    public Location getNextLocation(Direction direction) {
        boolean exists = this.exits.containsKey(direction);
        if (exists) {
            return this.exits.get(direction);
        } else {
            return null;
        }

    }

    public List<Npc> getMonsterList() {
        return this.monsters;
    }

    public Map<Direction, Location> getExtisLists() {
        return this.exits;
    }
    
    public String getSingleNpcStats(String name){
        boolean monsterExists = this.isMonsterExists(name);
        if(monsterExists){
            Npc monster = getMonster(name);
            return monster.getNpcStatistics();
        } else {
            return "There is no one called " + name + " here";
        }
    }

}
