package com.plateauu.sudgame.services;

public class NullCommand implements Command {

    @Override
    public String execute() {
        return "No such command";
    }
    
        
}
