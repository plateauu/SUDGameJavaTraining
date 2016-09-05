package SUD_GAME.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Location {
	private String shortDescription;
	private String longDescription;
	private Map<Direction, Location> exits;
	private ArrayList<Npc> monsters;

	public Location(String shortDescription, String longDescription) {
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.exits = new HashMap<>();
		this.monsters = new ArrayList<>();
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public String getDescription() {
		return "Now, you are at: " + this.shortDescription + "\n"
				+ this.longDescription + "\n" + "Visible exits: "
				+ getExitString() + "\n" + "Encountered monsters: "
				+ getMonsterString();

	}

	private String getExitString() {
		StringBuilder exitString = new StringBuilder();
	
		for (Direction direction : exits.keySet()) {
			exitString.append(direction.getDirectionDescription());
			exitString.append(", ");
		}
		return exitString.toString();
	}

	private String getMonsterString() {

		if (monsters.size() == 0) {
			return "None";
		} else {

			StringBuilder monsterString = new StringBuilder();

			for (Npc monster : monsters) {
				monsterString.append(monster.getName());
				monsterString.append(", ");
			}
			return monsterString.toString();
		}
	}

	
	// nie wiem czy tutaj za bardzo nie kombinuję, ale chciałem żeby każda funkcja była jak najmniejsza *(wykonywała jedno zadanie), 
	//a przy okazji mogła byc wykorzystywana wielokrotnie
	
	public boolean monsterExists(String name) {
		boolean isPresent = false;

		for (Npc monster : monsters) {
			if (monster.getName().equals(name)) {
				isPresent = true;
				break;
			}
		}
		return isPresent;
	}

	public int getMonsterIndex(String name) {

		int indexOfMonster = -1;

		for (Npc monster : monsters) {
			if (monster.getName().equals(name)) {
				indexOfMonster = monsters.indexOf(monster);
			}
		}
		return indexOfMonster;
	}
	
	public Npc getMonster(String name){
		int indexOfMonster;
		indexOfMonster = getMonsterIndex(name);
		return monsters.get(indexOfMonster);
	}
	
	// koniec wahania

	public void addLocation(Direction exit, Location loc) {
		this.exits.put(exit, loc);
	}

	public void addMonster(Npc monster) {
		this.monsters.add(monster);
	}

	public void removeMonster(Npc monster) {
		this.monsters.remove(monster);
	}
	
	public Location getNextLocation(Direction direction) {
		boolean exists = this.exits.containsKey(direction);
		if (exists) {
			return this.exits.get(direction);
		} else {
			return null;
		}

	}


}
