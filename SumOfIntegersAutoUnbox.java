import java.util.ArrayList;
import java.util.Scanner;

public class SumOfIntegersAutoUnbox {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.print("Enter the number of integers: ");
        int n = sc.nextInt();
        sc.nextLine();  // Consume newline

        // Accept integer inputs as strings
        for (int i = 0; i < n; i++) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            String input = sc.nextLine();

            // Parsing string to primitive int
            int value = Integer.parseInt(input);

            // Autoboxing: primitive int → Integer object
            numbers.add(value);
        }

        int sum = 0;

        // Enhanced for-loop with Unboxing (Integer → int)
        for (Integer num : numbers) {
            sum += num; // Unboxing happens automatically
        }

        System.out.println("\nThe sum of the integers is: " + sum);
        sc.close();
    }
}
