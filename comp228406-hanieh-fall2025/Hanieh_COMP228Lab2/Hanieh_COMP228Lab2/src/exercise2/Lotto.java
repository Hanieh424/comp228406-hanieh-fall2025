package exercise2;

import javax.swing.*;
import java.io.OptionalDataException;
import java.util.Random;

public class Lotto {
    private int[] numbers = new int[3];


public Lotto(){
    Random random = new Random();
    for (int i = 0; i < numbers.length; i++) {
        numbers[i] = random.nextInt(9) + 1;
    }
}

public int[] getNumbers() {
    return numbers;
}

public static void main(String[] args) {
    String input = JOptionPane.showInputDialog(
            null,
            "Pick a number between 3 and 27:",
            "Lotto Game",
            JOptionPane.QUESTION_MESSAGE
    );
    if (input == null) {
        JOptionPane.showMessageDialog(null, "Game cancelled");
        System.exit(0);
    }
    int userChoice = Integer.parseInt(input);
    boolean userWon = false;

    for (int i =1;i <= 5; i++) {
        Lotto lotto = new Lotto();
        int[] numb = lotto.getNumbers();
        int sum = numb[0] + numb[1] + numb[2];

        JOptionPane.showMessageDialog(
                null,
                "Roll" + i + ": " + numb[0] + "+" + numb[1] + "+" + numb[2] + "=" + sum,
                "Lotto Result",
                JOptionPane.INFORMATION_MESSAGE
        );

        if (sum == userChoice) {
            JOptionPane.showMessageDialog(null, "You won!");
            userWon = true;
            break;
        }
    }
    if(!userWon){
        JOptionPane.showMessageDialog(null, "Computer won! Better luck next time");

    }
    System.exit(0);
}
}
