package br.mattsousa;

import java.util.Locale;
import java.util.ResourceBundle;

import br.mattsousa.controller.GameController;
import br.mattsousa.model.GameCharacter;
import br.mattsousa.util.ChanceUtil;

public class App {

    private static Locale locale = Locale.getDefault();
    private static ResourceBundle messages = ResourceBundle.getBundle("message", locale);

    public static void main(String[] args) throws Exception {
        GameCharacter bastion = new GameCharacter(Short.valueOf("10"), Short.valueOf("10"));
        GameCharacter faulkner = new GameCharacter(Short.valueOf("10"), Short.valueOf("10"));

        Float hitChance = GameController.calculateHitChance(bastion, faulkner);
        System.out.println(messages.getString("hit.chance").formatted(hitChance));
        if (ChanceUtil.isHit(hitChance)) {
            
            GameController.executeAttack(bastion, faulkner);
            
            if (!faulkner.isAlive()) {
                System.out.println(messages.getString("game.win"));
            } else {
                System.out.println(messages.getString("game.lose"));
            }
            
        } else {
            System.out.println(messages.getString("hit.miss"));
        }
    }

}
