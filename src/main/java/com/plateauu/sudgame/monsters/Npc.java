package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.Statistics;
import com.plateauu.sudgame.domain.FightableStrategy;
import com.plateauu.sudgame.domain.Player;

public abstract class Npc {

    private final String npcName;
    private final Monsters npcRace;
    private final Statistics npcStats;
    String npcDescription;

    FightableStrategy fightableInterface;
    private ConversationScript conversationScript;
    boolean isTalkative;

    Npc(String name, int health, int strength, Monsters race, ConversationScript conversationScript) {
        this.npcName = name;
        this.npcRace = race;
        this.conversationScript = conversationScript;
        npcStats = new Statistics(health, strength, 10);
        isTalkative = true;
    }

    Npc(String name, int health, int strength, Monsters race) {
        this.npcName = name;
        this.npcRace = race;
        conversationScript = new ConversationScript(conversationScript.DEFAULT);
        npcStats = new Statistics(health, strength, 10);
        isTalkative = true;
    }

    Npc(String name, int health, int strenght, int agility, Monsters race, ConversationScript conversationScript) {
        this.npcName = name;
        this.npcRace = race;
        this.conversationScript = conversationScript;
        npcStats = new Statistics(health, strenght, agility);
        isTalkative = true;
    }

    Npc(String name, int health, int strenght, int agility, Monsters race) {
        this.npcName = name;
        this.npcRace = race;
        this.conversationScript = new ConversationScript(conversationScript.DEFAULT);
        npcStats = new Statistics(health, strenght, agility);
        isTalkative = true;
    }


    Npc(String name, int health, int strenght, int agility, Monsters race, ConversationScript conversationScript, boolean isTalkative) {
        this.npcName = name;
        this.npcRace = race;
        this.conversationScript = conversationScript;
        npcStats = new Statistics(health, strenght, agility);
        this.isTalkative = isTalkative;
    }

    Npc(String name, int health, int strenght, int agility, Monsters race, boolean isTalkative) {
        this.npcName = name;
        this.npcRace = race;
        this.conversationScript = new ConversationScript(conversationScript.DEFAULT);
        npcStats = new Statistics(health, strenght, agility);
        this.isTalkative = isTalkative;
    }



    public String getName() {
        return npcName;
    }

    public int getHealth() {
        return npcStats.getHealth();
    }

    public void setHealth(int npcHealth) {
        npcStats.setHealth(npcHealth);
    }

    public int getStrength() {
        return npcStats.getStrenght();
    }

    public int getAgility() {
        return npcStats.getAgility();
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
                + "\n" + npcStats.toString();
    }

    public static Npc createMonster(Monsters monster, String name, int npcHealth, int npcStrenght, int npcAgility, ConversationScript cs) {
        switch (monster) {
            case Ogr:
                return new NpcOgr(name, npcHealth, npcStrenght, npcAgility, cs);
            case Ork:
                return new NpcOrk(name, npcHealth, npcStrenght, npcAgility, cs);
            case Cupido:
                return new NpcCupido(name, npcHealth, npcStrenght, npcAgility, cs);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return this.npcName;
    }

    public boolean isAlive() {
        return npcStats.getHealth() > 0;
    }

    public abstract String makeConversation(Player player);

    public ConversationScript getConversationScript() {
        return conversationScript;
    }

    public boolean isTalkative() {
        return isTalkative;
    }




}
