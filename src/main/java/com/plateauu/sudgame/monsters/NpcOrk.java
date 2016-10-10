package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightByHandStrategy;

public class NpcOrk extends Npc {

    private final String DESCPRITION = "Strong and powerfull monster. Warning! it eats people!";

    public NpcOrk(String name, int npcHealth, int npcStrenght, int npcAgility) {
        super(name, npcHealth, npcStrenght, npcAgility, Monsters.Ork);
        this.fightableInterface = new FightByHandStrategy();
        this.npcDescription = DESCPRITION;
    }

}
