package sample;

import java.util.Scanner;
import java.io.File;
import java.util.HashMap;

public class Controller {
    static int programCounter = 1;
    static Scanner scan;
    static int lineCount;
    static HashMap<Integer,String>  InstHashMap = new HashMap<>();

    static void executeInstruction() {

        openFile();
        for (int i = 1; i < programCounter; i++) {
            scan.nextLine();
        }
        if (lineCount >= programCounter) {
            String operation = scan.next();
            boolean done = subExcuteOperation(operation);
            if( !done ){
                if(scan.hasNext()){
                  String operation2 = scan.next();
                  subExcuteOperation(operation2);
                }else
                {
                    programCounter = 1 + lineCount;
                }

            }

            //if (!scan.hasNext()) {
              //  endOfProgram = true;
            //}

            closeFile();
        }
    }
    static private boolean subExcuteOperation(String operation){
        boolean done = true;
        if (operation.compareTo("add") == 0) {
                Operation.add(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1,3)));
                programCounter++;
            } else if (operation.compareTo("sub") == 0) {
                Operation.sub(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1,3)));
                programCounter++;
            } else if (operation.compareTo("addi") == 0) {
                Operation.addi(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), scan.next());
                programCounter++;
            } else if (operation.compareTo("and") == 0) {
                Operation.and(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1,3)));
                programCounter++;
            } else if (operation.compareTo("or") == 0) {
                Operation.or(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1,3)));
                programCounter++;
            } else if (operation.compareTo("andi") == 0) {
                Operation.andi(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), Tool.decimaltoTwosComplement(scan.next()));
                programCounter++;

            } else if (operation.compareTo("ori") == 0) {
                Operation.ori(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), Tool.decimaltoTwosComplement(scan.next()));
                programCounter++;
            } else if (operation.compareTo("nor") == 0) {
                Operation.nor(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1,3)));
                programCounter++;

            } else if (operation.compareTo("sll") == 0) {
                Operation.sll(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), Tool.decimaltoTwosComplement(scan.next()));
                programCounter++;

            } else if (operation.compareTo("srl") == 0) {
                Operation.srl(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), Tool.decimaltoTwosComplement(scan.next()));
                programCounter++;
            } else if (operation.compareTo("sra") == 0) {
                Operation.sra(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), Tool.decimaltoTwosComplement(scan.next()));
                programCounter++;
            } else if (operation.compareTo("slt") == 0) {
                Operation.slt(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1,3)));
                programCounter++;
            } else if (operation.compareTo("slti") == 0) {
                Operation.slti(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), scan.next());
                programCounter++;
            } else if (operation.compareTo("beq") == 0) {
                Operation.beq(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), scan.next());
            }
            else if (operation.compareTo("bne") == 0) {
                    Operation.bne(Register.valueOf(scan.next().substring(1, 3)), Register.valueOf(scan.next().substring(1, 3)), scan.next());
            }
            else if (operation.compareTo("j") == 0) {
                    Operation.j(scan.next());
            }
            else if (operation.compareTo("sw") == 0) {
                    Register r = Register.valueOf(scan.next().substring(1, 3));
                    String connected = scan.next();
                    int size = connected.length();
                    int index = 0;
                    for (int i = 0; i < size ; i++) {
                        if(connected.charAt(i)=='('){
                           index = i ;
                        }
                    }
                    if(connected.charAt(index+2)== 'z'){
                        Operation.sw(r, connected.substring(0,index) , Register.valueOf(connected.substring(index+2 ,size -3)) );
                    }
                    else{
                        Operation.sw(r, connected.substring(0,index) , Register.valueOf(connected.substring(index+2 ,size -1)) );
                    }
                    programCounter ++ ;
            }
            else if (operation.compareTo("lw") == 0) {
                    Register r = Register.valueOf(scan.next().substring(1, 3));
                    String connected = scan.next();
                    int size = connected.length();
                    int index = 0;
                    for (int i = 0; i < size ; i++) {
                        if(connected.charAt(i)=='('){
                           index = i ;
                        }
                    }
                   // System.out.println(connected.substring(1,index)+ " " + connected.substring(index+2,size -1) );
                    if(connected.charAt(index+2)== 'z'){
                        Operation.lw(r, connected.substring(0,index) , Register.valueOf(connected.substring(index+2 ,size -3)) );
                    }
                    else{
                        Operation.lw(r, connected.substring(0,index) , Register.valueOf(connected.substring(index+2 ,size -1)) );
                    }
                    programCounter ++;
            }
            else if (operation.compareTo("lui") == 0) {
                    Operation.lui(Register.valueOf(scan.next().substring(1, 3)), scan.next());
                    programCounter ++;
            }
            else
                done = false;
            return done;
    }
    static int findLabelLine(String label){
        int lineNum = 1;
        openFile();

        while(scan.hasNext()){
            if (scan.nextLine().startsWith(label))
                break;
            else{
                lineNum ++;
            }
        }

        closeFile();
        return lineNum;
    }
    static void setlineCount(){
        openFile();
        while(scan.hasNext()){
                lineCount++;
                InstHashMap.put(lineCount, scan.nextLine());

            }
        closeFile();
    }
    static  void openFile(){
            try{
                File file = new File("AssemblyCode");
                scan = new Scanner(file);
            }
            catch (Exception e){
                System.out.println("File not Found!");
            }
    }
    static void closeFile(){
                scan.close();
    }
}
