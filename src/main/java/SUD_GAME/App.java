package SUD_GAME;

import java.util.Scanner;

import SUD_GAME.domain.Direction;
import SUD_GAME.domain.Location;
import SUD_GAME.domain.Npc;
import SUD_GAME.domain.Player;

/**
 * SUD Game Tutorial
 */

public class App {

	private static Thread battleThread = null;
	private static BattleThread battle = null;
	private final static String HELP = " "
			
			+ "\n Welcome to SUD GAME v.0.1" 
			+ "\n Expected parameteres: " 
			+ "\n N (north): go to north"
			+ "\n S (south): go to south"
			+ "\n E (east): go to east"
			+ "\n W (west): go to west"
			+ "\n k (kill) [monster_name]: attacks to [monster_name]"
			+ "\n r (run): run away from an battlefield"
			;
	

	public static void main(String[] args) {

		Location shire = new Location("Shire", "Beautful forest, a land where sun never setting down");
		Location mordor = new Location("Mordor", "It is a land where dark lord rule since ever" + "\n"
				+ "and dark of the darkness deamons and orks live there");
		Location graceland = new Location("Graceland", "Mysterious count. No one has more information about it");
		Location newyork = new Location("New York"," The city that never sleeps");

		shire.addLocation(Direction.N, mordor);
		shire.addLocation(Direction.S, graceland);
		mordor.addLocation(Direction.S, shire);
		graceland.addLocation(Direction.N, shire);
		graceland.addLocation(Direction.E, newyork);
		newyork.addLocation(Direction.W, graceland);
		

		Npc orkZygmund = new Npc("Zygmund", 3, 2);
		Npc ogrAlfred = new Npc("Alfred", 20, 5);
		Npc cinkciarz = new Npc("Cinkciarz", 20, 5);
		Npc ogrZenon = new Npc("Zenon", 20, 5);
		Npc kupidynMax = new Npc("Max", 1, 1);
		Npc kupidynMini = new Npc("Mini", 2, 1);
		Npc kupidynLenek = new Npc("Lenek", 3, 1);
		mordor.addMonster(orkZygmund);
		mordor.addMonster(ogrAlfred);
		mordor.addMonster(ogrZenon);
		newyork.addMonster(cinkciarz);
		graceland.addMonster(kupidynLenek);
		graceland.addMonster(kupidynMax);
		graceland.addMonster(kupidynMini);

		System.out.println("What's your name?");
		Scanner scan = new Scanner(System.in);
		String scanName = scan.nextLine();
		Player player = new Player(scanName, shire);

		actionCommander(player, scan);

	}

	private static void actionCommander(Player player, Scanner scan) {

		String command = "";
		while (!command.equalsIgnoreCase("Exit")) {
			try {
				command = readPlayerInput(scan).toLowerCase();
				String commands[] = command.split(" ");
				actOnCommand(commands, player);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Good bye!");
	}

	private static void actOnCommand(String[] commands, Player player) throws InterruptedException {

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
			stopBattle();
			break;
		default:
			showHelp();
			break;
			
		}
	}

	public static void stopBattle() {
		if (battleThread != null) {
			battle.setDeactive();
			System.out.println("You run out the battle");
		}
	}

	private static void attack(String name, Player player) {
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

	private static void move(Direction direction, Player player) {
		boolean hasMoved = player.move(direction);
		if (hasMoved) {
			System.out.println(player.getLocationDescription());
		} else {
			System.out.println("You can't go that way ");
		}
	}

	private static String readPlayerInput(Scanner scan) {
		System.out.print(">");
		String command = scan.nextLine();
		return command;
	}
	
	private static void showHelp(){
		System.out.println(HELP);
	}

}
