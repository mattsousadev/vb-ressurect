package br.mattsousa.battle.actions;

import br.mattsousa.model.GameCharacter;

public interface EndTurnAction {
    void onEndTurn(GameCharacter attacker, GameCharacter target);
}
