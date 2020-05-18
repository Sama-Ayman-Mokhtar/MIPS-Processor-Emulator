package sample;

public class Operation {

    static  void add(Register rd, Register rs,Register rt){
        int rsNum = Integer.parseInt(Tool.twosComplementToDecimal(rs.getValue()));
        int rtNum = Integer.parseInt(Tool.twosComplementToDecimal(rt.getValue()));
        int rdNum = rsNum + rtNum;
        rd.setValue(Tool.decimaltoTwosComplement(String.valueOf(rdNum)));
    }
    static  void sub(Register rd, Register rs,Register rt){
        int rsNum = Integer.parseInt(Tool.twosComplementToDecimal(rs.getValue()));
        int rtNum = Integer.parseInt(Tool.twosComplementToDecimal(rt.getValue()));
        int rdNum = rsNum - rtNum;
        rd.setValue(Tool.decimaltoTwosComplement(String.valueOf(rdNum)));
    }
    static  void addi(Register rd, Register rs, String immediate){
        int rsNum = Integer.parseInt(Tool.twosComplementToDecimal(rs.getValue()));
        int rtNum = Integer.parseInt(immediate);
        int rdNum = rsNum + rtNum;
        rd.setValue(Tool.decimaltoTwosComplement(String.valueOf(rdNum)));
    }
    static void and(Register rd, Register rs, Register rt){
        char arr[] = new char[32];
        for (int i = 0; i < 32; i++) {
            if(rs.getValue().charAt(i) == '1' && rt.getValue().charAt(i) == '1')
                arr[i] = '1';
            else
                arr[i] = '0';
        }
        rd.setValue(String.valueOf(arr));
    }
    static void or(Register rd, Register rs, Register rt){
        char arr[] = new char[32];
        for (int i = 0; i < 32; i++) {
            if(rs.getValue().charAt(i) == '0' && rt.getValue().charAt(i) == '0')
                arr[i] = '0';
            else
                arr[i] = '1';
        }
        rd.setValue(String.valueOf(arr));
    }
     static void andi(Register rd, Register rs, String immediate){
        char arr[] = new char[32];
        for (int i = 0; i < 32; i++) {
            if(rs.getValue().charAt(i) == '1' && immediate.charAt(i) == '1')
                arr[i] = '1';
            else
                arr[i] = '0';
        }
        rd.setValue(String.valueOf(arr));
    }
    static void ori(Register rd, Register rs, String immediate){
        char arr[] = new char[32];
        for (int i = 0; i < 32; i++) {
            if(rs.getValue().charAt(i) == '0' && immediate.charAt(i) == '0')
                arr[i] = '0';
            else
                arr[i] = '1';
        }
        rd.setValue(String.valueOf(arr));
    }
     static void nor(Register rd, Register rs, Register rt){
        char arr[] = new char[32];
        for (int i = 0; i < 32; i++) {
            if(rs.getValue().charAt(i) == '0' && rt.getValue().charAt(i) == '0')
                arr[i] = '1';
            else
                arr[i] = '0';
        }
        rd.setValue(String.valueOf(arr));
    }
    static void sll(Register rd, Register rs, String shiftAmount){
        char arr[] = new char[32];
        int sa = Integer.parseInt(Tool.twosComplementToDecimal(shiftAmount));
        for (int i = 0; i < sa; i++) {
            arr[i] = '0';
        }
        String subString = rs.getValue().substring((0+sa));
        String s = subString.concat(String.valueOf(arr));
        rd.setValue(s);
    }
    static void srl(Register rd, Register rs, String shiftAmount){
        char arr[] = new char[32];
        int sa = Integer.parseInt(Tool.twosComplementToDecimal(shiftAmount));
        for (int i = 0; i < sa; i++) {
            arr[i] = '0';
        }
        String subString = rs.getValue().substring(0,(32 - sa));
        String s = (String.valueOf(arr)).concat(subString);
        rd.setValue(s);
    }
     static void sra(Register rd, Register rs, String shiftAmount){
        char arr[] = new char[32];
        boolean isPositive = (rs.getValue().charAt(0) == '0');
        int sa = Integer.parseInt(Tool.twosComplementToDecimal(shiftAmount));
        for (int i = 0; i < sa; i++) {
            if (isPositive)
                arr[i] = '0';
            else
                arr[i] = '1';
        }
        String subString = rs.getValue().substring(0,(32 - sa));
        String s = (String.valueOf(arr)).concat(subString);
        rd.setValue(s);
    }
    static  void slt(Register rd, Register rs,Register rt){
        int rsNum = Integer.parseInt(Tool.twosComplementToDecimal(rs.getValue()));
        int rtNum = Integer.parseInt(Tool.twosComplementToDecimal(rt.getValue()));
        int rdNum = rsNum - rtNum;
        if (rdNum < 0)
            rd.setValue(Tool.decimaltoTwosComplement("1"));
        else
           rd.setValue(Tool.decimaltoTwosComplement("0"));
    }
    static  void slti(Register rd, Register rs,String immediate){
        int rsNum = Integer.parseInt(Tool.twosComplementToDecimal(rs.getValue()));
        int rtNum = Integer.parseInt(immediate);
        int rdNum = rsNum - rtNum;
        if (rdNum < 0)
            rd.setValue(Tool.decimaltoTwosComplement("1"));
        else
           rd.setValue(Tool.decimaltoTwosComplement("0"));
    }
    static void beq(Register rd, Register rs, String label) {
        int rdNum = Integer.parseInt(Tool.twosComplementToDecimal(rd.getValue()));
        int rsNum = Integer.parseInt(Tool.twosComplementToDecimal(rs.getValue()));
        if (rsNum == rdNum) {
            Controller.programCounter = Controller.findLabelLine(label) ;
        }
        else
            Controller.programCounter ++;
    }
     static void bne(Register rd, Register rs, String label) {
        int rdNum = Integer.parseInt(Tool.twosComplementToDecimal(rd.getValue()));
        int rsNum = Integer.parseInt(Tool.twosComplementToDecimal(rs.getValue()));
        if (!(rsNum == rdNum)) {
            Controller.programCounter = Controller.findLabelLine(label) ;
        }
        else
            Controller.programCounter ++;
    }
}
