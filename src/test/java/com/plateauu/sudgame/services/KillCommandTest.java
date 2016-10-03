/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Location;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;
import com.plateauu.sudgame.monsters.NpcOgr;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;

/**
 *
 * @author plateauu
 */
public class KillCommandTest {

    private Location shire;
    private Location mordor;
    private Npc ork;
    private Player player;

    @Before
    public void initTest() {
        shire = new Location("shire", "long description shire");
        mordor = new Location("mordor", "long description mordor");
        shire.addLocation(Direction.N, mordor);
        ork = new NpcOgr("Ork", 10, 3, 2);
        shire.addMonster(ork);
        player = new Player("PLateauu", shire);
    }

    @Test
    public void testKillCommand() {

        String[] command = {"kill", "ork"};

        Command killCommand = new KillCommand("ork", player, new CommandParser());
        Command spyKillCommand = Mockito.spy(killCommand);
        spyKillCommand.execute();
        Mockito.verify(spyKillCommand, times(1)).execute();

    }

    @Test
    public void testBadEnemy() {
        Command killCommand = new KillCommand("ogr", player, new CommandParser());

        String expectedValue = "There is no monster called ogr to attack";
        String actualValue = killCommand.execute();

        Assert.assertEquals("Comparing message:", expectedValue, actualValue);

    }

}
