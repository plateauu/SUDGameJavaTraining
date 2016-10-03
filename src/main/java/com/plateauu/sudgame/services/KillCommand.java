package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;

public class KillCommand implements Command {

    private String name;
    private Player player;
    private CommandParser commandParser;

    public KillCommand(String name, Player player, CommandParser commandParser) {
        this.name = name;
        this.player = player;
        this.commandParser = commandParser;
    }

    void attack() {
        boolean isPresent = player.ifMonsterNearby(name);
        Npc monster = player.prepareMonster(name);

        if (isPresent) {
            commandParser.beginCombat(monster, player);
        } else {
            System.out.println("There is no monster called  " + name + " to attack");
        }
    }

    @Override
    public void execute() {
        attack();
    }

}
