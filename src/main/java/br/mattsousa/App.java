package br.mattsousa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.ResourceBundle;

import br.mattsousa.controller.GameController;
import br.mattsousa.model.GameCharacter;
import br.mattsousa.util.ChanceUtil;

public class App {

    private static Locale locale = Locale.getDefault();
    private static ResourceBundle messages = ResourceBundle.getBundle("message", locale);

    public static void main(String[] args) throws Exception {
        Integer maxRound = 50;

        String heroName = "Bastion";
        String villainName = "Faulkner";

        GameCharacter bastion = new GameCharacter(heroName, Short.valueOf("100"), Short.valueOf("10"));
        GameCharacter faulkner = new GameCharacter(villainName, Short.valueOf("90"), Short.valueOf("10"));

        HashMap<String, GameCharacter> heroParty = new HashMap<>();
        HashMap<String, GameCharacter> villainParty = new HashMap<>();

        Queue<GameCharacter> charactersSequence = new LinkedList<>();

        charactersSequence.add(bastion);
        charactersSequence.add(faulkner);

        heroParty.put(bastion.getName(), bastion);
        villainParty.put(faulkner.getName(), faulkner);

        while(maxRound > 0 && !(heroParty.isEmpty() || villainParty.isEmpty())){
            System.out.println(messages.getString("battle.turn").formatted(maxRound));
            
            GameCharacter attacker = charactersSequence.poll();
            
            System.out.println(messages.getString("battle.name.and.life").formatted("Attacker", attacker.getName(), attacker.getLifePoints()));

            HashMap<String, GameCharacter> targetParty = heroParty.containsKey(attacker.getName()) ? villainParty : heroParty;
            
            GameCharacter target = targetParty.get(heroParty.containsKey(attacker.getName()) ? villainName : heroName);
            
            System.out.println(messages.getString("battle.name.and.life").formatted("Target", target.getName(), target.getLifePoints()));

            Float hitChance = GameController.calculateHitChance(attacker, target);
            
            System.out.println(messages.getString("battle.attacking"));
            if (ChanceUtil.isHit(hitChance)) {
                System.out.println(messages.getString("hit.hit"));
                GameController.executeAttack(attacker, target);
                System.out.println(messages.getString("hit.damage").formatted(attacker.getAttackPoints()));
            }else{
                System.out.println(messages.getString("hit.miss").formatted(attacker.getName(), target.getName()));
            }

            if(!target.isAlive()){
                System.out.println(messages.getString("battle.defeated").formatted(target.getName()));
                targetParty.remove(target.getName());
            } else {
                charactersSequence.add(target);
            }
            
            System.out.println();
            maxRound--;
        }

        if(heroParty.isEmpty()){
            System.out.println(messages.getString("game.lose"));
        }else if(villainParty.isEmpty()){
            System.out.println(messages.getString("game.win"));
        }else{
            System.out.println(messages.getString("game.draw"));
        }

    }

}
