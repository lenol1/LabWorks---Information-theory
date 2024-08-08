package TI_LR4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW_Decoding {
    public static String decompress(List<Integer> compressed, Map<String,Integer> dictionary) {
        Map<Integer,String> decompressDictionary = new HashMap<>();
        for (Map.Entry<String, Integer> e: dictionary.entrySet()){
            decompressDictionary.put(e.getValue(), e.getKey());
        }
        int index = decompressDictionary.size();

        String w = "" + decompressDictionary.get(compressed.remove(0));
        StringBuilder result = new StringBuilder(w);
        for (int k: compressed) {
            String entry;
            if (decompressDictionary.containsKey(k))
                entry = decompressDictionary.get(k);
            else if (k == dictionary.size())
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);

            result.append(" ").append(entry);
            decompressDictionary.put(++index, w + entry.charAt(0));

            w = entry;
        }
        System.out.println("\nДекодування: ");
        for(Map.Entry<Integer, String> pair : decompressDictionary.entrySet())
        {
            String value = pair.getValue();
            if(pair.getKey() > 7)
            System.out.println(pair.getKey()+" = "+ value);
        }
        return result.toString();
    }
}