package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Location;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;
import com.plateauu.sudgame.monsters.NpcCupido;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;

public class ShowStatsCommandTest {

    Location shire;
    Location mordor;
    Player player;

    @Before
    public void setUp() {
        shire = new Location("shire", "long description shire");
        mordor = new Location("mordor", "long description mordor");
        shire.addLocation(Direction.N, mordor);
        player = new Player("PLateauu", shire);

    }

    @Test
    public void testShowStats() throws InterruptedException {
        String[] command = {"stats"};

        ShowStatsCommand stats = new ShowStatsCommand(command, player);
        ShowStatsCommand spyStats = Mockito.spy(stats);

        spyStats.execute();
        Mockito.verify(spyStats, times(1)).showStats();
    }

    @Test
    public void testShowPlayerStats() throws InterruptedException {
        String[] command = {"stats"};
        ShowStatsCommand stats = new ShowStatsCommand(command, player);
        String expectedAnswer = "Name: PLateauu\nHealth: 10000\nStrenght: 2\nAgility: 10";
        Assert.assertEquals(expectedAnswer, stats.showStats());
    }

    @Test
    public void testShowMonsterStats() throws InterruptedException {
        Npc monster = new NpcCupido("Gnome", 1, 1, 1);
        shire.addMonster(monster);
        String[] command = {"stats", "gnome"};
        ShowStatsCommand stats = new ShowStatsCommand(command, player);

        String expectedAnswer = "Name: Gnome\nRace: Cupido\nHealth: 1\nStrenght: 1\nAgility: 1";
        Assert.assertEquals(expectedAnswer, stats.showStats());
    }

    @Test
    public void thereIsNoMonsterToShowStats() throws InterruptedException {
        String[] command = {"stats", "Dragon"};
        ShowStatsCommand stats = new ShowStatsCommand(command, player);

        String expectedAnswer = "There is no one called Dragon here";
        Assert.assertEquals(expectedAnswer, stats.showStats());
    }

}
