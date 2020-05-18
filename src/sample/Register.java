package sample;

public enum Register {
    zero("$zero"),at("$at"),v0("$v0"),v1("$v1"),a1("$a1"),a2("$a2"),
   a3("$a3"),t0("$t0"),t1("$t1"),t2("$t2"),t3("$t3"),t4("$t4"),t5("$t5"),
    t6("$t6"),t7("$t7"),s0("$S0"),s1("$S1"),s2("$S3"),s4("$S4"),s5("$S5"),
    s6("$S6"),s7("$S7"),t8("$t8"),t9("$t9"),k0("$k0"),k1("$k1"),gp("$gp"),
    sp("$sp"),fp("$fp"),ra("$ra");

    boolean isUsed;
    String value;
    String name;
    private Register(String name){
        this.name = name;
        isUsed = false;
        value = "00000000000000000000000000000000";
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
