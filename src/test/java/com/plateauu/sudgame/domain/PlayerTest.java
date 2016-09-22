package com.plateauu.sudgame.domain;

import com.plateauu.sudgame.monsters.Npc;
import com.plateauu.sudgame.monsters.NpcOgr;
import com.plateauu.sudgame.monsters.NpcOrk;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    Location location;
    Npc ork;
    Npc bat;
    Location mordor;
    Location shire;

    @Before
    public void initTest() {
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
    public void testMove() {
        Player player = new Player("Plateauu", shire);
        Location expextedLocation = mordor;
        player.move(Direction.N);
        Location actualLocation = player.getCurrentLocation();
        Assert.assertEquals("Move test", expextedLocation, actualLocation);

    }

}
