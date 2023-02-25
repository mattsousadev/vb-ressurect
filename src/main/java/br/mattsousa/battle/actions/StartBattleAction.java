package br.mattsousa.battle.actions;

import br.mattsousa.model.Attack;
import br.mattsousa.model.GameCharacter;

public interface StartBattleAction {
    void onStartBattle(GameCharacter attacker, GameCharacter target, Attack attack, Float hitChance);
}
