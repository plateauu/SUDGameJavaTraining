/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plateauu.sudgame.figth;

import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;

public interface BattleStrategy {

    public void fight(Player player, Npc monster, Runnable thread);
    
}
