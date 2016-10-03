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

    private Thread battleThread = null;
    private BattleThread battle = null;

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
                    command = new KillCommand(commands[1], player, this);
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

        if (command != null) {
            System.out.println(command.execute());
        }
    }

    private String readPlayerInput(Scanner scan) {
        System.out.print(">");
        String command = scan.nextLine();
        return command;
    }

    public void beginCombat(Npc monster, Player player) {
        BattleStrategy bs = new AgilityBattleStrategy();

        battle = new BattleThread(monster, player, bs);
        battleThread = new Thread(battle);
        battleThread.setName("Fight");
        battleThread.start();
    }

    private void stopBattle() {
        if (battleThread != null) {
            battle.setDeactive();
            System.out.println("You run out the battle");
        }
    }

    public void showStats(String[] commands, Player player) {
        if (commands.length == 1) {
            System.out.println(player.getStatistics());
        } else {
            String singleMonsterStats = player.getMonsterStatistics(commands[1]);
            System.out.println(singleMonsterStats);
        }
    }

}
