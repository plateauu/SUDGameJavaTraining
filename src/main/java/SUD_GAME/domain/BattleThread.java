package SUD_GAME.domain;

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
		System.out.println(player.getPlayerName() + " attacked "
				+ monster.getName());
		while (isActive) {
			fightWithMonster(monster, player);
		}
	}

	public void fightWithMonster(Npc monster, Player player) {


		if (playerHits) {
			int random = (int) (Math.random() * 4 + 1);
			int hitStrenght = random + player.getPlayerStrenght();

			System.out.println(player.getPlayerName() + " hits "
					+ monster.getName() + ". Hit strenght: " + hitStrenght);

			monster.setNpcHealth(monster.getNpcHealth() - hitStrenght);

			isActive = this.checkIsAlive(monster, player);
			playerHits = false;

		} else {
			int random = (int) (Math.random() * 4 + 1);
			int hitStrenght = random + monster.getNpcStrenght();

			System.out
					.println(monster.getName() + " hits "
							+ player.getPlayerName() + ". Hit strenght: "
							+ hitStrenght);

			player.setPlayerHealth(player.getPlayerHealth() - hitStrenght);

			if (player.getPlayerHealth() > 0) {
				System.out.println(player.getPlayerName() + " Health: "
						+ player.getPlayerHealth());
			}

			isActive = this.checkIsAlive(monster, player);
			playerHits = true;

		}
		try {
			Thread.sleep(2000);
		} catch (Exception e) {

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
			System.out.println(player.getPlayerName()
					+ " has won a fight with " + monster.getName());
			System.out.println(monster.getName() + " has died");
			player.getCurrentLocation().removeMonster(monster);
			isAlive = false;
			isActive = false;
		}

		return isAlive;

	}
	
	public void setDeactive(){
		this.isActive = false;
	}

}
