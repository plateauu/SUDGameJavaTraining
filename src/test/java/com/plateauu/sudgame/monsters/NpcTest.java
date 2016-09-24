package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Location;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NpcTest {

    private Location shire;
    private Location mordor;
    private NpcOgr ork;
    private NpcOrk bat;

    public NpcTest() {
    }

    @Before
    public void setUp() {
        shire = new Location("shire", "long description shire");
        mordor = new Location("mordor", "long description mordor");
        shire.addLocation(Direction.N, mordor);
        mordor.addLocation(Direction.S, shire);
        ork = new NpcOgr("Ogr", 10, 3, 2);
        bat = new NpcOrk("Batman", 10, 3, 1);
        mordor.addMonster(ork);
        mordor.addMonster(bat);
    }

    @Test
    public void testGetNpcStatistics() {
        ork.setNpcHealth(100);
        ork.setNpcStrenght(20);
        ork.setNpcAgility(10);
        String actualString = ork.getNpcStatistics();
        String expectedString = "Name: Ogr\nRace: Ogr\nHealth: 100\nStrenght: 20\nAgility: 10";
        assertEquals("Statistics test", expectedString, actualString);

    }

}
