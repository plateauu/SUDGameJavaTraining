package com.plateauu.sudgame.repository;

import com.plateauu.sudgame.repository.GameCreatorRepository;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Location;
import com.plateauu.sudgame.monsters.Monsters;
import com.plateauu.sudgame.monsters.Npc;

public class GameCreatorTest {

    GameCreatorRepository game;

    @Before
    public void initTest() {
        game = new GameCreatorRepository();
        game.addGameNpc("Marcin", 13, 3, 2, "Shire", Monsters.Ork);
        game.addExits("Shire", "New York", Direction.U);
    }

    @Test
    public void testAddGameNpc() {
        List<Npc> list = game.getGameNpc();
        int index = -1;
        for (Npc npc : list) {
            if (npc.getName().equals("Marcin")) {
                index = list.indexOf(npc);
                break;
            }
        }
        assertTrue(list.get(index).getName().equals("Marcin"));
    }

    @Test
    public void testAddExits() {
        assertTrue(game.getGameLocation("Shire").getExtisLists().containsKey(Direction.U));

    }

    @Test
    public void testAddGameLocation() {
        game.addGameLocation("shire", "shire is such a beautiful place");
        List<Location> list = game.getGameLocations();
        assertTrue(list.get(4).getShortDescription().equals("shire"));
    }

}
