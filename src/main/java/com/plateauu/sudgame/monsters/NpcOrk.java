package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightByHandStrategy;

public class NpcOrk extends Npc {


    public NpcOrk(String name, int npcHealth, int npcStrenght, int npcAgility) {
        super(name, npcHealth, npcStrenght, npcAgility, Monsters.Ork);
        fightableInterface = new FightByHandStrategy();
    }

}
