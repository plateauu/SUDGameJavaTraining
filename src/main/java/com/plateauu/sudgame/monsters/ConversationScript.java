package com.plateauu.sudgame.monsters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by plateauu on 10/31/16.
 */

public class ConversationScript {

    public static final int DEFAULT = 1;
    public static final int USER = 2;


    private Map<Integer, String> conversationsSubjects;
    private Map<String, Map<Integer, String>> responseSubject;
    private String actualSubject;
    private int conversationIndex;
    private int conversationLimit;
    private int conversationExitInt;


    public ConversationScript(int version) {

        if (version == 1) {

            defaultConfig();

        } else {

            userConfig();
        }
    }

    private void userConfig() {
        conversationsSubjects = new HashMap<>();
        conversationsSubjects.put(1, "Setup subjects");


        Map<Integer, String> defaultConversation = new HashMap<>();
        defaultConversation.put(0, "You have to configure script");

        responseSubject = new HashMap<>();
        responseSubject.put("Default", defaultConversation);

        actualSubject = "Nothing";
        conversationIndex = 0;
        conversationLimit = 1;
        conversationExitInt = 2;
    }

    private void defaultConfig() {
        conversationsSubjects = new HashMap<>();
        conversationsSubjects.put(1, "People");
        conversationsSubjects.put(2, "Fight");
        conversationsSubjects.put(3, "Place");
        conversationsSubjects.put(4, "Exit");


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

        responseSubject = new HashMap<>();
        responseSubject.put("People", peopleConversation);
        responseSubject.put("Fight", fightConversation);
        responseSubject.put("Place", placeConversation);
        responseSubject.put("Exit", exitConversation);

        actualSubject = "Nothing";
        conversationIndex = 0;
        conversationLimit = Math.min(Math.min(fightConversation.size(), placeConversation.size()), peopleConversation.size());
        conversationExitInt = 4;

    }

    public void setActualSubject(int playerInput) {
        for (int key : conversationsSubjects.keySet()) {
            if (key == playerInput) {
                actualSubject = conversationsSubjects.get(key);
            }
        }
    }


    public int getConversationExitInt() {
        return conversationExitInt;
    }

    public Map<Integer, String> getConversationsSubjects() {
        return conversationsSubjects;
    }

    public String getTopics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Select subject of the conversation:\n");

        for (Map.Entry subject : conversationsSubjects.entrySet()) {
            sb.append(subject.getKey() + ". ");
            sb.append(subject.getValue() + "\n");
        }

        return sb.toString();
    }


    //TODO testy
    public String getResponse(Npc monster) {
        String response;
        boolean ifExists = conversationSubjectExists(actualSubject);
        if (ifExists) {
            Map<Integer, String> actualConversation = responseSubject.get(actualSubject);
            response = monster.getName() + " said: " + actualConversation.get(conversationIndex) + "\n";
            conversationIndex++;

        } else {
            response = "Bad choice. Try again. If you want to go further just type: stop";
        }


        return response;
    }

    private boolean conversationSubjectExists(String actualSubject) {
        return conversationsSubjects.values().contains(actualSubject);
    }

    public boolean hasNextAnswer() {
        return conversationIndex < conversationLimit;
    }

    public void setConversationIndex(int conversationIndex) {
        this.conversationIndex = conversationIndex;
    }

    void setConversationLimit(int conversationLimit) {
        this.conversationLimit = conversationLimit;
    }

    void setConversationExitInt(int conversationExitInt) {
        this.conversationExitInt = conversationExitInt;
    }

    Map<String, Map<Integer, String>> getResponseSubject() {
        return responseSubject;
    }
}

