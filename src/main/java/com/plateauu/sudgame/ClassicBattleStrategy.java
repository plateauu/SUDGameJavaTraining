package com.plateauu.sudgame;

import com.plateauu.sudgame.BattleStrategy;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;

public class ClassicBattleStrategy implements BattleStrategy {

    private boolean playerHits = true;
    private BattleThread bt;

    @Override
    public void fight(Player player, Npc monster, Runnable thread) {
        bt = (BattleThread) thread;
        int hitStrenght = 0;
        try {
            playerHits = true;
            hitStrenght = player.calculateHitStrenght();
            damagaTaken(monster, player, hitStrenght);
            stopBattle(this.checkIsAlive(monster, player));

            playerHits = false;
            hitStrenght = monster.calculateHitStrenght();
            damagaTaken(monster, player, hitStrenght);
            showHealth(player);
            stopBattle(this.checkIsAlive(monster, player));

            Thread.sleep(2000);
            
        } catch (Exception e) {
            System.out.println("Bang!");
        }
    }

    public void damagaTaken(Npc monster, Player player, int hitStrenght) {

        if (playerHits) {
            monster.setHealth(monster.getHealth() - hitStrenght);
        } else {
            player.setHealth(player.getHealth() - hitStrenght);
        }
    }

    public void showHealth(Player player) {
        if (player.getHealth() > 0) {
            System.out.println(player.getName() + " Health: " + player.getHealth());
        }
    }

    private boolean checkIsAlive(Npc monster, Player player) {
        boolean isAlive = true;

        if (!player.isAlive()) {
            System.out.println(monster.getName() + " has won a battle");
            System.out.println(player.getName() + " has fail");
            isAlive = false;

        }

        if (!monster.isAlive()) {
            System.out.println(player.getName() + " has won a fight with " + monster.getName());
            System.out.println(monster.getName() + " has died");
            player.getCurrentLocation().removeMonster(monster);
            isAlive = false;
        }

        return isAlive;

    }

    public void stopBattle(boolean isAlive) {
        if (!isAlive) {
            bt.setDeactive();
        }
    }

}
