package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightBySword;

public class NpcOgr extends Npc {
	
	public NpcOgr(String name, int npcHealth, int npcStrenght) {
		super(name, npcHealth, npcStrenght);
		fightableInterface = new FightBySword();
	}

}
