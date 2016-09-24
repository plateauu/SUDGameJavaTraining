package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightByMagicStickStrategy;

public class NpcCupido extends Npc {

    private static final String NPCRACE = "Cupido";
    
    public NpcCupido(String name, int npcHealth, int npcStrenght, int npcAgility) {
        super(name, npcHealth, npcStrenght, npcAgility);
        fightableInterface = new FightByMagicStickStrategy();
        this.npcRace = NPCRACE;
    }

}
