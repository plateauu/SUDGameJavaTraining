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

    public void move() {
        boolean hasMoved = player.move(direction);
        if (hasMoved) {
            System.out.println(player.getLocationDescription());
        } else {
            System.out.println("You can't go that way ");
        }
    }

    @Override
    public void execute() {
        move();
    }
    
    

}
