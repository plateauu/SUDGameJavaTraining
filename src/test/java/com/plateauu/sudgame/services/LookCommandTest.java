package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Location;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;
import com.plateauu.sudgame.monsters.NpcCupido;
import com.plateauu.sudgame.monsters.NpcOgr;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LookCommandTest {

    private Location mordor;
    private Location shire;
    private Player player;
    private Npc cupido;

    @Before
    public void setUp() {
        mordor = new Location("mordor", "Mordor mordor long description");
        shire = new Location("shire", "Shire shire long Desc");
        mordor.addLocation(Direction.E, shire);
        shire.addLocation(Direction.W, mordor);
        cupido = new NpcCupido("Kupidyn", 10, 15, 5);
        mordor.addMonster(cupido);
        mordor.addLocationItem("Oak", "Old oak at the centre of the big, green forest");
        player = new Player("PLateauu", mordor);
        
    }

    @Test
    public void ifCorrectMethodPerformedTest() {
        String[] command = {"look"};
        LookCommand lookCommand = new LookCommand(command, player);
        LookCommand spyLookCommand = Mockito.spy(lookCommand);
        spyLookCommand.execute();
        Mockito.verify(spyLookCommand).player.getLocationDescription();
    }

    @Test
    public void resultOfTheLookCommandTest() {
        String[] command = {"look"};
        LookCommand lookCommand = new LookCommand(command, player);
        String actualValue = lookCommand.execute();
        String expectedValue = "Now, you are at: mordor\nMordor mordor long description\nVisible exits: East\nEncountered monsters: Kupidyn\nItems to see: Oak";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void lookAtTheMonsterTest() {
        String[] command = {"look", "Kupidyn"};
        LookCommand lookCommand = new LookCommand(command, player);
        String expectedValue = "Monster> Kupidyn (Cupido) - Small flying monster, who is weakness and full of fear";
        assertEquals(expectedValue, lookCommand.execute());

    }

    @Test
    public void lookIfThereIsNoMonsterTest() {
        String[] command = {"look", "Tree"};
        LookCommand lookCommand = new LookCommand(command, player);
        String expectedValue = "Beware of bad names";
        assertEquals(expectedValue, lookCommand.execute());
    }

    @Test
    public void ifLookAtItemMethodIsPerformedTest() {
        String[] command = {"look", "Oak"};
        LookCommand lookCommand = new LookCommand(command, player);
        LookCommand spyLookCommand = Mockito.spy(lookCommand);
        spyLookCommand.execute();
        Mockito.verify(spyLookCommand).lookAtItem();
    }
    
        @Test
    public void ifLookAtMonsterMethodIsPerformedTest() {
        String[] command = {"look", "Kupidyn"};
        LookCommand lookCommand = new LookCommand(command, player);
        LookCommand spyLookCommand = Mockito.spy(lookCommand);
        spyLookCommand.execute();
        Mockito.verify(spyLookCommand).lookAtMonster();
    }

    @Test
    public void lookAtItemTest() {
        String[] command = {"look", "OAk"};
        LookCommand lookCommand = new LookCommand(command, player);
        String expecetedValue = "Item> Old oak at the centre of the big, green forest";
        assertEquals(expecetedValue, lookCommand.execute());
    }
    
    @Test
    public void sameMonsterAndItemNameTest() {
        String[] command = {"look", "Oak"};
        Npc oakOgr = new NpcOgr("oak", 19, 19, 10);
        mordor.addMonster(oakOgr);
        LookCommand lookCommand = new LookCommand(command, player);
        String expecetedValue = "Item> Old oak at the centre of the big, green forest\n"
                            + "Monster> oak (Ogr) - Big monster, who is strong and bad looking guy";
        assertEquals(expecetedValue, lookCommand.execute());
    }
    
   

}
