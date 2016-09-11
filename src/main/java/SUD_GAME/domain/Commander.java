package SUD_GAME.domain;

public class Commander {
	public void actOnCommand(String[] commands, Player player) throws InterruptedException {

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
}
