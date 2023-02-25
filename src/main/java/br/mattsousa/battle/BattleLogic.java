package br.mattsousa.battle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import br.mattsousa.battle.actions.EndBattleAction;
import br.mattsousa.battle.actions.EndTurnAction;
import br.mattsousa.battle.actions.SelectAttackAction;
import br.mattsousa.battle.actions.StartBattleAction;
import br.mattsousa.battle.actions.StartTurnAction;
import br.mattsousa.controller.GameController;
import br.mattsousa.enums.CharacterStatus;
import br.mattsousa.model.Attack;
import br.mattsousa.model.GameCharacter;
import br.mattsousa.util.ChanceUtil;

public class BattleLogic {
    private HashMap<String, GameCharacter> firstParty;
    private HashMap<String, GameCharacter> secondParty;
    private Queue<GameCharacter> charactersSequence;

    private GameCharacter current;
    private Attack selectedAttack;
    private GameCharacter target;
    private Float hitChance;
    private Boolean isHit;

    public BattleLogic(HashMap<String, GameCharacter> firstParty, HashMap<String, GameCharacter> secondParty) {
        this.firstParty = firstParty;
        this.secondParty = secondParty;
        this.charactersSequence = new LinkedList<>();
        this.charactersSequence.addAll(firstParty.values());
        this.charactersSequence.addAll(secondParty.values());

        current = null;
        target = null;
        hitChance = null;
        isHit = false;
    }

    public void startTurn(StartTurnAction action) {
        current = charactersSequence.poll();

        charactersSequence.add(current);

        if (action != null) {
            action.onStartTurn(current);
        }
    }

    public void selectAttack(SelectAttackAction action){
        selectedAttack = action.onSelectAttack(current);
    }
    
    public void startBattle(StartBattleAction startBattleAction) {
        Byte newAttackerStamina = (byte) (current.getStaminaPoints() - selectedAttack.getStaminaCost());
        if (newAttackerStamina <= 0) {
            newAttackerStamina = 0;
            current.setStatus(CharacterStatus.STUNNED);
        }
        current.setStaminaPoints(newAttackerStamina);

        if (target.getStatus() == CharacterStatus.NORMAL) {
            Byte newTargetStamina = (byte) (target.getStaminaPoints() - 34);
            if (newTargetStamina <= 0) {
                newTargetStamina = 0;
                target.setStatus(CharacterStatus.STUNNED);
            }
            target.setStaminaPoints(newTargetStamina);
        }

        hitChance = GameController.calculateHitChance(current, target);
        startBattleAction.onStartBattle(current, target, selectedAttack, hitChance);
        isHit = ChanceUtil.isHit(hitChance);
    }

    public void endBattle(EndBattleAction action) {
        Integer experienceEarned = 0;
        if (isHit) {
            Integer oldExperience = current.getExperiencePoints();
            GameController.executeAttack(current, target, selectedAttack);
            Integer newExperience = current.getExperiencePoints();
            experienceEarned = newExperience - oldExperience;
        }
        action.onEndBattleAction(current, target, selectedAttack, isHit, experienceEarned);
    }

    public void endTurn(EndTurnAction action) {
        if (!target.isAlive()) {
            HashMap<String, GameCharacter> targetParty = getParty(target.getName());
            targetParty.remove(target.getName());
        } else {
            charactersSequence.add(target);
        }
        action.onEndTurn(current, target);
    }

    public void selectTarget(String targetName) {
        target = firstParty.get(targetName);
        if (target == null) {
            target = secondParty.get(targetName);
        }
    }

    public boolean isPlayerTurn(String name) {
        return current.getName().equalsIgnoreCase(name);
    }

    public HashMap<String, GameCharacter> getParty(String targetName) {
        if (firstParty.containsKey(targetName))
            return firstParty;
        if (secondParty.containsKey(targetName))
            return secondParty;
        return null;
    }

    public GameCharacter getCurrent() {
        return current;
    }

    public boolean currentCanPlay() {
        return current.getStatus() == CharacterStatus.NORMAL;
    }


    public boolean partyWon(HashMap<String, GameCharacter> party) {
        if(party == firstParty) return firstParty.size() > 0 && secondParty.size() == 0;
        else if (party == secondParty) return secondParty.size() > 0 && firstParty.size() == 0;
        return false;
    }

}
