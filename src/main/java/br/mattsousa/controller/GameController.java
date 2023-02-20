package br.mattsousa.controller;

import br.mattsousa.model.GameCharacter;

public class GameController {
    public static void executeAttack(GameCharacter attacker, GameCharacter target) {
        if (!attacker.isAlive() || !target.isAlive()){
            return;
        }
        
        Short damagedLifePoints = Integer.valueOf(target.getLifePoints() - attacker.getAttackPoints()).shortValue();
        if (damagedLifePoints < 0){
            damagedLifePoints = 0;
        }

        Integer increasedExperiencePoints = attacker.getExperiencePoints() + 1;
        
        attacker.setExperiencePoints(increasedExperiencePoints);
        target.setLifePoints(damagedLifePoints);
    }

    public static Float calculateHitChance(GameCharacter attacker, GameCharacter target){
        Float output = 0.0f;
        
        if(attacker.getExperiencePoints() > target.getExperiencePoints()){
            output = 0.6f;
        }else if(attacker.getExperiencePoints() < target.getExperiencePoints()){
            output = 0.4f;
        }else{
            output = 0.5f;
        }

        return output;
    }
}
