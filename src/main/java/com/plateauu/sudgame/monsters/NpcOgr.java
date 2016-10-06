package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightBySwordStrategy;

public class NpcOgr extends Npc {


    public NpcOgr(String name, int npcHealth, int npcStrenght, int npcAgility) {
        super(name, npcHealth, npcStrenght, npcAgility, Monsters.Ogr);
        fightableInterface = new FightBySwordStrategy();
    }
}
