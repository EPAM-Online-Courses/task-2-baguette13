package efs.task.syntax;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class GuessNumberGame {
    private final int M;
    private final int L;
    private final int number;
    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            this.M = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println("'" + argument + "' " + UsefulConstants.WRONG_ARGUMENT + "- to nie liczba");
            throw new IllegalArgumentException();
        }

        if (M < 1 || M > UsefulConstants.MAX_UPPER_BOUND) {
            System.out.println(argument + " " + UsefulConstants.WRONG_ARGUMENT + "- jest miesci sie w zakresie <1,400>");
            throw new IllegalArgumentException();
        }

        Random rand = new Random();
        this.number = rand.nextInt(M) + 1;
        this.L = (int) (Math.floor(Math.log(M) / Math.log(2)) + 1);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        int trial=0;
        int intFromUser;

        String guess;
        String[] tab = new String[L];
        Arrays.fill(tab,".");

        System.out.println("Zgadnij liczbe z zakresu <1," + M +">");

        while(true){
            if(trial == L){
                System.out.println(UsefulConstants.UNFORTUNATELY + " nie udało Ci się zgadnąć liczby " + number);
                break;
            }
            tab[trial] = "*";
            printAttempts(tab);
            System.out.println(UsefulConstants.GIVE_ME + " liczbę :");
            guess = scanner.next();

            try {
                intFromUser = Integer.parseInt(guess);
            } catch (NumberFormatException e) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                scanner.nextLine();
                trial++;
                continue;
            }

            if (intFromUser > number) {
                System.out.println(UsefulConstants.TO_MUCH);
            } else if (intFromUser < number) {
                System.out.println(UsefulConstants.TO_LESS);
            } else {
                System.out.println(UsefulConstants.YES + "!");
                System.out.println(UsefulConstants.CONGRATULATIONS + ", " + (trial + 1) + " - tyle zajelo Ci odgadniecie liczby");
                break;
            }
            trial++;
        }
    }

    private void printAttempts(String[] tab) {
        System.out.print("Proba: [");
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i]);
        }
        System.out.println("] ");
    }
}


