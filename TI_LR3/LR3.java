package TI_LR3;

import java.util.*;

public class LR3 {
    public static Map<String,Double> sortByValue(Map<String,Double> probability){
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(probability.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    public static Map<String, String> sortcodes(Map<String,String> codes){

        List<Map.Entry<String, String>> list =
            new LinkedList<Map.Entry<String, String>>(codes.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
        public int compare(Map.Entry<String, String> o1,
                Map.Entry<String, String> o2) {
            return (o1.getValue()).compareTo(o2.getValue());
        }
    });
    Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        for (Map.Entry<String, String> entry : list) {
        sortedMap.put(entry.getKey(), entry.getValue());
    }
        return sortedMap;
}
    public static void encode(Node root, String str, Map<String, String> huffmanCode)
    {
        if (root == null) {
            return;
        }
        if (isLeaf(root)) {
            huffmanCode.put(root.letter, str.length() > 0? str : "0");
        }
        encode(root.right, str + '1', huffmanCode);
        encode(root.left, str + '0', huffmanCode);
    }
    public static int decode(Node root, int index, StringBuilder sb)
    {
        if (root == null) {
            return index;
        }
        if (isLeaf(root))
        {
            System.out.print(root.letter);
            return index;
        }
        index++;

        root = (sb.charAt(index) == '1') ? root.left : root.right;
        index = decode(root, index, sb);
        return index;
    }
    public static boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }
    public static void Algorythm(Map<String,Double> probability)
    {
        Map<String,Double> treeMap = sortByValue(probability);
        PriorityQueue<Node> pq;
        pq = new PriorityQueue<>(Comparator.comparingDouble(l -> l.prob));

        for (var entry: probability.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        while (pq.size() != 1)
        {
            Node left = pq.poll();
            Node right = pq.poll();
            double sum = left.prob + right.prob;
            pq.add(new Node(null, sum, left, right));
        }
        Node root = pq.peek();
        Map<String, String> huffmanCode = new HashMap<>();
        encode(root, " ", huffmanCode);
        huffmanCode = sortcodes(huffmanCode);
        int[]errors = Hamming.HammingErrors(huffmanCode);
        int errindex = 0;
       /* System.out.println("Huffman codes:");
        for (Map.Entry<String, Double> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + huffmanCode.get(entry.getKey()));
        }*/
        System.out.println("Sign|Code - Error bit:");
        for (Map.Entry<String, String> entry :huffmanCode.entrySet()) {
            System.out.println("  "+entry.getKey() +" | "+entry.getValue()
                    + " - "+ errors[errindex] );
            errindex++;
        }
        StringBuilder sb = new StringBuilder();
        if (isLeaf(root))
        {
            while (root.prob-- > 0) {
                System.out.print(root.letter);
            }
        }
        else {
            int index = -1;
            while (index < sb.length() - 1) {
                index = decode(root, index, sb);
            }
        }
    }
    public static void main(String[] args)
    {
        Map<String,Double> letters = new HashMap<>();
        /*letters.put("aaa",0.008);letters.put("aab",0.0176); letters.put("aac",0.0144);
        letters.put("aba",0.0176);letters.put("abb",0.03872); letters.put("abc",0.03168);
        letters.put("aca",0.0144);letters.put("acb",0.03168); letters.put("acc",0.02592);

        letters.put("baa",0.0176);letters.put("bab",0.03872); letters.put("bac",0.03168);
        letters.put("bba",0.03872);letters.put("bbb",0.085184); letters.put("bbc",0.069696);
        letters.put("bca",0.03168);letters.put("bcb",0.069696); letters.put("bcc",0.057024);

        letters.put("caa",0.0144);letters.put("cab",0.03168); letters.put("cac",0.02592);
        letters.put("cba",0.03168);letters.put("cbb",0.069696); letters.put("cbc",0.057024);
        letters.put("cca",0.02592);letters.put("ccb",0.057024); letters.put("ccc",0.046656);
*/
        letters.put("a",0.05);letters.put("б",0.09); letters.put("в",0.04);
        letters.put("г",0.08);letters.put("д",0.07); letters.put("е",0.15);
        letters.put("є",0.36);letters.put("ж",0.15); letters.put("з",0.01);

        Algorythm(letters);
    }
}