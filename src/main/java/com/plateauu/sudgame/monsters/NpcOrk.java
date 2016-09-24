package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightByHandStrategy;

public class NpcOrk extends Npc {

    private static final String NPCRACE = "Ork";

    public NpcOrk(String name, int npcHealth, int npcStrenght, int npcAgility) {
        super(name, npcHealth, npcStrenght, npcAgility);
        fightableInterface = new FightByHandStrategy();
        this.npcRace = NPCRACE;
    }

}
