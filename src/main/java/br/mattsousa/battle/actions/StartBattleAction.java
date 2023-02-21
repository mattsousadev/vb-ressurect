package br.mattsousa.battle.actions;

import br.mattsousa.model.GameCharacter;

public interface StartBattleAction {
    void onStartBattle(GameCharacter attacker, GameCharacter target, Float hitChance);
}
