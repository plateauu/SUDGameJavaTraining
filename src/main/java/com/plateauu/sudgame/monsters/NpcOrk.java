package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightByHand;

public class NpcOrk extends Npc{

	public NpcOrk(String name, int npcHealth, int npcStrenght) {
		super(name, npcHealth, npcStrenght);
		fightableInterface = new FightByHand();
	}

	
	
}
