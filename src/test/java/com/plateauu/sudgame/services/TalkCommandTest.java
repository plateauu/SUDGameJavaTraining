package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Location;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.ConversationScript;
import com.plateauu.sudgame.monsters.Npc;
import com.plateauu.sudgame.monsters.NpcOrk;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Scanner;

/**
 * Created by plateauu on 10/31/16.
 */

public class TalkCommandTest {

    private Player player;
    private Scanner scan;
    private Npc ork;



    @Before
    public void setUp() throws Exception {
        Location mordor = new Location("Mordor", "Long description of Mordor");
        ConversationScript cs = new ConversationScript(ConversationScript.DEFAULT);
        ork = new NpcOrk("Ork", 100, 10, 10, cs);
        scan = new Scanner(System.in);
        mordor.addMonster(ork);
        player = new Player("Plateauu", mordor);
    }


    @Test
    public void ifTalkCommandWorkingWithMonster() throws Exception {
        String[] commands = {"t", "Ork"};
        TalkCommand talkCommand = new TalkCommand(commands, player, scan);
        TalkCommand spy = Mockito.spy(talkCommand);
        spy.execute();
        Mockito.verify(spy, Mockito.times(1)).makeConversation(ork);


    }

    @Test
    public void ifTalkCommandWorkingNoneMonster() throws Exception {
        String[] commands = {"t"};
        TalkCommand talkCommand = new TalkCommand(commands, player, scan);
        String actualValue = talkCommand.execute();
        String expectedValue = "Who do you want talk to? use: t (talk) [monster name]";
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void ifTalkCommandWorkingWrongName() throws Exception {
        String[] commands = {"t", "Denis"};
        TalkCommand talkCommand = new TalkCommand(commands, player, scan);
        String actualValue = talkCommand.execute();
        String expectedValue = "There is none called Denis";
        Assert.assertEquals(expectedValue, actualValue);
    }
}
