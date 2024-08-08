package TI_LR4;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LR4 {
    public static void printList(List<Integer> list) {
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
            String message = "ОЛІЙНИКОЛІЙНИК";
            Map<String,Integer> dictionary = new LinkedHashMap<>();
            System.out.println("\nРечення для кодування та декодування: '"+message+"'");
            List<Integer> compressed = LZW_Coding.compress(message,dictionary);
            printList(compressed);
            String decompressed = LZW_Decoding.decompress(compressed,dictionary);
            System.out.println(decompressed);
    }
}