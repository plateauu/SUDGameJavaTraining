package com.plateauu.sudgame;

import java.util.Scanner;

import com.plateauu.sudgame.domain.Commander;
import com.plateauu.sudgame.domain.Player;

/*
 * SUD Game Tutorial
 */

public class App {

	public static void main(String[] args) {

		GameCreator game = new GameCreator();

		game.setStartLocation("Shire");

		System.out.println("What's your name?");
		Scanner scan = new Scanner(System.in);
		String scanName = scan.nextLine();
		Player player = new Player(scanName, game.getStartLocation());

		Commander commander = new Commander();
		commander.actionCommander(player, scan);

	}

}
