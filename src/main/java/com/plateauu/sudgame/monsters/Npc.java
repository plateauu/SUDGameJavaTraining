package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.Fightable;

public abstract class Npc {
	private String npcName;
	private int npcHealth;
	private int npcStrenght;
	Fightable fightableInterface;

	public Npc(String name, int npcHealth, int npcStrenght) {
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

	public void setNpcHealth(int npcHealth) {
		this.npcHealth = npcHealth;
	}

	public int getStrenght() {
		return npcStrenght;
	}

	public int takeAShot() {
		return fightableInterface.fight(this.npcStrenght, this.npcName);
	}

	public void setWeapon(Fightable fightinhWay) {
		this.fightableInterface = fightinhWay;
	}

	public static Npc createMonster(Monsters monster, String name, int npcHealth, int npcStrenght) {
		switch (monster) {
		case Ogr:
			return new NpcOgr(name, npcHealth, npcStrenght);
		case Ork:
			return new NpcOrk(name, npcHealth, npcStrenght);
		case Cupido:
			return new NpcOrk(name, npcHealth, npcStrenght);
		default:
			return null;
		}
		
	}

    @Override
    public String toString() {
        return this.npcName;
    }
    
    

}
