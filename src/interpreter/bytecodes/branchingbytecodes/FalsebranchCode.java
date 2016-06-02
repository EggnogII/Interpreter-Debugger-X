package interpreter.bytecodes.branchingbytecodes;
import interpreter.bytecodes.LabelCode;
import interpreter.VirtualMachine;
import java.util.Vector;

// pop top of stack to check if its false
public class FalsebranchCode extends BranchingByteCode {

    private String label; //String representation of the label
    private LabelCode code;    //LabelCode to which this code links

    //constructor
    public FalsebranchCode() {}

    public void init(Vector args) {
        label = (String)args.get(0);
        String[] editedLabelString = label.split(" ");
        label = editedLabelString[0];
    }

    public String getLabelString() {
        return label;
    }

    public void setLabelString(String labelCode) {
        label = labelCode;
    }

    public void setLabel(LabelCode labelCode) {
        code = labelCode;
    }

    public LabelCode getLabel() {
        return code;
    }

    //move control of function to address given in the labelcode
    //associated with this code if the top of the stack is false
    @Override
    public void execute(VirtualMachine virtualMachine) {
        if (virtualMachine.popRTS() == 0) {
            virtualMachine.setProgCounter(code.getLocation());
        }
    }

    public String toString() {
        return ("FALSEBRANCH " + getLabelString());
    }
}
