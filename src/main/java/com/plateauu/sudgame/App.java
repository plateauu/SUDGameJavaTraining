package com.plateauu.sudgame;

import com.plateauu.sudgame.repository.GameCreatorRepository;
import java.util.Scanner;

import com.plateauu.sudgame.services.CommandReader;
import com.plateauu.sudgame.domain.Player;

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
        Player player = new Player(scanName, game.getStartLocation());

        CommandReader.actionCommander(player, scan);

    }

}
