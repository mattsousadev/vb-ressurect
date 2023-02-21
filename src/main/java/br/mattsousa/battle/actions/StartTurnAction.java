package br.mattsousa.battle.actions;

import br.mattsousa.model.GameCharacter;

public interface StartTurnAction {
    void onStartTurn(GameCharacter current);
}
