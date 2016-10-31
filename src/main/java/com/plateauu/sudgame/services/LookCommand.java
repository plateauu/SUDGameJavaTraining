package com.plateauu.sudgame.services;

import com.google.common.base.Joiner;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;

import java.util.ArrayList;
import java.util.List;

class LookCommand implements Command {

    Player player;
    String[] command;

    public LookCommand(String[] command, Player player) {
        this.player = player;
        this.command = command;
    }

    @Override
    public String execute() {
        List<String> itemList = new ArrayList<>();
        boolean isItemExists;
        boolean isMonsterExists;

        if (command.length != 1) {
            isItemExists = player.isItemNearby(command[1]);
            isMonsterExists = player.ifMonsterNearby(command[1]);
            if (isItemExists && isMonsterExists) {
                itemList.add(lookAtItem());
                itemList.add(lookAtMonster());
            } else if (isItemExists && !isMonsterExists) {
                itemList.add(lookAtItem());
            } else if (!isItemExists && isMonsterExists) {
                itemList.add(lookAtMonster());
            } else {
                itemList.add("Beware of bad names");
            }
        } else {
            itemList.add(player.getLocationDescription());
        }

        return Joiner.on("\n").join(itemList);

    }

    public String lookAtMonster() {
        Npc monster = player.prepareMonster(command[1]);
        return "Monster> " + monster.getDescprition();
    }

    public String lookAtItem() {
        return "Item> " + player.getItemNearby(command[1]);
    }

}
