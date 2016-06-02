package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Vector;

//return from function
public class ReturnCode extends ByteCode {

    protected String name = null; 
    protected String cleanName = null; 
    protected int returnValue; 

    //constructor
    public ReturnCode() {}

    public void init(Vector args) {
        if (args.size() != 0) {
            name = (String)args.get(0);
        }
    }

    //return from current function, save value
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.returnCounterPop();
        cleanName = virtualMachine.retrieveCurrentFunctionName();
        returnValue = virtualMachine.peekRTS();
    }

    public String toString() {
        if (name == null) {
            return ("RETURN     " + cleanName + ":" + returnValue);
        } else {
            return ("RETURN " + name + "     " + cleanName + ":" + returnValue);
        }
    }
}
