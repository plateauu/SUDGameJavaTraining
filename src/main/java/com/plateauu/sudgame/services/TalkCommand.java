package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.ConversationScript;
import com.plateauu.sudgame.monsters.Npc;

import java.util.Scanner;

/**
 * Created by plateauu on 10/31/16.
 */

class TalkCommand implements Command {


    private final String[] commands;
    private final Player player;
    private Scanner scan;


    TalkCommand(String[] commands, Player player, Scanner scan) {
        this.commands = commands;
        this.player = player;
        this.scan = scan;
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
                String response = makeConversation(monster);

                responseFromMonster = "Don't wanna talk you! Looser!";

            } else {
                responseFromMonster = "There is none called " + monsterName;
            }
        } else {
            responseFromMonster = "Who do you want talk to? use: t (talk) [monster name]";
        }

        return responseFromMonster;
    }

    private String makeConversation(Npc monster) {
        String response = "";
        int playerInput;

        ConversationScript cs = monster.getConversationScript();

        System.out.println(cs.getTopics());

        playerInput = scan.nextInt();
        cs.setActualSubject(playerInput);

        System.out.println(cs.get);





        //



        return response;
    }


    @Override
    public String execute() {
        return talkToMonster();
    }
}
