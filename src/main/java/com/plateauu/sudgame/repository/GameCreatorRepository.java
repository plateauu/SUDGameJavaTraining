package com.plateauu.sudgame.repository;

import java.util.ArrayList;
import java.util.List;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Location;
import com.plateauu.sudgame.monsters.Monsters;
import com.plateauu.sudgame.monsters.Npc;

public class GameCreatorRepository {

	private Location startLocation;
	private final List<Location> gameLocations;
	private final List<Npc> gameNpc;

	public GameCreatorRepository() {
		this.gameLocations = new ArrayList<>();
		this.gameNpc = new ArrayList<>();

		addGameLocation("Shire", "Beautful forest, a land where sun never setting down");
		addGameLocation("Mordor", "It is a land where dark lord rule since ever" + "\n"
				+ "and dark of the darkness deamons and orks live there");
		addGameLocation("Graceland", "Mysterious count. No one has more information about it");
		addGameLocation("New York", " The city that never sleeps");

		addExits("Shire", "Mordor", Direction.N);
		addExits("Mordor", "Shire", Direction.S);
		addExits("Shire", "Graceland", Direction.S);
		addExits("Graceland", "Shire", Direction.N);
		addExits("Graceland", "New York", Direction.E);
		addExits("New York", "Graceland", Direction.W);

		addGameNpc("Zygmund", 3, 2, "Mordor", Monsters.Ogr);
		addGameNpc("Alfred", 20, 5, "Mordor", Monsters.Ork);
		addGameNpc("Cinkciarz", 20, 5, "New York", Monsters.Golum);
		addGameNpc("Zenon", 20, 5, "Mordor", Monsters.Cupido);
		addGameNpc("Max", 1, 1, "Graceland", Monsters.Cupido);
		addGameNpc("Mini", 1, 1, "Graceland", Monsters.Cupido);
		addGameNpc("Lenek", 1, 1, "Graceland", Monsters.Cupido);

	}
	

	public List<Location> getGameLocations() {
		return gameLocations;
	}

	public List<Npc> getGameNpc() {
		return gameNpc;
	}

	public Location getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String name) {
		int index = getGameLocationIndex(name);
		if (index != -1) {
			this.startLocation = gameLocations.get(index);
		} else {
			
			this.startLocation = getGameLocation("Shire");
		}
	}

	public void addGameLocation(String name, String description) {
		gameLocations.add(new Location(name, description));
	}

	public int getGameLocationIndex(String name) {
		int indexOfLocation = - 1;
		for(Location location : this.gameLocations){
			if(location.getShortDescription() == name){
				indexOfLocation = gameLocations.indexOf(location);
				break;
			}
		}
		
		return indexOfLocation;
	}
	
	
	public Location getGameLocation(String name) {
		int index = getGameLocationIndex(name);
		if (index != -1) {
			return gameLocations.get(index);
		} else {
			return null;
		}
	}

	
	public void addGameNpc(String name, int npcHealth, int npcStrenght, String location, Monsters monsterType) {
		Npc monster = Npc.createMonster(monsterType, name, npcHealth, npcStrenght); 
		if (monster != null) {
			gameNpc.add(monster);
			getGameLocation(location).addMonster(monster);
		}
	}

	public void addExits(String startLocation, String endLocation, Direction direction) {
		if (getGameLocation(startLocation) != null && getGameLocation(endLocation) != null) {
			getGameLocation(startLocation).addLocation(direction, getGameLocation(endLocation));
		}
	}
}
