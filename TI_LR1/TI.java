package TI_LR1;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TI {
        public static void ReadFileLetters(String[] letters, String fname)
        {
            char[] buf = new char[1];
            int c, number = 0 ;
            try(FileReader reader = new FileReader(fname))
            {
                while((c = reader.read(buf))>0)
                    if(c < 2){
                        buf = Arrays.copyOf(buf, c);
                        if((buf[0] >= 65) && (buf[0]<= 90) || (buf[0] >= 97) && (buf[0] <= 122)) {
                            letters[number] = String.valueOf(buf[0]).toUpperCase();
                            number++;
                        }
                    }
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
        public static void SortLetters(String[] letters)
        {
            for(int z = 0; z < letters.length-1; z++)
                for(int j = z+1;j < letters.length-1;j++)
                    if((letters[z].compareTo(letters[j]))>0) {
                        String temp = letters[z];
                        letters[z] = letters[j];
                        letters[j] = temp;
                    }
        }
        public static double[] Probability(int [] numbers, int numb)
        {
            double[] prop = new double[26];
            for(int i = 0; i < 26; i++)
                prop[i] = (double)numbers[i + 1]/(double)numb;
            return prop;
        }
        public static double[] NumberOfInfo(double[] probability)
        {
            double [] info = new double[26];
            for(int i = 0; i<26;i++)
                info[i]=-(Math.log(probability[i])/Math.log(2));
            return info;
        }
        public  static double[] Entropia(double [] probability )
        {
            double[] entrop = new double[26];
            for(int i = 0; i < 26; i++)
                entrop[i] = -(probability[i]*(Math.log(probability[i])/Math.log(2)));
            return entrop;
        }
        public static void main(String[] args)  {
            String[] letters = new String [5000];
            int number = 0;
            ReadFileLetters(letters,"text.txt");
            for (String s : letters)
                if (!Objects.equals(s, " "))
                    number++;
            SortLetters(letters);
            HashMap<String, Integer> letter = new HashMap<>();
            int numb = 0, index = 1;
            int [] prop = new int [27];
            prop[0] = 0;
            for(int k = 0; k < number; k++) {
                for(int j = 0; j < number; j++)
                    if(Objects.equals(letters[k], letters[j])) {
                        numb++;
                        letter.put(letters[k], numb);
                    }
                if(numb!=0 && index<27)
                    if(prop[index - 1]!= numb) {
                        prop[index] = letter.get(letters[k]);
                        index++;
                    }
                numb = 0;
            }
            double [] lprop, lentr, info;
            lprop = Probability(prop,number);
            info = NumberOfInfo(lprop);
            lentr = Entropia(lprop);
            double allentr=0;
            for (int i=0; i< lentr.length;i++)
                allentr+=lentr[i];
            for (Map.Entry entry : letter.entrySet()) {
                System.out.println("\n Буква: " + entry.getKey() + " Кількість: "
                        + entry.getValue()+ " Ймовірність:" + lprop[numb]+"\n I(x): " +info[numb]
                        + "  H(x):" + lentr[numb]);
                numb++;
            }
            System.out.println("\n H(x) англійського тексту = "+allentr);

        }
}