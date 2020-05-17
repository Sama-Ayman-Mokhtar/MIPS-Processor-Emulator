package sample;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Tool {
    static String twosComplementToDecimal(String b32){
        String sfinal;
        if(b32.charAt(0) == '0'){
            int b = Integer.parseInt(b32,2);
            sfinal  = String.valueOf(b);

         }
         else {
             String b =getTwosComplement(b32);
             String withoutSign = b.substring(1);
             int last = Integer.parseInt(withoutSign,2);
              sfinal = String.valueOf(Integer.valueOf(last)*-1);
         }
        return sfinal;

    }
     static String decimaltoTwosComplement(String b32){
         String sfinal ;
         if(Integer.parseInt(b32) >= 0){
            String b = Integer.toBinaryString(Integer.parseInt(b32));
            sfinal  = extendByZeros(b);

         }
         else {
             String b = Integer.toBinaryString(abs(Integer.parseInt(b32)));
             b = extendByZeros(b);
             b =getTwosComplement(b);
              sfinal = b;
         }



        return sfinal;
     }
     static private String getTwosComplement (String b32){
         boolean flag = false;
         char[] arr =  new char[32];
         for (int i = 31; i > -1; i--) {
              if(b32.charAt(i) == '1' && flag == false){
                 flag = true;
                 arr[i] = '1';
             }
             else if(flag == false){
                 arr[i] = b32.charAt(i);
             }
             else {
                  if(b32.charAt(i) == '1'){
                      arr[i]= '0';
                  }
                  else
                      arr[i] = '1';
             }


         }
         return String.valueOf(arr);
     }
     static private String extendByZeros(String b){
         int length =  b.length();
         int left = 32 - b.length();
         char[] arr = new char[32];
         for (int i = 0; i < left ; i++) {
             arr[i] = '0';
         }
         for (int i = 0; i < length ; i++) {
             arr[left + i] = b.charAt(i);
         }

         return String.valueOf(arr);
     }

}
