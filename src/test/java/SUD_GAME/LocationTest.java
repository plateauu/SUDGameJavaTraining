package SUD_GAME;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import SUD_GAME.domain.Location;

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

}
