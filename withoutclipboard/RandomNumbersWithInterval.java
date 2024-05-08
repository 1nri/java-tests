import java.util.Random;
import java.util.Scanner;

public class RandomNumbersWithInterval {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for time interval in seconds
        System.out.print("Enter time interval in seconds: ");
        int timeInterval = scanner.nextInt();

        // Ask user for range of numbers
        System.out.print("Enter the minimum number in the range: ");
        int min = scanner.nextInt();
        System.out.print("Enter the maximum number in the range: ");
        int max = scanner.nextInt();

        // Generate first random number
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        System.out.println("Initial random number generated: " + randomNumber);

        // Loop to generate random numbers until interrupted
        while (true) {
            // Generate random number within the given range after the time interval
            System.out.println("Generating random number after " + timeInterval + " seconds...");
            try {
                Thread.sleep(timeInterval * 1000);
            } catch (InterruptedException e) {
                System.out.println("Program interrupted.");
                break;
            }

            randomNumber = random.nextInt(max - min + 1) + min;
            System.out.println("Random number generated: " + randomNumber);
        }

        scanner.close();
    }
}