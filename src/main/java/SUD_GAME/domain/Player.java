package SUD_GAME.domain;

public class Player {
	private String playerName;
	private Location currentLocation;

	public Player(String name, Location currentLocation) {
		this.playerName = name;
		this.currentLocation = currentLocation;
		System.out.println("Welcome, " + name);
		System.out.println(currentLocation.getDescription());
	}

	public Location getCurrentLocation() {
		return currentLocation;
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

}
