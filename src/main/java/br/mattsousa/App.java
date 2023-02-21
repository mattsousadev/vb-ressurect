package br.mattsousa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Scanner;

import br.mattsousa.controller.GameController;
import br.mattsousa.model.GameCharacter;
import br.mattsousa.util.ChanceUtil;

public class App {

    private static Locale locale = Locale.getDefault();
    private static ResourceBundle messages = ResourceBundle.getBundle("message", locale);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Integer maxRound = 50;

        String heroName = "Bastion";
        String villainName = "Faulkner";

        GameCharacter bastion = new GameCharacter(heroName, Short.valueOf("100"), Short.valueOf("32"));
        GameCharacter faulkner = new GameCharacter(villainName, Short.valueOf("90"), Short.valueOf("10"));

        HashMap<String, GameCharacter> heroParty = new HashMap<>();
        HashMap<String, GameCharacter> villainParty = new HashMap<>();

        Queue<GameCharacter> charactersSequence = new LinkedList<>();

        charactersSequence.add(bastion);
        charactersSequence.add(faulkner);

        heroParty.put(bastion.getName(), bastion);
        villainParty.put(faulkner.getName(), faulkner);
        Boolean willAttack = true;
        // TurnBattle turnBattle = new TurnBattle(heroParty, villainName, charactersSequence);
        while (maxRound > 0 && !(heroParty.isEmpty() || villainParty.isEmpty())) {
            // turnBattle.startTurn();
            System.out.println(messages.getString("battle.turn").formatted(maxRound));
            GameCharacter attacker = charactersSequence.poll();
            charactersSequence.add(attacker);

            // choose to attack or not
            if (heroParty.containsKey(attacker.getName())) {
                // if (turnBattle.isPlayerTurn()) {
                System.out.print(messages.getString("user.input.skip.attack"));
                willAttack = !scanner.nextLine().startsWith("1");
                // String userInput = userController.getUserInput();
            } else {
                willAttack = true;
            }

            if (willAttack) {
                // get target character
                // String targetName = userController.getUserInput();
                // HashMap<String, GameCharacter> targetParty = turnBattle.getParty(targetName);
                // GameCharacter target = targetParty.get(targetName);
                HashMap<String, GameCharacter> targetParty = heroParty.containsKey(attacker.getName()) ? villainParty
                        : heroParty;
                GameCharacter target = targetParty
                        .get(heroParty.containsKey(attacker.getName()) ? villainName : heroName);

                // turnBattle.startBattle(attacker, target, hitChance);
                System.out.println(messages.getString("battle.name.and.life").formatted("Attacker", attacker.getName(),
                        attacker.getLifePoints()));
                System.out.println(messages.getString("battle.name.and.life").formatted("Target", target.getName(),
                        target.getLifePoints()));
                
                Float hitChance = GameController.calculateHitChance(attacker, target);
                System.out.println(messages.getString("hit.chance").formatted(hitChance*100));

                // check if the attack hit or miss
                Boolean isHit = ChanceUtil.isHit(hitChance);
                // turnBattle.endBattle(attacker, target, isHit, experienceEarned);
                if (isHit) {
                    Integer oldExperience = attacker.getExperiencePoints();
                    GameController.executeAttack(attacker, target);
                    Integer newExperience = attacker.getExperiencePoints();

                    // attack hitted
                    System.out.println(messages.getString("hit.hit"));
                    System.out.println(
                            messages.getString("battle.experience-earn").formatted(newExperience - oldExperience));
                    System.out.println(messages.getString("hit.damage").formatted(attacker.getAttackPoints()));
                } else {
                    // attack missed!
                    System.out.println(messages.getString("hit.miss").formatted(attacker.getName(), target.getName()));
                }

                // before end turn
                // turnBattle.endTurn(attacker, target)
                if (!target.isAlive()) {
                    System.out.println(messages.getString("battle.defeated").formatted(target.getName()));
                    targetParty.remove(target.getName());
                } else {
                    charactersSequence.add(target);
                }
            } else {
                System.out.println(messages.getString("battle.skip"));
            }

            System.out.println();
            maxRound--;
        }

        //if(turnBattle.playerWon()){
        if (heroParty.isEmpty()) {
            System.out.println(messages.getString("game.lose"));
        }
        //else if(turnBattle.cpuWon()) {
        else if (villainParty.isEmpty()) {
            System.out.println(messages.getString("game.win"));
        } else {
            System.out.println(messages.getString("game.draw"));
        }

    }

}
