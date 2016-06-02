package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Vector;

//kill vm

public class HaltCode extends ByteCode {

    //constructor
    public HaltCode() {}

    public void init(Vector args) {}

    //kill vm
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setRun(false);
    }

    public String toString() {
        return ("HALT");
    }
}
