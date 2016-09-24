package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightableStrategy;

public abstract class Npc {

    private String npcName;
    String npcRace;
    private int npcHealth;
    private int npcStrenght;
    private int npcAgility;
    FightableStrategy fightableInterface;

    public Npc(String name, int npcHealth, int npcStrenght, int npcAgility) {
        this.npcName = name;
        this.npcHealth = npcHealth;
        this.npcStrenght = npcStrenght;
        this.npcAgility = npcAgility;
    }

    public String getName() {
        return npcName;
    }

    public int getNpcHealth() {
        return npcHealth;
    }

    public void setNpcHealth(int npcHealth) {
        this.npcHealth = npcHealth;
    }

    public int getStrenght() {
        return npcStrenght;
    }

    public void setNpcStrenght(int npcStrenght) {
        this.npcStrenght = npcStrenght;
    }

    public int getNpcAgility() {
        return npcAgility;
    }

    public void setNpcAgility(int npcAgility) {
        this.npcAgility = npcAgility;
    }
    
    public int takeAShot() {
        return fightableInterface.fight(this.npcStrenght, this.npcName);
    }

    public void setWeapon(FightableStrategy chosenWeapon) {
        this.fightableInterface = chosenWeapon;
    }
    
    public String getNpcStatistics(){
         return "Name: " + this.npcName
                + "\nRace: " + this.npcRace
                + "\nHealth: " + this.npcHealth 
                + "\nStrenght: " + this.npcStrenght 
                + "\nAgility: " + this.npcAgility;        
    }

    public static Npc createMonster(Monsters monster, String name, int npcHealth, int npcStrenght, int npcAgility) {
        switch (monster) {
            case Ogr:
                return new NpcOgr(name, npcHealth, npcStrenght, npcAgility);
            case Ork:
                return new NpcOrk(name, npcHealth, npcStrenght, npcAgility);
            case Cupido:
                return new NpcCupido(name, npcHealth, npcStrenght, npcAgility);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return this.npcName;
    }

}
