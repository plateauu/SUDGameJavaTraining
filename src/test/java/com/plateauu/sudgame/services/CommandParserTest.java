package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Location;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;
import com.plateauu.sudgame.monsters.NpcOgr;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;

public class CommandParserTest {

    private Location shire;
    private Location mordor;


    @Before
    public void initTest() {
        shire = new Location("shire", "long description shire");
        mordor = new Location("mordor", "long description mordor");
        shire.addLocation(Direction.N, mordor);
    }

    @Test
    public void testParserMove() throws InterruptedException {
        Player player = new Player("PLateauu", shire);
        String[] command = {"north"};
        CommandParser parser = new CommandParser();
        CommandParser spyParser = Mockito.spy(parser);
        spyParser.actOnCommand(command, player);
        Mockito.verify(spyParser, times(1)).move(Direction.N, player);
    }

    @Test
    public void testParserKill() throws InterruptedException {
        Player player = new Player("PLateauu", shire);
        Npc ork  = new NpcOgr("Ork", 10, 3 ,2);
        shire.addMonster(ork);
        String[] command = {"kill", "ork"};
        
        CommandParser parser = new CommandParser();
        CommandParser spyParser = Mockito.spy(parser);
        spyParser.actOnCommand(command, player);
        Mockito.verify(spyParser, times(1)).attack("ork", player);
    }
}
