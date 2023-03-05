import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
    Need to show correct answer when incorrect answer finished
    Need to have a way of exiting program
    Need to figure out a way to have answers with spaces (problem line is around 31)
 */

public class Trebek {
    static String question = "";
    static String allAnswers = "";

    static int questionNumber = 0;
    static int answerNumber = 0;

    public static void parseAnswers(){
        /*
        This function will be used to parse the answers that are saved
        in var allAnswers.

        It should be able to lable answers as a, b, c, d and
        be able to identify the correct answer
         */


        // sets allAnswers to an array called answerArray
        // This line prevents answers from having space :(
        // Need to fix it
        String[] answerArray = allAnswers.split(" ");
        int answerWalker = 0;
        int correctAnswer = 0;
        String answerA = "";
        String answerB = "";
        String answerC = "";
        String answerD = "";

        for (String i : answerArray){
            answerWalker += 1;

            // gets rid of first and last parenthesis/bracket
            // then adds each char to its corresponding
            // answer var
            for (int n = 1; n < i.length() -1 ; n++) {
                // checks if first char is [ then gets
                // its answer number
                if (i.charAt(0) == '['){
                    correctAnswer = answerWalker;
                }
                if (answerWalker == 1){
                    answerA += i.charAt(n);
                }
                else if (answerWalker == 2){
                    answerB += i.charAt(n);
                }
                else if (answerWalker == 3){
                    answerC += i.charAt(n);
                }
                else if (answerWalker == 4){
                    answerD += i.charAt(n);
                }
            }
        }

        System.out.println(question);
        System.out.println("a.) " + answerA);
        System.out.println("b.) " + answerB);
        System.out.println("c.) " + answerC);
        System.out.println("d.) " + answerD);

        System.out.println("Enter a, b, c, or d: ");
        Scanner userAnswerObj = new Scanner(System.in);
        String userAnswer = userAnswerObj.nextLine();
        userAnswer.toLowerCase();

        if (userAnswer.equals("a") && correctAnswer == 1){
            System.out.println("Correct!");
        }
        else if (userAnswer.equals("b") && correctAnswer == 2){
            System.out.println("Correct!");
        }
        else if (userAnswer.equals("c") && correctAnswer == 3){
            System.out.println("Correct!");
        }
        else if (userAnswer.equals("d") && correctAnswer == 4){
            System.out.println("Correct!");
        }
        else{
            System.out.println("Incorrect :(");
        }
    }

    public static void parseFile(String fileName) {
        try {
            File tbFile = new File(fileName);
            Scanner reader = new Scanner(tbFile);

            int firstStar = 0;
            int secondStar = 0;
            // if 1, program gets answers
            int getAnswers = 0;

            while (reader.hasNext()){

                // assign var word to each word in Scanner reader
                String word = reader.next();

                if (word.equals("{")) {
                    questionNumber += 1;
                }
                else if (word.equals("*")){
                    // checks to see if it is the first star
                    if (firstStar == 0 && (secondStar == 0 || secondStar == 1)){
                        firstStar = 1;
                        secondStar = 0;
                    }
                    // checks to see if it is the second star
                    else if (secondStar == 0 && firstStar == 1){
                        firstStar = 0;
                        secondStar = 1;
                    }
                }

                // checks if word isn't * but firstStar is 1
                // then adds word to question
                if (!word.equals("*") && firstStar == 1){
                    question += word;
                    question += " ";
                }

                // triggers program to start parsing answers
                if (word.equals(";")){
                    getAnswers = 1;
                }

                // if/elseif logic is used to make
                // word parsing easier and code
                // less complex
                if (word.equals("}")){
                    // resets getAnswer var
                    getAnswers = 0;
                    parseAnswers();
                    question = "";
                    allAnswers = "";
                }

                // puts all answers in allAnswers var
                else if (!word.equals(";") && getAnswers == 1) {
                    allAnswers += word;
                    allAnswers += " ";
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        parseFile("data/QA.tb");
    }
}
