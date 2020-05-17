package sample;

public enum Register {
    rZERO("$zero"),rAT("$at"),rV0("$v0"),rV1("$v1"),rA1("$a1"),rA2("$a2"),
   rA3("$a3"),rT0("$t0"),rT1("$t1"),rT2("$t2"),rT3("$t3"),rT4("$t4"),rT5("$t5"),
    rT6("$t6"),rT7("$t7"),rS0("$S0"),rS1("$S1"),rS2("$S3"),rS4("$S4"),rS5("$S5"),
    rS6("$S6"),rS7("$S7"),rT8("$t8"),rT9("$t9"),rK0("$k0"),rK1("$k1"),rGP("$gp"),
    rSP("$sp"),rFp("$fp"),rRA("$ra");

    boolean isUsed;
    String value;
    String name;
    private Register(String name){
        this.name = name;
        isUsed = false;
    }
    public String getName(){
        return name;
    }
    void setValue(String value){
        this.value = value;
        isUsed = true;
    }
    String getValue(){
        isUsed = true;
        return value;
    }


}
