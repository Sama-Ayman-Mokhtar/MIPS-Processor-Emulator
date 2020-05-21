package sample;
import java.io.File;
import java.io.PrintWriter;
public class FileWriter {
    static PrintWriter write ;
    static void writeFile(String s) {
        try {
            File file = new File("AssemblyCode");
            write = new PrintWriter(file);

        } catch (Exception e) {
            System.out.println("File not Found! {while writing}");
        }
        write.printf(s);
        write.close();
    }
}


