package br.mattsousa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.mattsousa.model.GameCharacter;

public class GameCharacterTest {
    
    @Test
    public void testIsAlive_shouldReturnFalse(){
        GameCharacter bastion = new GameCharacter("Bastion");
        bastion.setLifePoints(Short.valueOf("0"));
        Assertions.assertFalse(bastion.isAlive());
    }

    @Test
    public void testIsAlive_shouldReturnTrue(){
        GameCharacter bastion = new GameCharacter("Bastion");
        bastion.setLifePoints(Short.valueOf("1"));
        Assertions.assertTrue(bastion.isAlive());
    }

}
