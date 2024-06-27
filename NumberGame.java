import java.util.Scanner;
import java.util.Random;
public class NumberGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final int MAX_NUMBER = 100;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int rounds = 0;
        int totalAttempts = 0;
        int wins = 0;
        int losses = 0;
        boolean playAgain;
        do {
            rounds++;
            int numberToGuess = random.nextInt(MAX_NUMBER) + 1;
            int attempts = 0;
            boolean hasGuessedCorrectly = false;
            System.out.println("=== Round " + rounds + " ===");
            System.out.println("Guess the number between 1 and " + MAX_NUMBER + ".");
            while (attempts < MAX_ATTEMPTS && !hasGuessedCorrectly) {
                int userGuess = getUserGuess(scanner, attempts + 1);
                attempts++;
                if (userGuess == numberToGuess) {
                    hasGuessedCorrectly = true;
                    wins++;
                    System.out.println("Congratulations! You've guessed the correct number.");
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }
            if (!hasGuessedCorrectly) {
                losses++;
                System.out.println("Sorry, you've run out of attempts. The correct number was " + numberToGuess + ".");
            }
            totalAttempts += attempts;
            printRoundSummary(rounds, attempts, hasGuessedCorrectly);
            playAgain = askToPlayAgain(scanner);
        } while (playAgain);
        printFinalSummary(rounds, totalAttempts, wins, losses);
        scanner.close();
    }
    private static int getUserGuess(Scanner scanner, int attempt) {
        int guess = -1;
        while (guess < 1 || guess > MAX_NUMBER) {
            System.out.print("Attempt " + attempt + ": Enter your guess (1-" + MAX_NUMBER + "): ");
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                if (guess < 1 || guess > MAX_NUMBER) {
                    System.out.println("Invalid input! Please enter a number between 1 and " + MAX_NUMBER +".");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // clear the invalid input
            }
        }
        return guess;
    }
    private static void printRoundSummary(int round, int attempts, boolean hasGuessedCorrectly) {
        System.out.println("\n=== End of Round " + round + " ===");
        System.out.println("Attempts this round: " + attempts);
        if (hasGuessedCorrectly) {
            System.out.println("Result: Win");
        } else {
            System.out.println("Result: Loss");
        }
        System.out.println("========================\n");
    }
    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (yes/no): ");
        return scanner.next().equalsIgnoreCase("yes");
    }
    private static void printFinalSummary(int rounds, int totalAttempts, int wins, int losses) {
        int score = calculateScore(wins, losses);
        System.out.println("=== Game Over! ===");
        System.out.println("Total rounds played: " + rounds);
        System.out.println("Total attempts: " + totalAttempts);
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Score: " + score);
        System.out.printf("Average attempts per round: %.2f\n", totalAttempts / (double) rounds);
        System.out.println("==================");
    }
    private static int calculateScore(int wins, int losses) {
                return (wins * 10) - (losses * 5);
    }
}
