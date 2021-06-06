package my.tasks.kogutenko.task1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
   Task:
       If we use a correctly written arithmetic expression that contains numbers,
       transaction marks, and opening and closing parentheses, discard the numbers and transaction marks
       and then write down the remaining parentheses without spaces between them,
       We call the resulting result the correct bracket expression
       [bracket expression "()(()))" is correct, and "()(" and "())(" is not].

   Find the number of valid bracket expressions that contain N opening and N closing brackets.
   N is entered from the keyboard. N is a non-negative integer.
 */
public class TaskOne {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N;
        boolean circle = true;
        String answer;
        //create the loop for make several counts
        while(circle) {
            //loop for checking correct input value
            //while entering incorrectly that loop continues
            while (true) {
                System.out.print("Enter the amount of numbers: ");
                N = scanner.nextInt();
                scanner.nextLine();
                if (N > 0) {
                    break;
                } else {
                    System.out.println("Enter Enter correct values!!!\nnon-negative!!!");
                }
            }
            System.out.println("Answer is " + countBrackets(N));
            //a loop to check that the answer to a question is correct.
            // check by regular expression
            while (true) {
                System.out.print("Do you want to continue? (1 - yes; 0 - no)\n>>> ");
                Pattern pattern = Pattern.compile("[0-1]{1}");
                answer = scanner.nextLine();
                Matcher matcher = pattern.matcher(answer);
                if (matcher.matches() && Integer.parseInt(answer) == 0) {
                    circle = false;
                    break;
                } else if (matcher.matches() && Integer.parseInt(answer) == 1) {
                    break;
                } else {
                    System.out.println("Enter Enter correct values!!!\n0 or 1!!!");
                }
            }
        }
    }

    static long countBrackets(int n) {
        long [] brackets = new long [n + 1];

        brackets[0] = 1;
        for (int i = 1; i <= n; i++) {
            brackets[i] = 0;
            for (int j = 0; j < i; j++)
                brackets[i] += brackets[j] * brackets[i - 1 - j];
        }
        return brackets[n];
    }
}
