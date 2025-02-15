import java.util.Arrays;
import java.util.List;

public class CipherAks {
    public static final List<Character> ALPHABET = Arrays.asList(
            'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', ':', '!', '?', ' ');

    public static String encryptText(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            boolean isUpper = Character.isUpperCase(ch);
            char toLower = Character.toLowerCase(ch);

            int index = ALPHABET.indexOf(toLower);
            if (index != -1) {
                int bufferIndex = (index + key) % ALPHABET.size();
                if (isUpper) {
                    result.append(Character.toUpperCase(ALPHABET.get(bufferIndex)));
                }else {
                    result.append(ALPHABET.get(bufferIndex));
                }
            }else {
                result.append(toLower);
            }
        }
        return result.toString();
    }

    public static String decryptText(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            boolean isUpper = Character.isUpperCase(ch);
            char toLower = Character.toLowerCase(ch);

            int index = ALPHABET.indexOf(toLower);
            if (index != -1) {
                int bufferIndex = (index - key+ALPHABET.size()) % ALPHABET.size();
                if (isUpper) {
                    result.append(Character.toUpperCase(ALPHABET.get(bufferIndex)));
                }else {
                    result.append(ALPHABET.get(bufferIndex));
                }
            }else {
                result.append(toLower);
            }
        }
        return result.toString();
    }

    }
