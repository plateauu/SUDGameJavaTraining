package com.plateauu.sudgame;

import com.plateauu.sudgame.figth.BattleStrategy;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;

public class BattleThread implements Runnable {

    private Npc monster;
    private Player player;
    private boolean isActive = true;

    private BattleStrategy battleStrategy;

    public BattleThread(Npc monster, Player player, BattleStrategy battleStrategy) {
        this.monster = monster;
        this.player = player;
        this.battleStrategy = battleStrategy;
    }

    @Override
    public void run() {

        System.out.println(player.getName() + " attacked " + monster.getName());
        while (isActive) {
            battleStrategy.fight(player, monster, this);
        }
    }

    public void setDeactive() {
        this.isActive = false;
    }

}
