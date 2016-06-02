package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Vector;

//print out program execution
public class DumpCode extends ByteCode {

    boolean dumpState; //toggle for dumping the program/printing

    //constructor
    public DumpCode() {}

    public void init(Vector args) {
        String state = (String)args.get(0);
        setDumpState(state);
    }

    //returns true if dumping, false if not dumping
    public boolean getDumpState() {
        return dumpState;
    }

    //toggle dumping
    public void setDumpState(String state) {
        if (state.equals("ON")) {
            dumpState = true;
        } else {
            dumpState = false;
        }
    }
    
    //change dump state based on given info
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setDump(dumpState);
    }

    public String toString() {
        if (dumpState) {
            return ("DUMP ON");
        } else {
            return ("DUMP OFF");
        }
    }
}
