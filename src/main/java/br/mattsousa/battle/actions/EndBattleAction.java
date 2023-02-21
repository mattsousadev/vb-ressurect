package br.mattsousa.battle.actions;

import br.mattsousa.model.GameCharacter;

public interface EndBattleAction {
    void onEndBattleAction(GameCharacter attacker, GameCharacter target, Boolean isHit,Integer experienceEarned);
}
