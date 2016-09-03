package SUD_GAME;

import java.util.Scanner;

import SUD_GAME.domain.Direction;
import SUD_GAME.domain.Location;
import SUD_GAME.domain.Player;

/**
 * SUD Game Tutorial
 *
 */

public class App {
	
	
	public static void main(String[] args) {
			
		Location shire = new Location("Shire", "Beautful forest, a land where sun never setting down");
		Location mordor = new Location("Mordor", "It is a land where dark lord rule since ever" + "\n"+ "and dark of the darkness deamons and orks live there");
		shire.addLocation(Direction.N, mordor);
		mordor.addLocation(Direction.S, shire);
		
		System.out.println("What's your name?");
		Scanner scan = new Scanner(System.in);
		String scanName = scan.nextLine();
		Player firstPlayer = new Player(scanName, shire);
		
		String command = "";
		while (!command.equals("Exit")){
			command = readPlayerInput(scan);
			actOnCommand(command, firstPlayer);
		}
		
		System.out.println("Good bye!");
			
	}
	
	private static void actOnCommand(String command, Player player) {
		switch (command){
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
		default:
			System.out.println("Unknown command");
		}
	}

	private static void move(Direction direction, Player player) {
		boolean hasMoved = player.move(direction);
		if(hasMoved) {
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