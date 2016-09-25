package com.plateauu.sudgame.services;

import com.plateauu.sudgame.AgilityBattleStrategy;
import com.plateauu.sudgame.ClassicBattleStrategy;
import com.plateauu.sudgame.BattleStrategy;
import java.util.Scanner;

import com.plateauu.sudgame.BattleThread;
import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;

public class CommandParser {
    private final static String HELP = " "
            + "\n Welcome to SUD GAME v.0.1"
            + "\n Expected parameters: "
            + "\n N (north): moves to north"
            + "\n S (south): moves to south"
            + "\n E (east): moves to east"
            + "\n W (west): moves to west"
            + "\n k (kill) [monster_name]: attacks to [monster_name]"
            + "\n r (run): run away from the battlefield"
            + "\n stats: shows player's statistics"
            + "\n stats [monster_name]: shows monster's info";
    private static void showHelp() {
        System.out.println(CommandParser.HELP);
    }

    private Thread battleThread = null;
    private BattleThread battle = null;


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
                move(Direction.N, player);
                break;
            case "s":
            case "south":
                move(Direction.S, player);
                break;
            case "e":
            case "east":
                move(Direction.E, player);
                break;
            case "w":
            case "west":
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
            case "stats":
                showStats(commands, player);
                break;
            default:
                showHelp();
                break;
        }
    }


    void attack(String name, Player player) {
        boolean isPresent = player.ifMonsterNearby(name);
        Npc monster = player.prepareMonster(name);

        

        if (isPresent) {
            beginCombat(monster, player);
        } else {
            System.out.println("There is no monster called  " + name + " to attack");
        }
    }

    private void beginCombat(Npc monster, Player player) {
        BattleStrategy bs = new AgilityBattleStrategy();
        
        battle = new BattleThread(monster, player, bs);
        battleThread = new Thread(battle);
        battleThread.setName("Fight");
        battleThread.start();
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

    private void showStats(String[] commands, Player player) {
        if (commands.length == 1) {
            System.out.println(player.getStatistics());
        } else {
            String singleMonsterStats = player.getMonsterStatistics(commands[1]);
            System.out.println(singleMonsterStats);
        }
    }

}
