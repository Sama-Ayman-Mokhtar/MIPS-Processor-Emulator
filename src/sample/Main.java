package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

import java.util.ArrayList;
import java.util.Map;

public class Main extends Application {
    Boolean binary = false;
     BorderPane bp;
     GridPane middleGP = new GridPane();
     ScrollPane sp = getSp();
     HBox topHBox;
     VBox bottomVBox;
     int dynamic;
     VBox leftVBox;


    @Override
    public void start(Stage primaryStage) throws Exception{
         bp = new BorderPane();

         addRowMiddleGP(0,0);
         middleGP.setVgap(10);
         middleGP.setHgap(10);
         middleGP.setPadding(new Insets(20,20,20,20));
         //middleGP.setGridLinesVisible(true);
        getLeftVBox();
        bp.setRight(leftVBox);
        bp.setCenter(sp);
        bp.setBottom(getBottomVBox());
        bp.setTop(getTopHBox("Click 'Next Instruction' to Start"));
        Scene scene = new Scene(bp, 900, 600);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    VBox getBottomVBox(){
        bottomVBox = new VBox();
        Button nextInstruction = new Button("Next Instruction");
        nextInstruction.setStyle("-fx-font-size: 16pt");
        bottomVBox.getChildren().add(nextInstruction);
        bottomVBox.setAlignment(Pos.CENTER);
        bottomVBox.setPadding(new Insets(20,20,20,20));
        nextInstruction.setOnAction(e -> nextAction());
        return bottomVBox;
    }

    void nextAction(){
        if(Controller.programCounter <= Controller.lineCount){
            bp.setTop(getTopHBox(Controller.programCounter , Controller.InstHashMap.get(Controller.programCounter)));
            int pc = Controller.programCounter;
            Controller.executeInstruction();
            bp.setRight(getLeftVBox());
            dynamic ++;
            addRowMiddleGP(pc,dynamic);
            System.out.println(Controller.programCounter);
            System.out.println(Register.t0.getValue());
        }
        else{
            bp.setTop(getTopHBox( "End Of Program"));

        }


    }
    HBox getTopHBox(int num, String instruction){
        topHBox = new HBox();
        Label instCount = new Label("Line Number " + num + " ");
        instCount.setStyle("-fx-font-size: 16pt");
        Label inst = new Label(instruction);
        inst.setStyle("-fx-font-size: 16pt;");
        topHBox.getChildren().addAll(instCount,inst);
        topHBox.setSpacing(20);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.setPadding(new Insets(20,20,20,20));
        return topHBox;
    }
    HBox getTopHBox( String instruction){
        topHBox = new HBox();

        Label inst = new Label(instruction);
        inst.setStyle("-fx-font-size: 16pt;");
        topHBox.getChildren().addAll(inst);
        topHBox.setSpacing(20);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.setPadding(new Insets(20,20,20,20));
        return topHBox;
    }
    ScrollPane getSp(){
        ScrollPane sp = new ScrollPane();
        sp.setContent(middleGP);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        //sp.setFitToWidth(true);
        sp.setPannable(true);
        return  sp;
    }
    VBox getLeftVBox(){
        leftVBox = new VBox();
        GridPane memGp = new GridPane();
        memGp.setVgap(10);
        memGp.setHgap(10);
        memGp.setPadding(new Insets(10,10,10,10));
        VBox label = new VBox();
        Label l = new Label("Memory");
        l.setStyle("-fx-font-size: 16pt;");
        label.getChildren().add(l);
        label.setAlignment(Pos.CENTER);
        memGp.add(new Label("Address"),0,0);
        memGp.add(new Label("Value"),1,0);
        for ( Map.Entry<String, String> entry : Operation.memory.entrySet()) {
            String key = entry.getKey();
            String tab = entry.getValue();
            memGp.add(new Label(key),0,memGp.getRowCount());
            memGp.add(new Label(tab),1,memGp.getRowCount()-1);
        }
        leftVBox.getChildren().addAll(label,memGp);
        return leftVBox;
    }
    void addRowMiddleGP(int pc,int dynamic){

        if(pc == 0){
            Label f = new Label("Line #");
             f.setStyle("-fx-font-weight: bold;");
             middleGP.add(f,pc,0);

            for (Register r: Register.values()) {
                Label l = new Label(r.getName());
                l.setStyle("-fx-font-weight: bold;");
                middleGP.add(l,r.ordinal()+1,0);
            }
            middleGP.add(new Label("initially"),0,dynamic + 1);
            for (Register r: Register.values()) {
                Label l;
                if(!binary){
                   l = new Label(Tool.twosComplementToDecimal(r.getValue()));
                }
                else{
                   l = new Label(r.getValue());
                }
                middleGP.add(l,r.ordinal()+1,dynamic + 1);

            }

        }
        else{
            middleGP.add(new Label(String.valueOf(pc )),0,dynamic + 1);
           // middleGP.add(new Label(String.valueOf(pc)),0,pc-1);
            for (Register r: Register.values()) {
                Label l;
                if(!binary){
                   l = new Label(Tool.twosComplementToDecimal(r.getValue()));
                }
                else{
                   l = new Label(r.getValue());
                }

               // l.setStyle("-fx-font-weight: bold;");

                middleGP.add(l,r.ordinal()+1,dynamic + 1 );
            }

        }
    }

    public static void main(String[] args) {
        Controller.setlineCount();
        System.out.println(Controller.InstHashMap);
        Register.s0.setValue(Tool.decimaltoTwosComplement("10"));
        Register.s1.setValue(Tool.decimaltoTwosComplement("12"));
        Register.s2.setValue(Tool.decimaltoTwosComplement("32"));
        Register.s5.setValue(Tool.decimaltoTwosComplement("500"));
        launch(args);
        /* //ADD Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("4"));
        Register.rA2.setValue(Tool.decimaltoTwosComplement("2"));
        Operation.add(Register.rAT,Register.rA1,Register.rA2);
        System.out.println(Tool.twosComplementToDecimal(Register.rAT.getValue())); */
        /* //SUB Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("4"));
        Register.rA2.setValue(Tool.decimaltoTwosComplement("2"));
        Operation.sub(Register.rAT,Register.rA1,Register.rA2);
        System.out.println(Tool.twosComplementToDecimal(Register.rAT.getValue())); */
        /* //ADDI Test
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
        /* //SLT Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("2"));
        Register.rA2.setValue(Tool.decimaltoTwosComplement("4"));
        Operation.slt(Register.rAT,Register.rA1,Register.rA2);
        System.out.println(Tool.twosComplementToDecimal(Register.rAT.getValue())); */
        /* //SLTI Test
        Register.rA1.setValue(Tool.decimaltoTwosComplement("2"));
        Operation.slti(Register.rAT,Register.rA1,"4");
        System.out.println(Tool.twosComplementToDecimal(Register.rAT.getValue())); */
        /* //Label Finding Test
        System.out.println(Controller.findLabelLine("Exit"));
         */
        /* //Setting next Instruction
        while(!Controller.endOfProgram){
            System.out.println(Controller.programCounter);
            Controller.setNextInstruction();
            System.out.println(Controller.instruction);
            Controller.programCounter++;
        } */
        /* //beq and bne Test
        Register.s5.setValue(Tool.decimaltoTwosComplement("6"));
        Register.s4.setValue(Tool.decimaltoTwosComplement("6"));
        Controller.executeInstruction();
        Register.s1.setValue(Tool.decimaltoTwosComplement("1"));
        Register.s2.setValue(Tool.decimaltoTwosComplement("2"));
        Controller.executeInstruction();
        System.out.println(Register.s6.getValue());
        Controller.setlineCount();
        System.out.println(Controller.lineCount); */
        /* //LW & SW TEST
         Register.t0.setValue(Tool.decimaltoTwosComplement("6"));
         Register.t3.setValue(Tool.decimaltoTwosComplement("5"));
        Controller.executeInstruction();
        System.out.println(Operation.memory);
        System.out.println(Register.t5.getValue());
        Controller.executeInstruction();
        System.out.println(Operation.memory);
        System.out.println(Register.t5.getValue()); */
         /*//LUI TEST
       Controller.executeInstruction();
       System.out.println(Register.t1.getValue());
      System.out.println(Tool.twosComplementToDecimal( (Register.t1.getValue()) ));
        System.out.println(Tool.twosComplementToDecimal( "00000000000000010000000000000000"));*/

    }
}
