import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    private static final String ENTER_PATH = "Enter path of your file to encrypt: ";
    private static final String OUR_PATH = "Where do you want to save the file? Enter the path: ";
    private static final String KEY = "Enter your key (integer number): ";

    static Validator validator = new Validator();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("\nWelcome to my encryption program!");
            System.out.println("1. Encrypt file");
            System.out.println("2. Decrypt file");
            System.out.println("3. Brute force");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            if (option == 0) {
                System.out.println("Goodbye!");
                break;
            }

            Path path, savePath;
            int key;

            switch (option) {
                case 1:
                    System.out.print(ENTER_PATH);
                        path = Path.of(scanner.nextLine());

                    key= validator.getValidKey(scanner);

                    System.out.print(OUR_PATH);
                        savePath = Path.of(scanner.nextLine());
                        FileManager.encryptFile(path, savePath, key);
                    break;


                case 2:
                    System.out.print(ENTER_PATH);
                        path = Path.of(scanner.nextLine());

                    key= validator.getValidKey(scanner);

                    System.out.print(OUR_PATH);
                        savePath = Path.of(scanner.nextLine());
                        FileManager.decryptFile(path, savePath, key);
                    break;

                case 3:
                    System.out.print(ENTER_PATH);
                    path = Path.of(scanner.nextLine());
                        FileManager.bruteForce(path);
                    break;

                default:
                    System.out.println("Invalid option! Please enter a number between 0 and 3.");
            }
        }
        scanner.close();
    }
}