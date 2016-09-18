package com.plateauu.sudgame.domain;

import java.util.Random;

public class FightByHand implements Fightable {

	@Override
	public int fight(int strenght, String name) {
		Random r = new Random();
		int damage =strenght + r.nextInt(4); 
		System.out.println(name + " hits by Hand and takes " + damage + " damages" );
		return damage;
		
	}

}
