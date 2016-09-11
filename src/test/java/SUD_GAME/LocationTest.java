package SUD_GAME;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;

import SUD_GAME.domain.Direction;
import SUD_GAME.domain.Location;
import SUD_GAME.domain.Npc;

public class LocationTest {

	@Test
	public void testGetShortDescription() {
		Location location = new Location("shire", "long description shire");
		String shortDescription = location.getShortDescription();
		assertEquals("shire", shortDescription);
	}

	@Test
	public void testGetDescription() {
		Location location = new Location("shire", "long description shire");
		String description = location.getDescription();
		assertNotNull(description);
	}

	@Test
	public void testIsDescription() {
		Location location = new Location("shire", "long description shire");
		String description = location.getDescription();
		assertTrue(description.contains("shire"));
	}

	@Test
	public void testIsMonsterExists() {
		Location location = new Location("shire", "long description shire");
		String name = "Ork";
		Npc ork = new Npc(name, 10, 3);
		location.addMonster(ork);
		assertTrue(location.isMonsterExists(name));

	}

	@Test
	public void testAddMonster(){
		Location location = new Location("shire", "long description shire");
		String name = "Ork";
		Npc ork = new Npc(name, 10, 3);
		location.addMonster(ork);
		assertThat(location.getMonsterList(), containsInAnyOrder(hasProperty("name", is("Ork"))));
	}
	
	@Test
	public void testGetMonsterString(){
		Location location = new Location("shire", "long description shire");
		Npc ork = new Npc("Ork", 10, 3);
		Npc bat = new Npc("Batman", 10, 3);
		location.addMonster(ork);
		location.addMonster(bat);
		assertEquals(null, "Ork, Batman, ", location.getMonsterString());
	}
	
	@Test
	public void testExitsMap(){
		Location shire = new Location("shire", "long description shire");
		Location mordor = new Location("mordor", "long description mordor");
		mordor.addLocation(Direction.S, shire);
		shire.addLocation(Direction.N, mordor);
		
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

