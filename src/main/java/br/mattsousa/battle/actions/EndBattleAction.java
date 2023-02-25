package br.mattsousa.battle.actions;

import br.mattsousa.model.Attack;
import br.mattsousa.model.GameCharacter;

public interface EndBattleAction {
    void onEndBattleAction(GameCharacter attacker, GameCharacter target, Attack attack, Boolean isHit,Integer experienceEarned);
}
