package br.mattsousa;

import br.mattsousa.controller.GameController;
import br.mattsousa.model.GameCharacter;
import br.mattsousa.util.ChanceUtil;

public class App {
    public static void main(String[] args) throws Exception {
        GameCharacter bastion = new GameCharacter(Short.valueOf("10"), Short.valueOf("10"));
        GameCharacter faulkner = new GameCharacter(Short.valueOf("10"), Short.valueOf("10"));

        Float hitChance = GameController.calculateHitChance(bastion, faulkner);
        System.out.println("Hit chance: %.2f".formatted(hitChance));
        if (ChanceUtil.isHit(hitChance)) {
            
            GameController.executeAttack(bastion, faulkner);
            
            if (!faulkner.isAlive()) {
                System.out.println("You win");
            } else {
                System.out.println("You lose");
            }
            
        } else {
            System.out.println("Attack miss!");
        }
    }

}
