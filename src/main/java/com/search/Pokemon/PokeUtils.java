package Pokemon;

import java.util.ArrayList;

public class PokeUtils {
    public static ArrayList<String> stringSplit(String string, String delimiter) {
        ArrayList<String> result = new ArrayList<>();
        int index = string.indexOf(delimiter);
        if (index == -1) {
            System.out.println("Index out of range for split.");
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            result.add("");
            result.add(string.substring(index + delimiter.length(), string.length() ));
        } else if (index == string.length() - 1) {
            result.add(string.substring(0, index + delimiter.length()));
            result.add("");
        } else {
            result.add(string.substring(0, index));
            result.add(string.substring(index + delimiter.length(), string.length()));
        }
        return result;
    }
}
