package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.Statistics;
import com.plateauu.sudgame.domain.FightableStrategy;

public abstract class Npc {

    private final String npcName;
    String npcDescription;
    private final Monsters npcRace;
    private final Statistics stats;
    FightableStrategy fightableInterface;

    Npc(String name, int health, int strength, Monsters race) {
        this.npcName = name;
        this.npcRace = race;
        stats = new Statistics(health, strength, 10);
    }

    Npc(String name, int health, int strenght, int agility, Monsters race) {
        this.npcName = name;
        this.npcRace = race;
        stats = new Statistics(health, strenght, agility);
    }

    public String getName() {
        return npcName;
    }

    public int getHealth() {
        return stats.getHealth();
    }

    public void setHealth(int npcHealth) {
        stats.setHealth(npcHealth);
    }

    public int getStrength() {
        return stats.getStrenght();
    }

    public int getAgility() {
        return stats.getAgility();
    }

    public String getDescprition() {
        return this.npcName + " (" + this.npcRace.toString() + ") - " + this.npcDescription;
    }

    public int calculateHitStrenght() {
        return fightableInterface.fight(this.getStrength(), this.npcName);
    }

    public void setWeapon(FightableStrategy chosenWeapon) {
        this.fightableInterface = chosenWeapon;
    }

    //todo add descprition
    public String getStatistics() {
        return "Name: " + this.npcName
                + "\nRace: " + this.npcRace
                + "\n" + stats.toString();
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

    public boolean isAlive() {
        return stats.getHealth() > 0;
    }

}
