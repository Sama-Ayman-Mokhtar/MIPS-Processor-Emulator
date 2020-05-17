package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
       // launch(args);
        /* //AND Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("4"));
        Register.rA2.setValue(Tool.decimaltoTwosComplement("2"));
        Operation.add(Register.rAT,Register.rA1,Register.rA2);
        System.out.println(Tool.twosComplementToDecimal(Register.rAT.getValue())); */
        /* //SUB Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("4"));
        Register.rA2.setValue(Tool.decimaltoTwosComplement("2"));
        Operation.sub(Register.rAT,Register.rA1,Register.rA2);
        System.out.println(Tool.twosComplementToDecimal(Register.rAT.getValue())); */
        /* //ANDI Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("4"));
        Operation.addi(Register.rAT,Register.rA1,"2");
        System.out.println(Tool.twosComplementToDecimal(Register.rAT.getValue())); */
        /* //AND Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("8"));
        Register.rA2.setValue(Tool.decimaltoTwosComplement("31"));
        Operation.and(Register.rAT,Register.rA1,Register.rA2);
        System.out.println(Register.rA1.getValue());
        System.out.println(Register.rA2.getValue());
        System.out.println(Register.rAT.getValue()); */
        /* //OR Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("8"));
        Register.rA2.setValue(Tool.decimaltoTwosComplement("31"));
        Operation.or(Register.rAT,Register.rA1,Register.rA2);
        System.out.println(Register.rA1.getValue());
        System.out.println(Register.rA2.getValue());
        System.out.println(Register.rAT.getValue()); */
        /* //ANDI Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("8"));
        Operation.andi(Register.rAT,Register.rA1,Tool.decimaltoTwosComplement("31"));
        System.out.println(Register.rA1.getValue());
        System.out.println(Tool.decimaltoTwosComplement("31"));
        System.out.println(Register.rAT.getValue()); */
        /* //ORI Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("8"));
        Operation.ori(Register.rAT,Register.rA1,Tool.decimaltoTwosComplement("31"));
        System.out.println(Register.rA1.getValue());
        System.out.println(Tool.decimaltoTwosComplement("31"));
        System.out.println(Register.rAT.getValue()); */
        /* //NOR Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("8"));
        Register.rA2.setValue(Tool.decimaltoTwosComplement("31"));
        Operation.or(Register.rAT,Register.rA1,Register.rA2);
        System.out.println(Register.rA1.getValue());
        System.out.println(Register.rA2.getValue());
        System.out.println("Or: " + Register.rAT.getValue());
        Operation.nor(Register.rAT,Register.rA1,Register.rA2);
        System.out.println("Nor:" + Register.rAT.getValue()); */
        /* //SLL Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("8"));
        Operation.sll(Register.rAT,Register.rA1,Tool.decimaltoTwosComplement("4"));
        System.out.println(Register.rA1.getValue());
        System.out.println(Register.rAT.getValue()); */
        /* //SRL Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("8"));
        Operation.srl(Register.rAT,Register.rA1,Tool.decimaltoTwosComplement("1"));
        System.out.println(Register.rA1.getValue());
        System.out.println(Register.rAT.getValue()); */
        /* //SRA Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("-8"));
        Operation.sra(Register.rAT,Register.rA1,Tool.decimaltoTwosComplement("1"));
        System.out.println(Register.rA1.getValue());
        System.out.println(Register.rAT.getValue()); */


    }
}
