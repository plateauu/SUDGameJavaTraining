package SUD_GAME;

import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

import SUD_GAME.domain.Direction;
import SUD_GAME.domain.Location;
import SUD_GAME.domain.Npc;
import SUD_GAME.domain.Player;

/**
 * SUD Game Tutorial
 * 
 */

public class App {

	public static void main(String[] args) {

		Location shire = new Location("Shire",
				"Beautful forest, a land where sun never setting down");
		Location mordor = new Location(
				"Mordor",
				"It is a land where dark lord rule since ever"
						+ "\n"
						+ "and dark of the darkness deamons and orks live there");
		Location graceland = new Location("Graceland",
				"Mysterious count. No one has more information about it");

		shire.addLocation(Direction.N, mordor);
		shire.addLocation(Direction.S, graceland);
		mordor.addLocation(Direction.S, shire);
		graceland.addLocation(Direction.N, shire);

		Npc orkZygmund = new Npc("Zygmund", 3, 2);
		Npc ogrAlfred = new Npc("Alfred", 20, 5);
		mordor.addMonster(orkZygmund);
		mordor.addMonster(ogrAlfred);

		System.out.println("What's your name?");
		Scanner scan = new Scanner(System.in);
		String scanName = scan.nextLine();
		Player firstPlayer = new Player(scanName, shire);

		String command = "";
		while (!command.equals("Exit")) {
			command = readPlayerInput(scan);
			String commands[] = command.split(" ");
			actOnCommand(commands, firstPlayer);

		}

		System.out.println("Good bye!");

	}

	private static void actOnCommand(String[] commands, Player player) {
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
			if (commands.length > 1) {
				attack(commands[1], player);
			} else {
				System.out.println("usage > a <monster name> or attack <monster name>." + "\n" + "Remember about size of the letter");
			}
			break;
		default:
			System.out.println("Unknown command");
		}
	}

	private static void attack(String attackedName, Player player) {
		boolean isPresent = player.getCurrentLocation().monsterExists(attackedName);
		if (isPresent) {
			player.fightWithMonster(attackedName);
		} else {
			System.out.println("There is no monster called  " + attackedName
					+ " to attack");
		}

	}

	private static void move(Direction direction, Player player) {
		boolean hasMoved = player.move(direction);
		if (hasMoved) {
			System.out.println(player.getCurrentLocation().getDescription());
		} else {
			System.out.println("You can't go that way ");
		}
	}

	private static String readPlayerInput(Scanner scan) {
		System.out.print(">");
		String command = scan.nextLine();
		return command;
	}
}