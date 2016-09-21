package com.plateauu.sudgame;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Before;
import org.junit.Test;

import com.plateauu.sudgame.domain.*;
import com.plateauu.sudgame.monsters.Npc;
import com.plateauu.sudgame.monsters.NpcOgr;
import com.plateauu.sudgame.monsters.NpcOrk;

public class LocationTest {
	
	Location location;
	Npc ork;
	Npc bat;
	Location mordor;
	Location shire;

	@Before
	public void initTest(){
		shire = new Location("shire", "long description shire");
		mordor = new Location("mordor", "long description mordor");
		mordor.addLocation(Direction.S, shire);
		shire.addLocation(Direction.N, mordor);
		ork = new NpcOgr("Ork", 10, 3);
		bat = new NpcOrk("Batman", 10, 3);
		mordor.addMonster(ork);
		mordor.addMonster(bat);
	}
	
	@Test
	public void testGetShortDescription() {
		String shortDescription = shire.getShortDescription();
		assertEquals("shire", shortDescription);
	}

	@Test
	public void testGetDescription() {
		String actualDescription = shire.getDescription();
		String excpectedDescription = "Now, you are at: shire\nlong description shire\nVisible exits: North, \nEncountered monsters: None";
		assertNotNull("Checking if this not null description", actualDescription);
		assertEquals("compare string", excpectedDescription, actualDescription);
	}

	@Test
	public void testIsDescription() {
		String description = shire.getDescription();
		assertTrue("Checking if description is correct",description.contains("shire"));
	}

	@Test
	public void testIsMonsterExists() {
		String expectedName = "Ork";
		assertTrue("isMontersExits test",mordor.isMonsterExists(expectedName));

	}

	@Test
	public void testGetMonsterString(){
		assertEquals("Monster test's", "Ork, Batman, ", mordor.getMonsterString());
		
	}
	
	@Test
	public void testExitsMap(){
		Map<Direction, Location> map = mordor.getExtisLists();
		Map<Direction, Location> expectedMap = new HashMap<>();
		expectedMap.put(Direction.S, shire);
		assertThat(map, is(expectedMap));
		assertThat(map.size(), is(1));
		assertThat(map, IsMapContaining.hasEntry(Direction.S, shire));
		assertThat(map, IsMapContaining.hasKey(Direction.S));
		assertThat(map, IsMapContaining.hasValue(shire));
		
	}
}

