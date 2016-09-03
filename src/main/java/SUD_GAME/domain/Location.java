package SUD_GAME.domain;

import java.util.HashMap;
import java.util.Map;

public class Location {
	private String shortDescription;
	private String longDescription;
	private Map<Direction, Location> exits;
	
	public Location(String shortDescription, String longDescription){
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.exits = new HashMap<>();
	}
	
	public String getShortDescription(){
		return shortDescription;
	}
	
	public String getLongDescription(){
		return longDescription;
	}

	public String getDescription(){
		return  "Now, you are at: " + this.shortDescription + "\n"
				+ this.longDescription + "\n" + "Visible exits: " + getExitString();
	}
	
	
	private String getExitString() {
		 StringBuffer exitString = new StringBuffer();;
		 for (Direction direction : exits.keySet()) {
			 exitString.append(direction.getDirectionDescription());
			 exitString.append(" ");
		 }
		return exitString.toString();
	}

	public void addLocation(Direction exit, Location loc){
		this.exits.put(exit, loc);
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
