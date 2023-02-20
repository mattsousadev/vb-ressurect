package br.mattsousa.util;

import java.util.Random;

public class ChanceUtil {
    public static boolean isHit(Float likelihood) {
        return new Random().nextDouble() <= likelihood;
    }
}
