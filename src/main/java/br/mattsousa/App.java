package br.mattsousa;

import br.mattsousa.model.GameCharacter;

public class App {
    public static void main(String[] args) throws Exception {
        GameCharacter bastion = new GameCharacter(Short.valueOf("10"), Short.valueOf("10"));
        GameCharacter faulkner = new GameCharacter(Short.valueOf("10"), Short.valueOf("10"));

        GameController.executeAttack(bastion, faulkner);

        if (!faulkner.isAlive()) {
            System.out.println("You win");
        } else {
            System.out.println("Faulkner: %d".formatted(faulkner.getLifePoints()));
            System.out.println("You lose");
        }
    }

}
