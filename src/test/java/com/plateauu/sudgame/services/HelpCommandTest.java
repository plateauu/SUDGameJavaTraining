
package com.plateauu.sudgame.services;

import org.junit.Test;
import static org.junit.Assert.*;


public class HelpCommandTest {
    
    @Test
    public void ifHelpHasCome() {
    HelpCommand help = new HelpCommand();
            assertNotNull(help.execute());
        
    }
}
