package br.mattsousa;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import br.mattsousa.battle.BattleLogic;
import br.mattsousa.controller.GameController;
import br.mattsousa.enums.CharacterStatus;
import br.mattsousa.model.GameCharacter;
import br.mattsousa.user.SystemInUserInput;
import br.mattsousa.user.UserInput;

public class App {

    private static Locale locale = Locale.getDefault();
    private static ResourceBundle messages = ResourceBundle.getBundle("message", locale);
    private static UserInput userInput = new SystemInUserInput();

    public static void main(String[] args) throws Exception {
        Integer maxRound = 50;

        String heroName = "Bastion";
        String villainName = "Faulkner";

        GameCharacter bastion = new GameCharacter(heroName, Short.valueOf("100"), Short.valueOf("32"));
        GameCharacter faulkner = new GameCharacter(villainName, Short.valueOf("90"), Short.valueOf("10"));

        HashMap<String, GameCharacter> heroParty = new HashMap<>();
        HashMap<String, GameCharacter> villainParty = new HashMap<>();

        heroParty.put(bastion.getName(), bastion);
        villainParty.put(faulkner.getName(), faulkner);
        Boolean willAttack = true;

        BattleLogic turnBattle = new BattleLogic(heroParty, villainParty);

        while (maxRound > 0 && !(heroParty.isEmpty() || villainParty.isEmpty())) {
            final Integer currentMax = maxRound;
            
            turnBattle.startTurn((GameCharacter current) -> {
                System.out.println(messages.getString("battle.turn").formatted(currentMax));
                if (current.getStaminaPoints() == 100) {
                    current.setStatus(CharacterStatus.NORMAL);
                }
                if (current.getStatus() == CharacterStatus.STUNNED) {
                    System.out.println(messages.getString("battle.stunned").formatted(current.getName()));
                }
                Byte newStamina = (byte) (current.getStaminaPoints() + GameController.STAMINA_INCREASE);
                if (newStamina >= 100) {
                    newStamina = 100;
                }
                current.setStaminaPoints(newStamina);
            });

            if (turnBattle.currentCanPlay()) {
                if (turnBattle.isPlayerTurn(heroName)) {
                    System.out.print(messages.getString("user.input.skip.attack"));
                    willAttack = !userInput.getUserInput().startsWith("1");
                } else {
                    willAttack = true;
                }

                if (willAttack) {

                    String targetName = turnBattle.isPlayerTurn(heroName) ? villainName : heroName;

                    turnBattle.selectTarget(targetName);

                    turnBattle.startBattle((GameCharacter attacker, GameCharacter target, Float hitChance) -> {
                        System.out.println(
                                messages.getString("battle.player-status").formatted("Attacker", attacker.getName(),
                                        attacker.getLifePoints(), attacker.getStaminaPoints()));
                        System.out.println(
                                messages.getString("battle.player-status").formatted("Target", target.getName(),
                                        target.getLifePoints(), target.getStaminaPoints()));

                        System.out.println(messages.getString("hit.chance").formatted(hitChance * 100));
                    });

                    turnBattle.endBattle(
                            (GameCharacter attacker, GameCharacter target, Boolean isHit, Integer experienceEarned) -> {
                                if (isHit) {
                                    System.out.println(messages.getString("hit.hit"));
                                    System.out.println(
                                            messages.getString("battle.experience-earn").formatted(experienceEarned));
                                    System.out.println(
                                            messages.getString("hit.damage").formatted(attacker.getAttackPoints()));
                                } else {
                                    System.out.println(
                                            messages.getString("hit.miss").formatted(attacker.getName(),
                                                    target.getName()));
                                }
                            });

                    turnBattle.endTurn((GameCharacter attacker, GameCharacter target) -> {
                        if (!target.isAlive()) {
                            System.out.println(messages.getString("battle.defeated").formatted(target.getName()));
                        }
                    });

                } else {
                    System.out.println(messages.getString("battle.skip"));
                }
            }

            System.out.println();
            maxRound--;
        }

        // if(turnBattle.playerWon()){
        if (heroParty.isEmpty()) {
            System.out.println(messages.getString("game.lose"));
        }
        // else if(turnBattle.cpuWon()) {
        else if (villainParty.isEmpty()) {
            System.out.println(messages.getString("game.win"));
        } else {
            System.out.println(messages.getString("game.draw"));
        }

    }

}
