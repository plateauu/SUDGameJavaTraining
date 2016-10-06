/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plateauu.sudgame.services;

import com.plateauu.sudgame.domain.Player;

/**
 *
 * @author plateauu
 */
class ShowStatsCommand implements Command {
    
    String[] commands;
    Player player;

    public ShowStatsCommand(String[] commands, Player player) {
        this.commands = commands;
        this.player = player;
    }

    @Override
    public String execute() {
    
        return showStats();
            
    }
    
    public String showStats() {
        String stats;
        if (commands.length == 1) {
            stats = player.getStatistics();
        } else {
            stats = player.getMonsterStatistics(commands[1]);
        }
        return stats;
    }

    
    
    
}
