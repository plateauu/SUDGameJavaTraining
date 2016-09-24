package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightBySwordStrategy;

public class NpcOgr extends Npc {

    private static final String NPCRACE = "Ogr";

    public NpcOgr(String name, int npcHealth, int npcStrenght, int npcAgility) {
        super(name, npcHealth, npcStrenght, npcAgility);
        fightableInterface = new FightBySwordStrategy();
        this.npcRace = NPCRACE;
    }
}
