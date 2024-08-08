package TI_LR4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LZW_Coding {
    public static List<Integer> compress(String uncompressed, Map<String,Integer> dictionary) {
        Map<String, Integer> Dictionary = new LinkedHashMap<>();
        int index = 0;
        for (char c : uncompressed.toCharArray()) {
            if (!Dictionary.containsKey(String.valueOf(c)))
                Dictionary.put(String.valueOf(c), ++index);
        }
        dictionary.putAll(Dictionary);
        System.out.println("Початкові символи:");
        for(Map.Entry<String, Integer> pair : dictionary.entrySet())
        {
            Integer value = pair.getValue();
            System.out.println(pair.getKey()+" = "+value);
        }
        String w = "";
        List<Integer> result = new ArrayList<>();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (Dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(Dictionary.get(w));
                Dictionary.put(wc, ++index);
                w = "" + c;
            }
        }
        System.out.println("\nКодування: ");
        for(Map.Entry<String, Integer> pair : Dictionary.entrySet())
        {
            Integer value = pair.getValue();
            if(value>7)
                System.out.println(pair.getKey()+" = "+value);
        }
        if (!w.equals(""))
            result.add(Dictionary.get(w));

        return result;
    }

}