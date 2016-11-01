package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.ConversationScript;
import com.plateauu.sudgame.monsters.Npc;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by plateauu on 10/31/16.
 */

class TalkCommand implements Command {


    private final String[] commands;
    private final Player player;



    TalkCommand(String[] commands, Player player) {
        this.commands = commands;
        this.player = player;

    }

    private boolean isParamTransferred() {
        int commandsLength = commands.length;
        return commandsLength >= 2;
    }

    private String talkToMonster() {
        String responseFromMonster;

        if (isParamTransferred()) {
            String monsterName = commands[1];
            if (player.ifMonsterNearby(monsterName)) {

                Npc monster = player.prepareMonster(monsterName);

                makeConversation(monster);

                responseFromMonster = monster.getName() + "> Don't wanna talk you! Looser!";

            } else {
                responseFromMonster = "There is none called " + monsterName;
            }
        } else {
            responseFromMonster = "Who do you want talk to? use: t (talk) [monster name]";
        }

        return responseFromMonster;
    }

    private void makeConversation(Npc monster) {
        Scanner scanner = new Scanner(System.in);
        int playerInput;
        int prevInput = 0;

        ConversationScript cs = monster.getConversationScript();
        cs.setConversationIndex(0);

        while (cs.hasNextAnswer()) {

            try {

                System.out.println(cs.getTopics());

                System.out.print(monster.getName() + "> ");

                playerInput = scanner.nextInt();

                if (prevInput != playerInput) {
                    cs.setConversationIndex(0);
                }

                if (cs.getConversationsSubjects().keySet().contains(playerInput)) {
                    cs.setActualSubject(playerInput);
                    System.out.println(cs.getResponse(monster));
                } else {
                    System.out.println(monster.getName() + "> Wrong option.\n");
                }

                prevInput = playerInput;

                Thread.sleep(1000);

            } catch (InputMismatchException e) {
                System.out.println("You typed wrong number. Pick the number!");
                scanner.next();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }


    @Override
    public String execute() {
        return talkToMonster();
    }
}
