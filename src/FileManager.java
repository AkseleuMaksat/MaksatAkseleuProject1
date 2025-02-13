import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    static CipherAks cipherAks  = new CipherAks();
    static Validator validator = new Validator();
    public static void encryptFile(Path inputPath, Path outputPath, int key) {
        if (!validator.isValidPath(inputPath)) {
            System.out.println("Ошибка: Файл не найден.");
            return;
        }
        String text="";
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath.toFile()));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath.toFile()))){
            int currentChar;
            while ((currentChar = br.read()) != -1) {
                char c = (char) currentChar;
                text = text + c;
            }
            bw.write(cipherAks.encryptText(text, key));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void decryptFile(Path inputPath, Path outputPath, int key) {
        if (!validator.isValidPath(inputPath)) {
            System.out.println("Ошибка: Файл не найден.");
            return;
        }
        String text="";
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath.toFile()));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath.toFile()))
             ){
            int currentChar;
            while ((currentChar = br.read()) != -1) {
                char c = (char) currentChar;
                text = text + c;
            }
            bw.write(cipherAks.decryptText(text, key));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void bruteForce(Path inputPath) {
        if (!Validator.isValidPath(inputPath)) {
            System.out.println("Ошибка: Файл не найден.");
            return;
        }
        String text="";
        String outPath=inputPath.toString();
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath.toFile()))){
            int currentChar;
            while ((currentChar = br.read()) != -1) {
                char c = (char) currentChar;
                text = text + c;
            }
            for (int key = 0; key <cipherAks.ALPHABET.size(); key++) {
                    String decryptedText = cipherAks.decryptText(text, key);
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(outPath+key+1+".txt"))){
                       bw.write(decryptedText);
                    }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
