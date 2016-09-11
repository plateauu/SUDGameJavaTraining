package com.plateauu.sudgame;

import java.util.ArrayList;
import java.util.List;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Location;
import com.plateauu.sudgame.domain.Npc;

public class GameCreator {

	private Location startLocation;
	private List<Location> gameLocations;
	private List<Npc> gameNpc;

	public GameCreator() {

		this.gameLocations = new ArrayList<Location>();
		this.gameNpc = new ArrayList<Npc>();

		addGameLocation("Shire", "Beautful forest, a land where sun never setting down");
		addGameLocation("Mordor", "It is a land where dark lord rule since ever" + "\n"
				+ "and dark of the darkness deamons and orks live there");
		addGameLocation("Graceland", "Mysterious count. No one has more information about it");
		addGameLocation("New York", " The city that never sleeps");

		addExits("Shire", "Mordor", Direction.N);
		addExits("Shire", "Graceland", Direction.S);
		addExits("Mordor", "Shire", Direction.S);
		addExits("Graceland", "Shire", Direction.N);
		addExits("Graceland", "New York", Direction.N);
		addExits("New York", "Graceland", Direction.W);

		addGameNpc("Zygmund", 3, 2, "Mordor");
		addGameNpc("Alfred", 20, 5, "Mordor");
		addGameNpc("Cinkciarz", 20, 5, "New York");
		addGameNpc("Zenon", 20, 5, "Mordor");
		addGameNpc("Max", 1, 1, "Graceland");
		addGameNpc("Mini", 1, 1, "Graceland");
		addGameNpc("Lenek", 1, 1, "Graceland");

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

	public void addGameNpc(String name, int npcHealth, int npcStrenght, String location) {
		gameNpc.add(new Npc(name, npcHealth, npcStrenght));
		Npc monster = getGameNpc(name);
		if (monster != null) {
			getGameLocation(location).addMonster(monster);
		}
	}

	
	public int getGameNpcIndex(String name) {
		int indexOfMonster = - 1;
		for(Npc monster : this.gameNpc){
			if(monster.getName() == name){
				indexOfMonster = gameNpc.indexOf(monster);
				break;
			}
		}
		
		return indexOfMonster;
	}
	
	public Npc getGameNpc(String name) {
		int index = getGameNpcIndex(name);
		if (index != -1) {
			return gameNpc.get(index);
		} else {
			return null;
		}

	}

	public void addExits(String startLocation, String endLocation, Direction direction) {
		if (getGameLocation(startLocation) != null && getGameLocation(endLocation) != null) {
			getGameLocation(startLocation).addLocation(direction, getGameLocation(endLocation));
		}
	}
}
