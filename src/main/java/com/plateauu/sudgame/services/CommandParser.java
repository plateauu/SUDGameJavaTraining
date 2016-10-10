package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Player;

public class CommandParser {

    public void actOnCommand(String[] commands, Player player) throws InterruptedException {
        Command command = null;

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
            default:
                command = new HelpCommand();
                break;
        }

        if (command != null) {
            System.out.println(command.execute());
        }
    }

}
