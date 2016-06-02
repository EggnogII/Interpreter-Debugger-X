package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Vector;
/*
    Loads the literal value
    LIT n, loads n
    LIT 0 i - form of LIT was generated to load 0 onto the stack in order to
    initialize the variable i to 0
    @Author Imran Irfan
*/
public class LitCode extends ByteCode {

    protected String name = null; 
    protected int lit; 

    //constructor
    public LitCode() {}

    public void init(Vector args) {
        String arg = (String)args.get(0);
        Integer integerArg = Integer.parseInt(arg);
        lit = integerArg.intValue();
        if (args.size() > 1) {
            name = (String)args.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.pushRTS(lit);
    }

    public String toString() {
        String outString = ("LIT " + lit);
        if (name != null) {
            outString += (" int " + name);
        }
        return outString;
    }
}
