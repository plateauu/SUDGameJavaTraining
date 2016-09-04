package SUD_GAME.domain;

public class Player implements Runnable {
	private String playerName;
	private Location currentLocation;
	private int playerHealth;
	private int playerStrenght;

	public Player(String name, Location currentLocation) {
		this.playerName = name;
		this.currentLocation = currentLocation;
		this.playerHealth = 10;
		this.playerStrenght = 5;
		System.out.println("Welcome, " + this.playerName);
		System.out.println(currentLocation.getDescription());
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public String getPlayerName() {
		return playerName;
	}

	public boolean move(Direction direction) {
		Location nextLocation = this.currentLocation.getNextLocation(direction);
		if (nextLocation != null) {
			this.currentLocation = nextLocation;
			return true;
		} else {
			return false;
		}
	}

	public void fightWithMonster(String name) {
		Npc monster = this.currentLocation.getMonster(name);
		boolean playerHits = true;
		boolean activeFight = true;

		System.out.println(this.playerName + " attacked " + monster.getName());

		while (activeFight) {
			if (playerHits) {
				int random = (int) (Math.random() * 4 + 1);
				int hitStrenght = random + this.playerStrenght;

				System.out.println(this.playerName + " hits "
						+ monster.getName() + ". Hit strenght: " + hitStrenght);

				monster.setNpcHealth(monster.getNpcHealth() - hitStrenght);

				activeFight = this.checkIsAlive(monster, this);
				playerHits = false;

			} else {
				int random = (int) (Math.random() * 4 + 1);
				int hitStrenght = random + monster.getNpcStrenght();

				System.out.println(monster.getName() + " hits "
						+ this.playerName + ". Hit strenght: " + hitStrenght);

				this.playerHealth -= hitStrenght;

				if (this.playerHealth > 0) {
					System.out.println(this.playerName + " Health: "
							+ this.playerHealth);
				}

				activeFight = this.checkIsAlive(monster, this);
				playerHits = true;

			}
		}

	}

	private boolean checkIsAlive(Npc monster, Player player) {
		boolean isActive = true;

		if (this.playerHealth <= 0) {
			System.out.println(monster.getName() + " has won a battle");
			System.out.println(this.playerName + " has fail");
			isActive = false;
		}

		if (monster.getNpcHealth() <= 0) {
			System.out.println(this.playerName + " has won a fight with "
					+ monster.getName());
			System.out.println(monster.getName() + " has died");
			this.currentLocation.removeMonster(monster);
			isActive = false;
		}

		return isActive;

	}

}
