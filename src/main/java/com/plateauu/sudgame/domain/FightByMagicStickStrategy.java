package com.plateauu.sudgame.domain;

import java.util.Random;

public class FightByMagicStickStrategy implements FightableStrategy {

	@Override
	public int fight(int strenght, String name) {
		Random r = new Random();
		int damage = strenght + r.nextInt(2); 
		System.out.println(name + " use Magic Stick and takes "  + damage + " damages");
		return damage;
	}

}
