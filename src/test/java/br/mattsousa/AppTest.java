package br.mattsousa;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    @DisplayName("A simple test")
    public void test() {
        int result = 2 + 2;
        Assertions.assertEquals(4, result);
    }
}