package br.mattsousa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.mattsousa.controller.GameController;
import br.mattsousa.model.GameCharacter;

public class GameControllerTest {
    @Test
    public void testExecuteAttack() {
        GameCharacter bastion = new GameCharacter(Short.valueOf("10"), Short.valueOf("10"));
        GameCharacter faulkner = new GameCharacter(Short.valueOf("10"), Short.valueOf("10"));

        Assertions.assertEquals(Short.valueOf("10"), faulkner.getLifePoints());
        
        GameController.executeAttack(bastion, faulkner);

        Assertions.assertEquals(Short.valueOf("0"), faulkner.getLifePoints());
    }
}
