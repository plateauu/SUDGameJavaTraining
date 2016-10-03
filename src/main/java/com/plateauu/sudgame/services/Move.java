/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Player;

/**
 *
 * @author plateauu
 */
public class Move {
    
    Direction direction;
    Player player;
            
 
       void move(Direction direction, Player player) {
        boolean hasMoved = player.move(direction);
        if (hasMoved) {
            System.out.println(player.getLocationDescription());
        } else {
            System.out.println("You can't go that way ");
        }
    }
    
    
}
