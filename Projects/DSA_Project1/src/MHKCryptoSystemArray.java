import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by hanxi on 12/09/2015.
 */
public class MHKCryptoSystemArray {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("Enter a string and I will encrypt it as single large integer.");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        int inputSize = input.length();

        System.out.println("Clear text:");
        System.out.println(input);
        System.out.println("Number of clear text bytes = " + input.length());
        System.out.println("Welcome to Data Structures and Algorithms is encrypted as");

        char[] array = input.toCharArray();

        /* Key Generation */

        BigInteger[] w = new BigInteger[7];
        w[0]= new BigInteger("3");
        w[1]= new BigInteger("5");
        w[2]= new BigInteger("15");
        w[3]= new BigInteger("25");
        w[4]= new BigInteger("54");
        w[5]= new BigInteger("110");
        w[6]= new BigInteger("225");

        BigInteger q = new BigInteger("439");
        BigInteger r =new BigInteger("10");

        BigInteger s = new BigInteger("1");
        while(!((s.multiply(r)).mod(q)).equals(BigInteger.ONE)){
            s = s.add(BigInteger.ONE);
        }

        BigInteger[] beta = new BigInteger[7];
        for(int i=0; i<7; i++){
            beta[i] = (w[i].multiply(r)).mod(q);
        }

        /* Encryption */
        BigInteger[] ciperText = new BigInteger[inputSize];
        ciperText = encryption(input, beta, inputSize);
        for(int i=0; i<ciperText.length;i++){
            System.out.print(ciperText[i]);
        }
        System.out.println();

        System.out.println("Result of decryption:");

        /* Decryption */
        BigInteger[] deciperText = new BigInteger[inputSize];
        deciperText = decryption(ciperText,w,s,q,inputSize);
        String output=TransformdeciperText(deciperText);
        System.out.println(output);
    }

    /* the element in the list should be the UTF-8 code in BigInteger type*/
    public static String TransformdeciperText(BigInteger[] deciperText) throws UnsupportedEncodingException {
        String output="";
        for(int i=0; i<deciperText.length;i++){
            byte[] character = deciperText[i].toByteArray();
            String str = new String(character,"UTF-8");
            if(str.equals("@")){
                str=" ";
            }
            output += str;
        }
        return output;
    }
    /* the method will transform the UTF-8 code to a string */
    /** Time Complexity: θ(n) */

    /* the ciperText list should not be empty */
    public static BigInteger[] decryption(BigInteger[] ciperText, BigInteger[] w, BigInteger s, BigInteger q, int inputSize){
        BigInteger[] deciper = new BigInteger[inputSize];
        for(int i=0; i<ciperText.length;i++){
            BigInteger c1 = (ciperText[i].multiply(new BigInteger(String.valueOf(s))))
                    .mod(new BigInteger(String.valueOf(q)));
            BigInteger c2 = new BigInteger(int2Bytes(w, c1));
            String strUTF8 = Integer.valueOf(c2.toString(),2).toString();
            BigInteger c3 = new BigInteger(strUTF8);

            deciper[i]=c3;
            System.out.println("c1 " + c1);
            System.out.println("c2 "+ c2);
            System.out.println("c3 "+ c3);
        }
        return deciper;
    }
    /* the moethod will transform the cipertext to UTF-8 code */
    /** Time Complexity: θ(n) */

    /* the BigInterger array w should not be empty */
    public static String int2Bytes(BigInteger[] w, BigInteger c1){
        String output = "";
        for(int j=w.length-1; j>=0; j--){
            if(c1.compareTo(w[j])>=0){
                c1 = c1.subtract(w[j]);
                output = "1" + output;
            }
            else{
                output = "0" + output;
            }
        }
        return output;
    }
    /* the method transform the decimal format to binary format */
    /** Time Complexity: θ(n) */

    /* the input string should not be null */
    public static BigInteger[] encryption(String input, BigInteger[] beta, int inputSize) throws UnsupportedEncodingException {
        char[] array = input.toCharArray();
        BigInteger[] ciperText = new BigInteger[inputSize];

        for(int i=0; i<array.length; i++){
            //System.out.println("1. "+array[i]);
            String c = Char2Byte(array[i]);
            //System.out.println("2. "+c);
            BigInteger sum = new BigInteger("0");
            for(int j=0; j<c.length(); j++){
                BigInteger c1 = new BigInteger(String.valueOf(c.charAt(j)));
                sum = sum.add(c1.multiply(new BigInteger(String.valueOf(beta[j]))));
            }
            //System.out.println("3. "+i+" "+sum);
            ciperText[i]=sum;
        }
        return ciperText;
    }
    /* the method transform a string to cipertext */
    /** Time Complexity: θ(n^2) */

    /* the parameter should be a character */
    public static String Char2Byte(char str) throws UnsupportedEncodingException {
        if(String.valueOf(str) == null) return null;

        byte[] bt =  String.valueOf(str).getBytes("UTF8");
        String str2 = "";
        for(int i=0; i<bt.length; i++){
            str2 += Byte.toString(bt[i]);
        }
        int stri = new Integer(str2);
        String str3 = Integer.toBinaryString(stri);
        return str3;
    }
    /* the Character will be transformed into a binary string */
    /** Time Complexity: θ(n) */
}
