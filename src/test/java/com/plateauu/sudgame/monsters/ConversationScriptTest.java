package com.plateauu.sudgame.monsters;

import org.junit.Assert;
import org.junit.Test;



/**
 * Created by plateauu on 11/1/16.
 */

public class ConversationScriptTest {


    @Test
    public void isCorrectStringAppend() throws Exception{
        ConversationScript cs = new ConversationScript(ConversationScript.DEFAULT);
        cs.getConversationsSubjects().put(1, "People");
        cs.getConversationsSubjects().put(2, "Fight");
        cs.getConversationsSubjects().put(3, "Place");
        String actualValue = cs.getTopics();
        String expectedValue = "1. People\n2. Fight\n3. Place\n";
        Assert.assertEquals(actualValue, expectedValue);
    }

}