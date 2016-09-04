package SUD_GAME;

import java.util.Scanner;

import SUD_GAME.domain.BattleThread;
import SUD_GAME.domain.Direction;
import SUD_GAME.domain.Location;
import SUD_GAME.domain.Npc;
import SUD_GAME.domain.Player;

/**
 * SUD Game Tutorial
 * 
 */

public class App {

	private static Thread battleThread = null;
	private static BattleThread battle = null;

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

		actionCommander(firstPlayer, scan);

	}

	private static void actionCommander(Player player, Scanner scan) {

		String command = "";
		while (!command.equals("Exit")) {
			command = readPlayerInput(scan);
			String commands[] = command.split(" ");
			try {
				actOnCommand(commands, player);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Good bye!");
	}

	private static void actOnCommand(String[] commands, Player player)
			throws InterruptedException {

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
				System.out.println("usage > a <monster name> or attack <monster name>."
								+ "\n" + "Remember about size of the letter");
			}
			break;
		case "r":
			if (battleThread != null) {

				battle.setDeactive();
				System.out.println("You run out the battle");
			}
			break;
		default:
			System.out.println("Unknown command");
		}
	}

	private static void attack(String name, Player player) {
		boolean isPresent = player.getCurrentLocation().monsterExists(name);
		Npc monster = player.getCurrentLocation().getMonster(name);
		if (isPresent) {

			battle = new BattleThread(monster, player);
			battleThread = new Thread(battle);
			battleThread.setName("Fight");
			battleThread.start();

		} else {
			System.out.println("There is no monster called  " + name
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
