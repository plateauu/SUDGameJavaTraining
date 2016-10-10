
package com.plateauu.sudgame.services;

class HelpCommand implements Command {

    private final String HELP = " "
            + "\n Welcome to SUD GAME v.0.1"
            + "\n Expected parameters: "
            + "\n N (north): moves to north"
            + "\n S (south): moves to south"
            + "\n E (east): moves to east"
            + "\n l (look): gets location's description"
            + "\n W (west): moves to west"
            + "\n k (kill) [monster_name]: attacks to [monster_name]"
            + "\n r (run): run away from the battlefield"
            + "\n stats: shows player's statistics"
            + "\n stats [monster_name]: shows monster's info";
    
    public HelpCommand() {}

    @Override
    public String execute() {
        return HELP;
    }

}
