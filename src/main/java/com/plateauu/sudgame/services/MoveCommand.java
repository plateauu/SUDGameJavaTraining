package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Player;

public class MoveCommand implements Command {

    Direction direction;
    Player player;

    public MoveCommand(Direction direction, Player player) {
        this.direction = direction;
        this.player = player;
    }

    public String move() {
        String result = "";
        boolean hasMoved = player.move(direction);
        if (hasMoved) {
            result = player.getLocationDescription();
        } else {
            result = "You can't go that way";
        }
        return result;
    }

    @Override
    public String execute() {
        return move();
    }

}
