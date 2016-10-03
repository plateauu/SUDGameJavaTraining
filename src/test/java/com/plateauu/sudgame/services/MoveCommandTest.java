/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Location;
import com.plateauu.sudgame.domain.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author plateauu
 */
public class MoveCommandTest {

    private Location shire;
    private Location mordor;

    @Before
    public void initTest() {
        shire = new Location("shire", "long description shire");
        mordor = new Location("mordor", "long description mordor");
        shire.addLocation(Direction.N, mordor);
    }

    @Test
    public void moveCommandTest() {
        Player player = new Player("PLateauu", shire);
        String[] command = {"north"};

        MoveCommand moveCommand = new MoveCommand(Direction.N, player);
        MoveCommand spyMoveCommand = Mockito.spy(moveCommand);
        spyMoveCommand.execute();
        Mockito.verify(spyMoveCommand).move();
    }

    @Test
    public void getInfoIfThereIsNoLocationTest() {
        Player player = new Player("PLateauu", shire);
        MoveCommand moveCommand = new MoveCommand(Direction.E, player);
        String result = moveCommand.execute();
        assertEquals("You can't go that way", result);

        
    }

}
