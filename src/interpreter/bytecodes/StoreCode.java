package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Vector;

//store the top item in stack into specified location
public class StoreCode extends ByteCode {

    private int offset, 
            value = 0;  
    protected String id;  

    //constructor
    public StoreCode() {}

    public void init(Vector args) {
        String arg = (String)args.get(0);
        offset = Integer.parseInt(arg);
        if (args.size() > 1) {
            id = (String)args.get(1);
        }
    }

    //stores the top item from stack into specified location
    @Override
    public void execute(VirtualMachine virtualMachine) {
        value = virtualMachine.runStackStore(offset);
    }

    public String toString() {
        return ("STORE " + id + "     " + id + " = " + value);
    }
}
