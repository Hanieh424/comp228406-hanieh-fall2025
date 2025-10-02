package exercise1;

import javax.swing.*;
import java.util.Random;

public class Test {
    //Questions
    private final String[] questions = {
            "Q1. which data type is used to store whole numbers in Java? ",
            "Q2. Which keyword is used to create a class in Java?",
            "Q3. Which method is the entry point of any Java program?",
            "Q4. Which symbol is used to end a java statement?",
            "Q5. Which package is used for input dialogs like JOptionPane?"

    };
    //Options
    private final String[][] options = {
            {"1. double", "2.int", "3.String", "4.boolean"},
            {"1. function", "2.define", "3.class", "4.void"},
            {"1. start()", "2.main()", "3.run()", "4.init()"},
            {"1. :", "2. ,", "3. ;", "4. ."},
            {"1. java.until", "2. java.swing", "3. javax.swing", "4.java.io"}
    };

    //Correct answers
    private final int[] correctAnswers = {2, 3, 2, 3, 3};

    private int correctCount = 0;
    private int incorrectCount = 0;
    private final Random random = new Random();

    public void simulateQuestion(int qIndex) {
        StringBuilder question = new StringBuilder(questions[qIndex] + "\n");
        for (String opt : options[qIndex]) {
            question.append(opt).append("\n");
        }
        String answerStr = JOptionPane.showInputDialog(null, question.toString(), "Simple Test", JOptionPane.QUESTION_MESSAGE);


        if (answerStr == null) {
            JOptionPane.showMessageDialog(null, "Test ended early.");
            System.exit(0);
        }


        try {
            int answer = Integer.parseInt(answerStr);
            checkAnswer(qIndex, answer);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Enter a number 1-4.");
            simulateQuestion(qIndex);

        }
    }
    public void checkAnswer(int qIndex, int answer) {
        if (answer == correctAnswers[qIndex]) {
            correctCount++;
            JOptionPane.showMessageDialog(null, generateMessage(true));
        } else {
            incorrectCount++;
            JOptionPane.showMessageDialog(null, generateMessage(false) +
                    "\nCorrect Answer: " + options[qIndex][correctAnswers[qIndex] - 1]);
        }
    }
    public String generateMessage(boolean correct){
            int ran = random.nextInt(4);

        if (correct) {
                switch (ran) {
                    case 0: return "Excellent!";
                    case 1: return "Good!";
                    case 2: return "Keep up the good work!";
                    default: return "Nice work!";

                }
            }else {
                switch (ran){

                    case 0: return "No, Please try again" ;
                    case 1: return "Wrong. Try once more";
                    case 2: return "Don't give up!";
                    default:return "Keep trying...";

                }
            }



    }
        public void inputAnswer(){
            for (int i = 0; i < questions.length; i++) {
                simulateQuestion(i);
            }
            int total = correctCount + incorrectCount;
            double percentage = (correctCount * 100.0) / total;

            JOptionPane.showMessageDialog(null,"Test Completed!\n" +
                    "Correct Answers: " + correctCount + "\n"+
                    "Incorrect Answers: " + incorrectCount + "\n" +
                    "Percentage: " + percentage + "%");

        }

        public static void main(String[] args){
            Test test = new Test();
            test.inputAnswer();
        }
    }



