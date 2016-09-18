package com.plateauu.sudgame;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Location;
import com.plateauu.sudgame.monsters.Monsters;
import com.plateauu.sudgame.monsters.Npc;

public class GameCreatorTest {

	@Test
	public void testAddGameNpc() {
		GameCreator game = new GameCreator();
		game.addGameNpc("Marcin", 13, 3, "Shire", Monsters.Ork);
		List<Npc> list = game.getGameNpc();
		
		int index = -1;
		for (Npc npc:list){
			if(npc.getName().equals("Marcin")){
				index = list.indexOf(npc);
				break;
			}
		}
		assertTrue(list.get(index).getName().equals("Marcin"));
	}

	@Test
	public void testAddExits() {
		GameCreator game = new GameCreator();
		game.addExits("Shire", "New York", Direction.U);
		assertTrue(game.getGameLocation("Shire").getExtisLists().containsKey(Direction.U));

	}

	@Test
	public void testAddGameLocation() {
		GameCreator game = new GameCreator();
		game.addGameLocation("shire", "shire is such a beautiful place");
		List<Location> list = game.getGameLocations();
		assertTrue(list.get(4).getShortDescription().equals("shire"));
	}

}
