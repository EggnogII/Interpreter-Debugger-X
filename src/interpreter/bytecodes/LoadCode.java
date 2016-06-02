package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Vector;

/*
    Pop top of stack; store value in the offset n from
    the start of the frame from the top of the stack
*/

public class LoadCode extends ByteCode {

    private int offset; //Number of places from the start of the frame to place the popped top of the stack.
    protected String id; //Name of the variable for the popped/placed item.

   //constructer
    public LoadCode() {}

    public void init(Vector args) {
        String arg = (String)args.get(0);
        offset = Integer.parseInt(arg);
        id = (String)args.get(1);
    }

    //pop the top of the stack, store value in the offset
    //TOP OF FRAME -> TOP OF STACK
    
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.runStackLoad(offset);
    }

    public String toString() {
        return ("LOAD " + id + "     <load " + id + ">");
    }
}
