package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Vector;

//The Args bytecode is used prior to calling a function

public class ArgsCode extends ByteCode {

    protected int newFrameLocation; //Number of arguements for the following function.

    //constructer
    public ArgsCode() {}

    public void init(Vector args) {
        String arg = (String)args.get(0);
        newFrameLocation = Integer.parseInt(arg);
    }

    //start a new frame at given location
    //allocate previous given number of args on stack to following function
    
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.runStackNewFrameAt(newFrameLocation);
    }

    public String toString() {
        return ("ARGS " + newFrameLocation);
    }
}
