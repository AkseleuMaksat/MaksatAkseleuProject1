import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {
    protected static boolean isValidPath(Path path) {
        return Files.exists(path) && Files.isRegularFile(path);
    }
}
