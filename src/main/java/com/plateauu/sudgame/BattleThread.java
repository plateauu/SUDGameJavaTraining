package com.plateauu.sudgame;

import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;

public class BattleThread implements Runnable {
	private Npc monster;
	private Player player;
	private boolean isActive = true;
	private boolean playerHits = true;

	public BattleThread(Npc monster, Player player) {
		this.monster = monster;
		this.player = player;

	}

	@Override
	public void run() {
		System.out.println(player.getPlayerName() + " attacked " + monster.getName());
		while (isActive) {
			fightWithMonster(monster, player);
		}
	}

	public void fightWithMonster(Npc monster, Player player) {
		try {
			if (playerHits) {
				int hitStrenght = player.takeAShot();
				damagaTaken(monster, player, hitStrenght);
				isActive = this.checkIsAlive(monster, player);
				playerHits = false;
			} else {
				int hitStrenght = monster.takeAShot();
				damagaTaken(monster, player, hitStrenght);
				showHealth(player);
				isActive = this.checkIsAlive(monster, player);
				playerHits = true;
			}
			Thread.sleep(2000);
		} catch (Exception e) {

		}

	}

	public void damagaTaken(Npc monster, Player player, int hitStrenght) {

		if (playerHits) {
			monster.setNpcHealth(monster.getNpcHealth() - hitStrenght);
		} else {
			player.setPlayerHealth(player.getPlayerHealth() - hitStrenght);
		}
	}

	public void showHealth(Player player) {
		if (player.getPlayerHealth() > 0) {
			System.out.println(player.getPlayerName() + " Health: " + player.getPlayerHealth());
		}
	}

	private boolean checkIsAlive(Npc monster, Player player) {
		boolean isAlive = true;

		if (player.getPlayerHealth() <= 0) {
			System.out.println(monster.getName() + " has won a battle");
			System.out.println(player.getPlayerName() + " has fail");
			isAlive = false;
			isActive = false;
			
		}

		if (monster.getNpcHealth() <= 0) {
			System.out.println(player.getPlayerName() + " has won a fight with " + monster.getName());
			System.out.println(monster.getName() + " has died");
			player.getCurrentLocation().removeMonster(monster);
			isAlive = false;
			isActive = false;
		}

		return isAlive;

	}

	public void setDeactive() {
		this.isActive = false;
	}

}
