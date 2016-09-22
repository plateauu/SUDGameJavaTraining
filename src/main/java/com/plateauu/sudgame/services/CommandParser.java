package com.plateauu.sudgame.services;

import java.util.Scanner;

import com.plateauu.sudgame.BattleThread;
import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;

public class CommandParser {

    private Thread battleThread = null;
    private BattleThread battle = null;

    private final static String HELP = " "
            + "\n Welcome to SUD GAME v.0.1"
            + "\n Expected parameteres: "
            + "\n N (north): go to north"
            + "\n S (south): go to south"
            + "\n E (east): go to east"
            + "\n W (west): go to west"
            + "\n k (kill) [monster_name]: attacks to [monster_name]"
            + "\n r (run): run away from an battlefield";

    public void actionCommander(Player player, Scanner scan) {

        String command = "";
        while (!command.equalsIgnoreCase("Exit")) {
            try {
                command = readPlayerInput(scan).toLowerCase();
                String commands[] = command.split(" ");
                actOnCommand(commands, player);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Good bye!");
    }

    public void actOnCommand(String[] commands, Player player) throws InterruptedException {
        switch (commands[0]) {
            case "n":
            case "north":
                stopBattle();
                move(Direction.N, player);
                break;
            case "s":
            case "south":
                stopBattle();
                move(Direction.S, player);
                break;
            case "e":
            case "east":
                stopBattle();
                move(Direction.E, player);
                break;
            case "w":
            case "west":
                stopBattle();
                move(Direction.W, player);
                break;
            case "a":
            case "attack":
            case "kill":
            case "k":
                if (commands.length > 1) {
                    attack(commands[1], player);
                } else {
                    showHelp();
                }
                break;
            case "r":
            case "run":
                stopBattle();
                break;
            case "exit":
                break;
            default:
                showHelp();
                break;
        }
    }

    private static void showHelp() {
        System.out.println(CommandParser.HELP);
    }

    void attack(String name, Player player) {
        boolean isPresent = player.ifMonsterNearby(name);
        Npc monster = player.prepareMonster(name);
        if (isPresent) {
            battle = new BattleThread(monster, player);
            battleThread = new Thread(battle);
            battleThread.setName("Fight");
            battleThread.start();
        } else {
            System.out.println("There is no monster called  " + name + " to attack");
        }
    }

    void move(Direction direction, Player player) {
        boolean hasMoved = player.move(direction);
        if (hasMoved) {
            System.out.println(player.getLocationDescription());
        } else {
            System.out.println("You can't go that way ");
        }
    }

    private String readPlayerInput(Scanner scan) {
        System.out.print(">");
        String command = scan.nextLine();
        return command;
    }

    private void stopBattle() {
        if (battleThread != null) {
            battle.setDeactive();
            System.out.println("You run out the battle");
        }
    }

}
