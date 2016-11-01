package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Player;

import java.util.Scanner;

class CommandParser {

    void actOnCommand(String[] commands, Player player, Scanner scan) throws InterruptedException {
        Command command = new NullCommand();

        switch (commands[0]) {
            case "n":
            case "north":
                command = new MoveCommand(Direction.N, player);
                break;
            case "s":
            case "south":
                command = new MoveCommand(Direction.S, player);
                break;
            case "e":
            case "east":
                command = new MoveCommand(Direction.E, player);
                break;
            case "w":
            case "west":
                command = new MoveCommand(Direction.W, player);
                break;
            case "a":
            case "attack":
            case "kill":
            case "k":
                if (commands.length > 1) {
                    command = new KillCommand(commands[1], player);
                } else {
                    command = new HelpCommand();
                }
                break;
            case "exit":
                break;
            case "l":
            case "look":
                command = new LookCommand(commands, player);
                break;
            case "stats":
                command = new ShowStatsCommand(commands, player);
                break;
            case "talk":
            case "t":
                command = new TalkCommand(commands, player, scan);
                break;

            case "help":
            case "h":
                command = new HelpCommand();
                break;
            default:
                break;
        }

        System.out.println(command.execute());
    }

}
