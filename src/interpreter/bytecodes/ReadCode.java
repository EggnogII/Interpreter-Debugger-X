package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.io.*;
import java.util.Vector;

//reads integer, prompts the user for input, value goes on top of stack
public class ReadCode extends ByteCode {

    //constructor
    public ReadCode() {}

    public void init(Vector args) {}

    //asks user for an integer, reads it, and pushes
    @Override
    public void execute(VirtualMachine virtualMachine) {
        try {
            System.out.print("Integer?: "); 
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inputInt = br.readLine();
            Integer integerArg = Integer.parseInt(inputInt);
            virtualMachine.pushRTS(integerArg);
        } catch (Exception e) {
            System.out.println("INVALID DATA ENTRY");
            System.exit(1);
        }
    }

    public String toString() {
        return ("READ");
    }
}
