package br.mattsousa.user;

import java.util.Scanner;

public class SystemInUserInput implements UserInput {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getUserInput() {
        return scanner.nextLine();
    }
    
}
