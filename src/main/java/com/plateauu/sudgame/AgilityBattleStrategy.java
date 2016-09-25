/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plateauu.sudgame;

import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;
import java.util.Random;

/**
 *
 * @author plateauu
 */
/*
TODO: unit tests for: damageTaken, checkIsAlive, showHealth, calculateHitChance
 */
public class AgilityBattleStrategy implements BattleStrategy {

    private boolean playerHits = true;
    private BattleThread bt;

    @Override
    public void fight(Player player, Npc monster, Runnable thread) {
        bt = (BattleThread) thread;
        try {
            playerHits = true;
            if (calculateHitChance(player, monster)) {
                int hitStrenght = player.calculateHitStrenght();
                damagaTaken(monster, player, hitStrenght);
                stopBattle(this.checkIsAlive(monster, player));
            } else {
                System.out.println("You missed the " + monster.getName());
            }

            playerHits = false;
            if (calculateHitChance(monster, player)) {
                int hitStrenght = monster.calculateHitStrenght();
                damagaTaken(monster, player, hitStrenght);
                showHealth(player);
                stopBattle(this.checkIsAlive(monster, player));
            } else if (monster.isAlive()) {
                System.out.println(monster.getName() + " missed");
            }

            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("Bang!!");
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

    private boolean calculateHitChance(Npc monster, Player player) {
        int r = new Random().nextInt(20);
        return (monster.getAgility() + r > player.getAgility());
    }

    private boolean calculateHitChance(Player player, Npc monster) {
        int r = new Random().nextInt(40);
        return (player.getAgility() + r > monster.getAgility());
    }
}
