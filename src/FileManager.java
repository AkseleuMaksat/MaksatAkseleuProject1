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
            System.out.println("Error. File not found.");
            return;
        }

        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath.toFile()));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath.toFile()))){
            int currentChar;
            while ((currentChar = br.read()) != -1) {
                text.append( (char) currentChar);

            }
            bw.write(cipherAks.encryptText(text.toString(), key));
            System.out.println("File encrypted successfully.");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void decryptFile(Path inputPath, Path outputPath, int key) {
        if (!validator.isValidPath(inputPath)) {
            System.out.println("Error. File not found.");
            return;
        }
        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath.toFile()));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath.toFile()))
             ){
            int currentChar;
            while ((currentChar = br.read()) != -1) {
                text.append( (char) currentChar);
            }
            bw.write(cipherAks.decryptText(text.toString(), key));
            System.out.println("File decrypted successfully.");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void bruteForce(Path inputPath) {
        if (!Validator.isValidPath(inputPath)) {
            System.out.println("Error. File not found.");
            return;
        }
        StringBuilder text = new StringBuilder();
        String outPath=inputPath.toString();
        String outputPath="";
        for (int i = 0; i < outPath.length(); i++) {
            if(outPath.charAt(i)=='.'){
                break;
            }else {
                outputPath+=outPath.charAt(i);
            }

        }
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath.toFile()))){
            int currentChar;
            while ((currentChar = br.read()) != -1) {
                text.append( (char) currentChar);
            }
            for (int key = 0; key <cipherAks.ALPHABET.size(); key++) {
                    String decryptedText = cipherAks.decryptText(text.toString(), key);
                    String index=  String.valueOf(key+1);
                    String totalName = outputPath+index;
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(totalName+".txt"))){
                       bw.write(decryptedText);
                    }
            }
            System.out.println("Using brute force, done successfully.");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
