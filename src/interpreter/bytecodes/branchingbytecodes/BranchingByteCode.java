package interpreter.bytecodes.branchingbytecodes;
import interpreter.bytecodes.ByteCode;
import interpreter.bytecodes.LabelCode;
import interpreter.VirtualMachine;
import java.util.Vector;

/**
 * Abstract child of Bytecode which deals with branching
 *
 * @author Imran
 */
public abstract class BranchingByteCode extends ByteCode{
    
    //inherited from ByteCode
    public abstract void init(Vector args);
    public abstract String toString();

    @Override
    public abstract void execute(VirtualMachine virtualMachine);

    //return the name of the label respective to this code
    public abstract String getLabelString();

    //set the name of the label respective to this code
    public abstract void setLabelString(String labelCode);

    //set the label of the code to the respective labelcode
    public abstract void setLabel(LabelCode labelCode);

    //return the labelcode to the linked code
    public abstract LabelCode getLabel();
    
}
