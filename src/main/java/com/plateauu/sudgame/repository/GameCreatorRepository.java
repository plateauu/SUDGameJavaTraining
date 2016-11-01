package com.plateauu.sudgame.repository;

import java.util.ArrayList;
import java.util.List;

import com.plateauu.sudgame.domain.Direction;
import com.plateauu.sudgame.domain.Location;
import com.plateauu.sudgame.monsters.ConversationScript;
import com.plateauu.sudgame.monsters.Monsters;
import com.plateauu.sudgame.monsters.Npc;

public final class GameCreatorRepository {

    private Location startLocation;
    private final List<Location> gameLocations;
    private final List<Npc> gameNpc;

    public GameCreatorRepository() {
        this.gameLocations = new ArrayList<>();
        this.gameNpc = new ArrayList<>();

        ConversationScript exampleConversationScript = new ConversationScript(ConversationScript.DEFAULT);

        addGameLocation("Shire", "Beautful forest, a land where sun never setting down");
        addGameLocation("Mordor", "It is a land where dark lord rule since ever" + "\n"
                + "and dark of the darkness deamons and orks live there");
        addGameLocation("Graceland", "Mysterious count. No one has more information about it");
        addGameLocation("New York", " The city that never sleeps");

        addExits("Shire", "Mordor", Direction.N);
        addExits("Mordor", "Shire", Direction.S);
        addExits("Shire", "Graceland", Direction.S);
        addExits("Graceland", "Shire", Direction.N);
        addExits("Graceland", "New York", Direction.E);
        addExits("New York", "Graceland", Direction.W);

        addGameNpc("Zygmund", 3, 2, 10, "Mordor", Monsters.Ogr);
        addGameNpc("Alfred", 20, 5, 5, "Mordor", Monsters.Ork);
        addGameNpc("Stone", 10, 5, 5, "Mordor", Monsters.Cupido);
        addGameNpc("Cinkciarz", 20, 5, 10, "New York", Monsters.Golum);
        addGameNpc("Zenon", 20, 5, 1, "Mordor", Monsters.Cupido, exampleConversationScript);
        addGameNpc("Max", 1, 1, 1, "Graceland", Monsters.Cupido, exampleConversationScript);
        addGameNpc("Mini", 1, 1, 1, "Graceland", Monsters.Cupido, exampleConversationScript);
        addGameNpc("Lenek", 1, 1, 1, "Graceland", Monsters.Cupido, exampleConversationScript);
        
        
        addLocationItem("Shire", "Oak", "Old Oak at the centre of the big, green forest");
        addLocationItem("Shire", "Wooden House", "Old big place where hobbit lives");
        addLocationItem("Mordor", "Stone", "Many of stones around the castle");
        addLocationItem("Mordor", "Castle", "Castle with four towers and sauron's eye");

    }

    List<Location> getGameLocations() {
        return gameLocations;
    }

    List<Npc> getGameNpc() {
        return gameNpc;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String name) {
        int index = getGameLocationIndex(name);
        if (index != -1) {
            this.startLocation = gameLocations.get(index);
        } else {

            this.startLocation = getGameLocation("Shire");
        }
    }

    void addGameLocation(String name, String description) {
        gameLocations.add(new Location(name, description));
    }

    private int getGameLocationIndex(String name) {
        int indexOfLocation = - 1;
        for (Location location : this.gameLocations) {
            if (location.getShortDescription().equals(name)) {
                indexOfLocation = gameLocations.indexOf(location);
                break;
            }
        }

        return indexOfLocation;
    }

    Location getGameLocation(String name) {
        int index = getGameLocationIndex(name);
        if (index != -1) {
            return gameLocations.get(index);
        } else {
           return null;
            
        }
    }



    void addGameNpc(String name, int npcHealth, int npcStrenght, int npcAgility, String location, Monsters monsterType, ConversationScript conversationScript) {
        Npc monster = Npc.createMonster(monsterType, name, npcHealth, npcStrenght, npcAgility, conversationScript);
        if (monster != null) {
            gameNpc.add(monster);
            getGameLocation(location).addMonster(monster);
        }
    }

    void addGameNpc(String name, int npcHealth, int npcStrenght, int npcAgility, String location, Monsters monsterType) {
        Npc monster = Npc.createMonster(monsterType, name, npcHealth, npcStrenght, npcAgility, new ConversationScript(ConversationScript.DEFAULT));
        if (monster != null) {
            gameNpc.add(monster);
            getGameLocation(location).addMonster(monster);
        }
    }

    public void addExits(String startLocation, String endLocation, Direction direction) {
        if (getGameLocation(startLocation) != null && getGameLocation(endLocation) != null) {
            getGameLocation(startLocation).addLocation(direction, getGameLocation(endLocation));
        }
    }

    private void addLocationItem(String locationName, String itemName, String itemDescription ) {
        Location location = getGameLocation(locationName);
        if (location != null) {
            location.addLocationItem(itemName, itemDescription);
        }
    }
}
