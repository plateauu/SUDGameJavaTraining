package com.plateauu.sudgame.services;

import com.plateauu.sudgame.figth.AgilityBattleStrategy;
import com.plateauu.sudgame.figth.BattleStrategy;
import com.plateauu.sudgame.BattleThread;
import com.plateauu.sudgame.domain.Player;
import com.plateauu.sudgame.monsters.Npc;

public class KillCommand implements Command {

    private String name;
    private Player player;

    public KillCommand(String name, Player player) {
        this.name = name;
        this.player = player;
 
    }
    
    public String attack() {
        String result = "";
        boolean monsterIsPresent = player.ifMonsterNearby(name);

        if (monsterIsPresent) {
            Npc monster = player.prepareMonster(name);
            beginCombat(monster, player);
            result = "Battle has bagun";
        } else {
            result = "There is no monster called " + name + " to attack";
        }
        return result;
    }

    public void beginCombat(Npc monster, Player player) {
        BattleStrategy bs = new AgilityBattleStrategy();

        Runnable battle = new BattleThread(monster, player, bs);
        Thread battleThread = new Thread(battle);
        battleThread.setName("Fight");
        battleThread.start();
    }

    @Override
    public String execute() {
        return attack();
    }

}
