package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightBySwordStrategy;

public class NpcOgr extends Npc {

    private final String DESCPRITION = "Big monster, who is strong and bad looking guy";

    public NpcOgr(String name, int npcHealth, int npcStrenght, int npcAgility) {
        super(name, npcHealth, npcStrenght, npcAgility, Monsters.Ogr);
        this.fightableInterface = new FightBySwordStrategy();
        this.npcDescription = DESCPRITION;
        
    }
}
