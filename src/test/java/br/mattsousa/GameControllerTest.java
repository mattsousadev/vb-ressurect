package br.mattsousa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.mattsousa.controller.GameController;
import br.mattsousa.model.GameCharacter;

public class GameControllerTest {
    @Test
    public void testExecuteAttack() {
        GameCharacter bastion = new GameCharacter("Bastion", Short.valueOf("10"), Short.valueOf("10"));
        GameCharacter faulkner = new GameCharacter("Faulkner", Short.valueOf("10"), Short.valueOf("10"));

        Assertions.assertEquals(Short.valueOf("10"), faulkner.getLifePoints());
        
        GameController.executeAttack(bastion, faulkner);

        Assertions.assertEquals(Short.valueOf("0"), faulkner.getLifePoints());
    }

    @Test
    public void testExecuteAttack_shouldPreventNegativeLifePoints() {
        GameCharacter bastion = new GameCharacter("Bastion", Short.valueOf("10"), Short.valueOf("10"));
        GameCharacter faulkner = new GameCharacter("Faulkner", Short.valueOf("1"), Short.valueOf("10"));

        Assertions.assertEquals(Short.valueOf("1"), faulkner.getLifePoints());
        
        GameController.executeAttack(bastion, faulkner);

        Assertions.assertEquals(Short.valueOf("0"), faulkner.getLifePoints());
    }

    @Test
    public void testExecuteAttack_shouldNotRunIfAttackerIsNotAlive() {
        GameCharacter bastion = new GameCharacter("Bastion", Short.valueOf("0"), Short.valueOf("10"));
        GameCharacter faulkner = new GameCharacter("Faulkner", Short.valueOf("1"), Short.valueOf("10"));

        Assertions.assertEquals(Short.valueOf("1"), faulkner.getLifePoints());
        
        GameController.executeAttack(bastion, faulkner);

        Assertions.assertEquals(Short.valueOf("1"), faulkner.getLifePoints());
    }

    @Test
    public void testExecuteAttack_shouldIncreaseExperiencePoints() {
        GameCharacter bastion = new GameCharacter("Bastion", Short.valueOf("10"), Short.valueOf("10"));
        GameCharacter faulkner = new GameCharacter("Faulkner", Short.valueOf("10"), Short.valueOf("10"));

        Integer beforeExperiencePoints = bastion.getExperiencePoints();
        
        GameController.executeAttack(bastion, faulkner);

        Assertions.assertNotEquals(beforeExperiencePoints, bastion.getExperiencePoints());
    }

    @Test
    public void testCalculateHitChance_shouldGet50PercentChance() {
        GameCharacter bastion = new GameCharacter("Bastion");
        GameCharacter faulkner = new GameCharacter("Faulkner");
        Assertions.assertEquals(GameController.calculateHitChance(bastion, faulkner), Float.valueOf("0.5"));
    }
}
