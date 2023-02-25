package br.mattsousa;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import br.mattsousa.battle.BattleLogic;
import br.mattsousa.controller.GameController;
import br.mattsousa.enums.CharacterStatus;
import br.mattsousa.model.Attack;
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

        Attack slash = new Attack("Slash", Short.valueOf("30"), Byte.valueOf("10"));
        Attack soaringDance = new Attack("Soaring dance", Short.valueOf("90"), Byte.valueOf("90"));

        GameCharacter bastion = new GameCharacter(heroName, Short.valueOf("100"));
        GameCharacter faulkner = new GameCharacter(villainName, Short.valueOf("90"));

        bastion.getAttacks().add(slash);
        bastion.getAttacks().add(soaringDance);
        faulkner.getAttacks().add(slash);

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

                    if (turnBattle.isPlayerTurn(heroName)) {
                        turnBattle.selectAttack(attacker -> {
                            Attack output = null;
                            while (true) {
                                for (int i = 0; i < attacker.getAttacks().size(); i++) {
                                    Attack attack = attacker.getAttacks().get(i);
                                    System.out.println("%d - %s | AT: %d - ST: %d".formatted(
                                            i + 1, attack.getName(), attack.getAttackPoints(),
                                            attack.getStaminaCost()));
                                }
                                System.out.print("Select a attack:");
                                String selected = userInput.getUserInput().trim();
                                try {
                                    Integer indexSelected = Integer.parseInt(selected) - 1;
                                    output = attacker.getAttacks().get(indexSelected);
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Error on selecting. Try again.");
                                    continue;
                                }
                            }
                            return output;
                        });
                    }else{
                        turnBattle.selectAttack((attacker -> attacker.getAttacks().get(0)));
                    }

                    turnBattle.startBattle(
                            (GameCharacter attacker, GameCharacter target, Attack selectedAttack, Float hitChance) -> {
                                System.out.println(
                                        messages.getString("battle.player-status").formatted("Attacker",
                                                attacker.getName(),
                                                attacker.getLifePoints(), attacker.getStaminaPoints()));
                                System.out.println(
                                        messages.getString("battle.player-status").formatted("Target", target.getName(),
                                                target.getLifePoints(), target.getStaminaPoints()));

                                System.out.println(messages.getString("hit.chance").formatted(hitChance * 100));
                                System.out.println();
                            });

                    turnBattle.endBattle(
                            (GameCharacter attacker, GameCharacter target, Attack selectedAttack, Boolean isHit,
                                    Integer experienceEarned) -> {
                                if (isHit) {
                                    System.out.println(messages.getString("hit.hit"));
                                    System.out.println(
                                            messages.getString("battle.experience-earn").formatted(experienceEarned));
                                    System.out.println(
                                            messages.getString("hit.damage")
                                                    .formatted(selectedAttack.getAttackPoints()));
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

        if (turnBattle.partyWon(villainParty)) {
            System.out.println(messages.getString("game.lose"));
        } else if (turnBattle.partyWon(heroParty)) {
            System.out.println(messages.getString("game.win"));
        } else {
            System.out.println(messages.getString("game.draw"));
        }

    }

}
