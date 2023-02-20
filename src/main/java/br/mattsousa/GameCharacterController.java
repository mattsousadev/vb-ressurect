package br.mattsousa;

import br.mattsousa.model.GameCharacter;

public class GameCharacterController {
    public static void executeAttack(GameCharacter attacker, GameCharacter target) {
        Short damagedLifePoints = Integer.valueOf(target.getLifePoints() - attacker.getAttackPoints()).shortValue();
        if (damagedLifePoints < 0){
            damagedLifePoints = 0;
        }
        target.setLifePoints(damagedLifePoints);
    }   
}
