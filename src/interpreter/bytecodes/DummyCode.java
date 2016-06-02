package interpreter.bytecodes;

import interpreter.VirtualMachine;
import java.util.Vector;

// bytecode for unprocessable bytecodes, to allow the program
//to run through
public class DummyCode extends ByteCode{

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(VirtualMachine virtualMachine) {}

    @Override
    public String toString() {return ("--Unrecognized ByteCode--");}

}
