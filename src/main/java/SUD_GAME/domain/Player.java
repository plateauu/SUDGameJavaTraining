package SUD_GAME.domain;

import java.util.Random;

public class Player {
	private String playerName;
	private Location currentLocation;
	private int playerHealth;
	private int playerStrenght;

	public Player(String name, Location currentLocation) {
		this.playerName = name;
		this.currentLocation = currentLocation;
		this.playerHealth = 10000;
		this.playerStrenght = 2;
		System.out.println("Welcome, " + this.playerName);
		System.out.println(currentLocation.getDescription());
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}
	
	public String getLocationDescription(){
		return currentLocation.getDescription();
		
	}
	
	public boolean ifMonsterNearby(String name){
		return this.currentLocation.isMonsterExists(name);
	}
	
	public Npc prepareMonster(String name){
		return getCurrentLocation().getMonster(name);
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getPlayerStrenght() {
		return playerStrenght;
	}

	public int getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
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
	

	public int takeAShot() {
		Random r = new Random();
		return this.playerStrenght + r.nextInt(4);
	}

}
