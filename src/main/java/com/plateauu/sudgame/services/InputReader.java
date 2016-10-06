/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Player;
import java.util.Scanner;

/**
 *
 * @author plateauu
 */
public class InputReader {

    Scanner scan;
    Player player;

    public InputReader(Scanner scan, Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void actionCommander(Player player, Scanner scan) {
        CommandParser commandParser = new CommandParser();
        String command = "";
        while (!command.equalsIgnoreCase("Exit")) {
            try {
                command = readPlayerInput(scan).toLowerCase();
                String commands[] = command.split(" ");
                commandParser.actOnCommand(commands, player);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Good bye!");
    }

    private String readPlayerInput(Scanner scan) {
        System.out.print(">");
        String command = scan.nextLine();
        return command;
    }

}
