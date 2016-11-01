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
    private Map<String, String> responseSubject;
    private String actualSubject;
    private Map<String, Integer> subjectCounter;

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

        responseSubject = new HashMap<>();
        responseSubject.put("Clean all maps", "Setup subjects");

        subjectCounter = new HashMap<>();
        subjectCounter.put("Setup subjects", 1);

        actualSubject = "Nothing";
    }

    private void defaultConfig() {
        conversationsSubjects = new HashMap<>();
        conversationsSubjects.put(1, "People");
        conversationsSubjects.put(2, "Fight");
        conversationsSubjects.put(3, "Place");

        responseSubject = new HashMap<>();
        responseSubject.put("People", "People are fine");
        responseSubject.put("Fight", "I hate fight");
        responseSubject.put("Place", "This place is fine");

        subjectCounter = new HashMap<>();
        subjectCounter.put("People", 0);
        subjectCounter.put("Fight", 0);
        subjectCounter.put("Place", 0);

        actualSubject = "Nothing";
    }

    public void setActualSubject(int playerInput) {
        for (int key : conversationsSubjects.keySet()){
            if (key == playerInput) {
                actualSubject = conversationsSubjects.get(key);
            }
        }
    }


    public Map<Integer, String> getConversationsSubjects() {
        return conversationsSubjects;
    }

    public String getTopics() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry subject : conversationsSubjects.entrySet()) {
            sb.append(subject.getKey() + ". ");
            sb.append(subject.getValue() + "\n");
        }

        return sb.toString();
    }


    //TODO getrespone, licznik odpowiedzi, while, exit, testy
    public String getResponse() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry subject : conversationsSubjects.entrySet()) {
            sb.append(subject.getKey() + ". ");
            sb.append(subject.getValue() + "\n");
        }

        return sb.toString();
    }
}

