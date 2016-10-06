package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightByMagicStickStrategy;

public class NpcCupido extends Npc {

    
    public NpcCupido(String name, int npcHealth, int npcStrenght, int npcAgility) {
        super(name, npcHealth, npcStrenght, npcAgility, Monsters.Cupido);
        fightableInterface = new FightByMagicStickStrategy();
    }

}
