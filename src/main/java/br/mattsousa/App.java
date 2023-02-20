package br.mattsousa;

import java.util.Random;

import br.mattsousa.controller.GameController;
import br.mattsousa.model.GameCharacter;

public class App {
    public static void main(String[] args) throws Exception {
        GameCharacter bastion = new GameCharacter(Short.valueOf("10"), Short.valueOf("10"));
        GameCharacter faulkner = new GameCharacter(Short.valueOf("10"), Short.valueOf("10"));

        Float hitChance = GameController.calculateHitChance(bastion, faulkner);
        System.out.println("Hit chance: %.2f".formatted(hitChance));
        Random random = new Random();
        Double chance = random.nextDouble();
        System.out.println("Chance got!: %.2f".formatted(chance));
        if (chance <= hitChance) {
            GameController.executeAttack(bastion, faulkner);
            if (!faulkner.isAlive()) {
                System.out.println("You win");
            } else {
                System.out.println("Faulkner: %d".formatted(faulkner.getLifePoints()));
                System.out.println("You lose");
            }
        } else {
            System.out.println("Attack miss!");
        }
    }

}
