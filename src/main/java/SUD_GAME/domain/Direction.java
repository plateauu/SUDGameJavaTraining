package SUD_GAME.domain;

public enum Direction {
	N("North"), S("South"), E("East"), W("West"), U("Up"), D("Down");

	private String directionDescription;

	private Direction(String directionDescription) {
		this.directionDescription = directionDescription;
	}

	public String getDirectionDescription() {
		return directionDescription;
	}

}
