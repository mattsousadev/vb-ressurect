package br.mattsousa.battle.actions;

import br.mattsousa.model.Attack;
import br.mattsousa.model.GameCharacter;

public interface SelectAttackAction {
    Attack onSelectAttack(GameCharacter attacker);
}
