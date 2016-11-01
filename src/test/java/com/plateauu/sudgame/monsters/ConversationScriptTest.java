package com.plateauu.sudgame.monsters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by plateauu on 11/1/16.
 */

public class ConversationScriptTest {

    private ConversationScript cs;

    @Before
    public void setUp() {
        cs = new ConversationScript(ConversationScript.DEFAULT);

        cs.getConversationsSubjects().clear();
        cs.getConversationsSubjects().put(1, "People");
        cs.getConversationsSubjects().put(2, "Fight");
        cs.getConversationsSubjects().put(3, "Place");
        cs.getConversationsSubjects().put(4, "Exit");

        Map<Integer, String> peopleConversation = new HashMap<>();
        peopleConversation.put(0, "People Are Awesome");
        peopleConversation.put(1, "Don't like people");
        peopleConversation.put(2, "I would like to kill them!!!");

        Map<Integer, String> fightConversation = new HashMap<>();
        fightConversation.put(0, "I know kung-fu");
        fightConversation.put(1, "Don't know who I would like to fight");
        fightConversation.put(2, "It means.... start fighting!");


        Map<Integer, String> placeConversation = new HashMap<>();
        placeConversation.put(0, "I love this place");
        placeConversation.put(1, "This place is abandon");
        placeConversation.put(2, "I would like to live this place");

        Map<Integer, String> exitConversation = new HashMap<>();
        exitConversation.put(0, "Live in peace!");

        cs.getResponseSubject().clear();
        cs.getResponseSubject().put("People", peopleConversation);
        cs.getResponseSubject().put("Fight", fightConversation);
        cs.getResponseSubject().put("Place", placeConversation);
        cs.getResponseSubject().put("Exit", exitConversation);

        cs.setActualSubject(100);
        cs.setConversationIndex(0);
        cs.setConversationLimit(Math.min(Math.min(fightConversation.size(), placeConversation.size()), peopleConversation.size()));
        cs.setConversationExitInt(4);

    }


    @Test
    public void isCorrectStringAppendTest() {

        String actualValue = cs.getTopics();
        String expectedValue = "Select subject of the conversation:\n1. People\n2. Fight\n3. Place\n4. Exit\n";
        Assert.assertEquals(actualValue, expectedValue);
    }

    @Test
    public void ifGetResponsePositiveTest() {
        Npc monster = new NpcOrk("Ork", 100, 10, 1, cs);
        cs.setActualSubject(1);
        cs.setConversationIndex(0);
        String actual = cs.getResponse(monster);
        String expected = "Ork said: People Are Awesome\n";
        Assert.assertEquals(expected, actual);
    }



}