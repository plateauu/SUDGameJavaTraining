package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightByMagicStick;

public class NpcCupido extends Npc {

	public NpcCupido(String name, int npcHealth, int npcStrenght) {
		super(name, npcHealth, npcStrenght);
		fightableInterface = new FightByMagicStick();
	}

}
