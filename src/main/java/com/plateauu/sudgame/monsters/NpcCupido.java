package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightByMagicStickStrategy;

public class NpcCupido extends Npc {
    
    private final String DESCPRITION = "Small flying monster, who is weakness and full of fear";

    
    
    public NpcCupido(String name, int npcHealth, int npcStrenght, int npcAgility) {
        super(name, npcHealth, npcStrenght, npcAgility, Monsters.Cupido);
        this.fightableInterface = new FightByMagicStickStrategy();
        this.npcDescription = DESCPRITION; 
    }

}
