package com.plateauu.sudgame;

import com.plateauu.sudgame.repository.GameCreatorRepository;
import java.util.Scanner;

import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.services.InputReader;

/*
 * SUD Game Tutorial
 */
public class App {

    public static void main(String[] args) {

        GameCreatorRepository game = new GameCreatorRepository();


        game.setStartLocation("Shire");

        System.out.println("What's your name?");
        Scanner scan = new Scanner(System.in);
        String scanName = scan.nextLine();
        Player player = new Player(scanName, game.getStartLocation(),10, 25);

        InputReader ir = new InputReader(scan, player);
        ir.actionCommander(player, scan);

    }

}
