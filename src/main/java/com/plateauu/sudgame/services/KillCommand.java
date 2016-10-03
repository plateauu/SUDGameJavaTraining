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

    String attack() {
        boolean isPresent = player.ifMonsterNearby(name);
        Npc monster = player.prepareMonster(name);

        if (isPresent) {
            commandParser.beginCombat(monster, player);
            return "Battle has bagun";
        } else {
            return "There is no monster called " + name + " to attack";
        }
    }

    @Override
    public String execute() {
        return attack();
    }

}
