package com.plateauu.sudgame.services;

public class NullCommand implements Command {

    @Override
    public String execute() {
        return "Use h (help) for help";
    }
    
        
}
