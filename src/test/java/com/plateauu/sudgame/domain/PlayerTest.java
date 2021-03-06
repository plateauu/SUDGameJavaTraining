package com.plateauu.sudgame.domain;

import com.plateauu.sudgame.monsters.ConversationScript;
import com.plateauu.sudgame.monsters.Npc;
import com.plateauu.sudgame.monsters.NpcOgr;
import com.plateauu.sudgame.monsters.NpcOrk;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
        shire.addLocation(Direction.N, mordor);
        mordor.addLocation(Direction.S, shire);
        ConversationScript cs = new ConversationScript(ConversationScript.DEFAULT);
        ork = new NpcOgr("Ork", 10, 3, 2, cs);
        bat = new NpcOrk("Batman", 10, 3, 1, cs);
        mordor.addMonster(ork);
        mordor.addMonster(bat);
    }

    @Test
    public void testPlayerMove() {
        Player player = new Player("Plateauu", shire);
        Location expextedLocation = mordor;
        player.move(Direction.N);
        Location actualLocation = player.getCurrentLocation();
        assertEquals("Move test", expextedLocation, actualLocation);

    }
    
    @Test
    public void testGetPlayerStatistics(){
        Player player = new Player("PLateauu", shire, 20, 10);
        player.setHealth(100);
        String actualString = player.getStatistics();
        String expectedString = "Name: PLateauu\nHealth: 100\nStrenght: 20\nAgility: 10";   
        assertEquals("Statistics test", expectedString, actualString);
        
    }

}
