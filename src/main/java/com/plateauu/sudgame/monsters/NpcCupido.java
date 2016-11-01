package com.plateauu.sudgame.monsters;

import com.plateauu.sudgame.domain.FightByMagicStickStrategy;
import com.plateauu.sudgame.domain.Player;

public class NpcCupido extends Npc {

    private final String DESCRIPTION = "Small flying monster, who is weakness and full of fear";

    public NpcCupido(String name, int npcHealth, int nbcStrength, int npcAgility, ConversationScript conversationScript) {
        super(name, npcHealth, nbcStrength, npcAgility, Monsters.Cupido, conversationScript);
        this.fightableInterface = new FightByMagicStickStrategy();
        this.npcDescription = DESCRIPTION;
        this.isTalkative = false;
    }

    public NpcCupido(String name, int npcHealth, int nbcStrength, int npcAgility) {
        super(name, npcHealth, nbcStrength, npcAgility, Monsters.Cupido);
        this.fightableInterface = new FightByMagicStickStrategy();
        this.npcDescription = DESCRIPTION;
        this.isTalkative = false;
    }


    @Override
    public String makeConversation(Player player) {

        return null;
    }
}
