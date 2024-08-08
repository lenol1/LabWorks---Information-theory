package TI_LR3;

import java.util.Map;

public class Hamming {
    public static int[] HammingErrors(Map<String,String> huffmancode)
    {
        int[] errors = new int[huffmancode.size()];
        String[] signs = new String[huffmancode.size()];
        for(int i = 0;i < errors.length; i++)
            errors[i] = 0;
        int hlpr, index = 0;
        String[] numbers;
        for (Map.Entry<String, String> numb : huffmancode.entrySet()) {
            signs[index] = numb.getValue().replace("", " ").trim();
            index++;
        }
            for(int i = 0; i< errors.length;i++) {
                numbers = signs[i].split(" ");
                for (String number : numbers) {
                    errors[i] ^= Integer.parseInt(number);
                }
            }
        return errors;
    }
}