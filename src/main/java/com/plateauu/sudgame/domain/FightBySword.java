package com.plateauu.sudgame.domain;

import java.util.Random;

public class FightBySword implements Fightable {

	@Override
	public int fight(int strenght, String name) {
		Random r = new Random();
		int damage = (int) (strenght * 1.2 + r.nextInt(6)); 
		System.out.println(name + " hits by Sword and takes " + damage + " damages" );
		return damage;
	}

}
