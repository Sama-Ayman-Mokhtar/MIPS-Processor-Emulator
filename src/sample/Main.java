package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import java.util.Map;

public class Main extends Application {
    private Boolean binary = false;
     private BorderPane bp;
     private GridPane middleGP = new GridPane();
     private ScrollPane sp = getSp();
     private HBox topHBox;
     private VBox bottomVBox;
     private int dynamic;
     private VBox leftVBox;
     static Boolean start = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

         bp = new BorderPane();

         HBox topi = new HBox();
         Label inst = new Label("Please, Delete The Assembly Code Below, Paste Yours Then click Continue");
        inst.setStyle("-fx-font-size: 16pt;");
        topi.getChildren().addAll(inst);
        topi.setSpacing(20);
        topi.setAlignment(Pos.CENTER);
        topi.setPadding(new Insets(20,20,20,20));
         bp.setTop(topi);

         VBox bv = new VBox();
        Button cont = new Button("Continue");
        cont.setStyle("-fx-font-size: 16pt");
        bv.getChildren().add(cont);
        bv.setAlignment(Pos.CENTER);
        bv.setPadding(new Insets(20,20,20,20));
        cont.setOnAction(e -> nextAction());
        bp.setBottom(bv);

         TextArea ta = new TextArea();
         ta.setText("addi $s0, $zero, 10 \naddi $s1, $zero, 12 \naddi $s2, $zero, 32\naddi $s5, $zero, 500 \nadd $t0, $s0, $s1\nbeq $s0, $s1, Label\nsub $t1, $s0, $s1 \nLabel: addi $t2, $s0, 5 \nbne $s0, $s1, Go \nadd $t7, $t2, $t1 \nsra $s2, 3 \nGo: add $s6, $s5, $zero \nsw $s5, 3($s5) \nj There \nslti $t3, $s0, 5 \nThere: lw $s4, 3($s5) \nor $t5, $t2, $s0 \nnor $t6, $t2, $s0 \nsll $at, $s2, 2 \nsrl $at, $s2, 1 \nsra $at, $s2, 2 \nlui $s7, 1 \nExit:");
         bp.setCenter(ta);
         cont.setOnAction(e-> {
             FileWriter.writeFile(ta.getText());
             addRowMiddleGP(0,0);
             middleGP.setVgap(10);
             middleGP.setHgap(10);
             middleGP.setPadding(new Insets(20,20,20,20));
             //middleGP.setGridLinesVisible(true);
            getLeftVBox();
            bp.setRight(leftVBox);
            bp.setCenter(sp);
            bp.setBottom(getBottomVBox("Next Instruction"));
            bp.setTop(getTopHBox("Click 'Next Instruction' to Start"));
            Controller.setlineCount();
        //System.out.println(Controller.InstHashMap);
       // Register.s0.setValue(Tool.decimaltoTwosComplement("10"));
        //Register.s1.setValue(Tool.decimaltoTwosComplement("12"));
       // Register.s2.setValue(Tool.decimaltoTwosComplement("32"));
        //Register.s5.setValue(Tool.decimaltoTwosComplement("500"));

         });
        Scene scene = new Scene(bp, 900, 600);
        primaryStage.setTitle("Emulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private VBox getBottomVBox(String s){
        bottomVBox = new VBox();
        Button nextInstruction = new Button(s);
        nextInstruction.setStyle("-fx-font-size: 16pt");
        bottomVBox.getChildren().add(nextInstruction);
        bottomVBox.setAlignment(Pos.CENTER);
        bottomVBox.setPadding(new Insets(20,20,20,20));
        nextInstruction.setOnAction(e -> nextAction());
        return bottomVBox;
    }

    private void nextAction(){
        if(Controller.programCounter <= Controller.lineCount){
            bp.setTop(getTopHBox(Controller.programCounter , Controller.InstHashMap.get(Controller.programCounter)));
            int pc = Controller.programCounter;
            Controller.executeInstruction();
            bp.setRight(getLeftVBox());
            dynamic ++;
            addRowMiddleGP(pc,dynamic);
            //System.out.println(Controller.programCounter);
            //System.out.println(Register.t0.getValue());
        }
        else{
            bp.setTop(getTopHBox( "End Of Program"));
        }
    }
    private HBox getTopHBox(int num, String instruction){
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
    private HBox getTopHBox( String instruction){
        topHBox = new HBox();

        Label inst = new Label(instruction);
        inst.setStyle("-fx-font-size: 16pt;");
        topHBox.getChildren().addAll(inst);
        topHBox.setSpacing(20);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.setPadding(new Insets(20,20,20,20));
        return topHBox;
    }
    private ScrollPane getSp(){
        ScrollPane sp = new ScrollPane();
        sp.setContent(middleGP);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        //sp.setFitToWidth(true);
        sp.setPannable(true);
        return  sp;
    }
    private VBox getLeftVBox(){
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

            memGp.add(new Label(key),0,Operation.memory.size());
            memGp.add(new Label(tab),1,Operation.memory.size()) ;
        }
        leftVBox.getChildren().addAll(label,memGp);
        return leftVBox;
    }
    private void addRowMiddleGP(int pc,int dynamic){

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
                launch(args);
    }
}
