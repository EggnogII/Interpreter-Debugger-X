package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Vector;

//prints the top item of stack onto the screen
public class WriteCode extends ByteCode {
    
//constructor
    public WriteCode() {}

    public void init(Vector args) {}

    //print
    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println(virtualMachine.peekRTS());
    }

    public String toString() {
        return ("WRITE");
    }
}
