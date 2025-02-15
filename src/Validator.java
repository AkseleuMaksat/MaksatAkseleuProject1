import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Validator {
    private static final String KEY = "Enter your key (integer number): ";

    protected static boolean isValidPath(Path path) {
        return Files.exists(path) && Files.isRegularFile(path);
    }

    protected static int getValidKey(Scanner scanner) {
        int key;
        while (true) {
            System.out.print(KEY);
            try {
                key = Integer.parseInt(scanner.nextLine());
                if (key >= 0) {
                    return key;
                } else {
                    System.out.println("Key cannot be negative. Please enter a non-negative integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a non-negative integer.");
            }
        }
    }
}
