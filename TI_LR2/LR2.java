package TI_LR2;

public class LR2 {
    public static void SortProbabilities(String[][] arr, int number)
    {
        String[][] hlpr = new String[2][2];
        for(int i = 0;i < number;i++)
            for(int j = i + 1;j < number - 1;j++)
                if(Double.parseDouble(arr[i][1])<Double.parseDouble(arr[j][1]))
                {
                    hlpr[0][0] = arr[i][0];
                    hlpr[0][1] = arr[i][1];
                    arr[i][0] = arr[j][0];
                    arr[i][1] = arr[j][1];
                    arr[j][0] = hlpr[0][0];
                    arr[j][1] = hlpr[0][1];
                }
    }

    public static void Algorythm(int cnumb, int maxnumb, double[]prob, String []symb)
    {
        double _1stgr = 0, _2ndgr = 0, _1stdiff, _2nddiff ;
        int i, k = 0, j;
        if ((cnumb + 1) == maxnumb || cnumb == maxnumb || cnumb > maxnumb) {
            if (cnumb == maxnumb || cnumb > maxnumb) {
                symb[--cnumb]+="0";
                return;
            }
            symb[maxnumb] += "1";
            symb[cnumb] += "0";
            return;
        }
        else {
            if(cnumb==6) {
                for (i = cnumb; i <= maxnumb - 2; i++)
                    _1stgr += prob[i];
                _2ndgr += prob[i];
            }
            else
                for (i = cnumb; i <= maxnumb - 1; i++)
                    _1stgr+= +prob[i];
            _2ndgr += prob[maxnumb];
            _1stdiff = _1stgr - _2ndgr;
            if (_1stdiff < 0)
                _1stdiff*=-1;
            j = 2;
            while (j !=maxnumb - cnumb + 1) {
                k = maxnumb - j;
                _1stgr = _2ndgr = 0;
                for (i = cnumb; i <= k; i++)
                    _1stgr+= prob[i];
                for (i = maxnumb; i > k; i--)
                    _2ndgr+=prob[i];
                _2nddiff = _1stgr - _2ndgr;
                if (_2nddiff < 0)
                    _2nddiff*= -1;
                if (_2nddiff >= _1stdiff)
                    break;
                _1stdiff = _2nddiff;
                j++;
            }
            k++;
            if(cnumb==6) {
                for (i = k + 1; i <= maxnumb; i++)
                    symb[i] += "1";
            }
            else
                for (i = cnumb; i <= k; i++)
                    symb[i] += "0";
            for (i = k + 1; i <= maxnumb; i++)
                symb[i] += "1";
            Algorythm(cnumb, k, prob, symb);
            Algorythm(k + 1, maxnumb, prob, symb);
        }
    }
    public static void AvgCodeLength(double[] prob, String[]symb)
    {
        double  avgnumber=0;
        for(int i =0;i<prob.length;i++)
        {
            avgnumber += prob[i]*(symb[i].length()-1);
        }
        System.out.println( " Середня довжина кодового слова = "+ avgnumber+" біт/символ");
    }
    public static void MinCodeLength(double[] prob)
    {
        double  minnumber=0;
        for(int i =0;i<prob.length;i++)
        {
            minnumber += -((prob[i]*(Math.log(prob[i])/Math.log(2))));
        }
        System.out.println( " Мінімальна довжина кодового слова = "+ minnumber+" біт/символ");
    }
    public static void main(String[] args) {
        int number = 9,hlpr=0;
        char l = (char)1040;
        String[][] letter = new String[number][2];
        for(int i = 0; i< number;i++)
            if(i!=6) {
                letter[i][0] = String.valueOf((char)(l+hlpr));
                hlpr++;
            }
            else
                letter[i][0] = String.valueOf((char)1028);
        letter[0][1]="0.05";
        letter[1][1]="0.09";
        letter[2][1]="0.04";
        letter[3][1]="0.08";
        letter[4][1]="0.07";
        letter[5][1]="0.15";
        letter[6][1]="0.36";
        letter[7][1]="0.15";
        letter[8][1]="0.01";
        SortProbabilities(letter,number);
        String [] s  = new String[9];
        double[] prob = new double[9];
        for(int i = 0; i < number;i++) {
            prob[i] = Double.parseDouble(letter[i][1]);
            s[i] = " ";
        }
        Algorythm(0, prob.length-1,prob,s);
        System.out.println();
        for(int i = 0;i< number;i++) {
            for (int j = 0; j < 2; j++)
                System.out.print(letter[i][j]+" ");
            System.out.println(s[i]+"\n");
        }
        AvgCodeLength(prob,s);
        MinCodeLength(prob);
    }

}
