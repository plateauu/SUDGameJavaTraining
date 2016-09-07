package SUD_GAME.domain;

import java.util.Random;

public class Npc {
	private String npcName;
	private int npcHealth;
	private int npcStrenght;
	
	public Npc(String name, int npcHealth, int npcStrenght){
		this.npcName = name;
		this.npcHealth = npcHealth;
		this.npcStrenght = npcStrenght;
	}

	public String getName() {
		return npcName;
	}
	
	public int getNpcHealth() {
		return npcHealth;
	}
	
	public void setNpcHealth(int npcHealth){
		this.npcHealth = npcHealth;
	}

	public int getNpcStrenght(){
		return npcStrenght;
	}
	
	public int takeAShot(){
		Random r = new Random();
		return this.npcStrenght + r.nextInt(4);
				
	}
	
}
