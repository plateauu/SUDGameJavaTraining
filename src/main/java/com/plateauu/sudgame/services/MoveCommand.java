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
        boolean hasMoved = player.move(direction);
        if (hasMoved) {
            return player.getLocationDescription();
        } else {
            return "You can't go that way ";
        }
    }

    @Override
    public String execute() {
        return move();
    }
    
    

}
