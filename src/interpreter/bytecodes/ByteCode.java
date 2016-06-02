package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Vector;

/**
 * the abstract class which deals with the byte codes that do
 * not branch
 * @author Imran
 */

public abstract class ByteCode {

    //initializes code
    public abstract void init(Vector args);

    //run code
    public abstract void execute(VirtualMachine virtualMachine);
    
    //make a string of the code
    @Override
    public abstract String toString();

}