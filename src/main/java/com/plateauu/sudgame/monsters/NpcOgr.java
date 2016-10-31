package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightBySwordStrategy;

public class NpcOgr extends Npc {

    private final String DESCRIPTION = "Big monster, who is strong and bad looking guy";

    public NpcOgr(String name, int npcHealth, int nbcStrength, int npcAgility) {
        super(name, npcHealth, nbcStrength, npcAgility, Monsters.Ogr);
        this.fightableInterface = new FightBySwordStrategy();
        this.npcDescription = DESCRIPTION;
        
    }
}
