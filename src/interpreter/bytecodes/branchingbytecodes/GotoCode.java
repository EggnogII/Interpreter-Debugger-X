package interpreter.bytecodes.branchingbytecodes;
import interpreter.bytecodes.LabelCode;
import interpreter.VirtualMachine;
import java.util.Vector;

/*
    GOTO
    @Author Imran Irfan
*/

public class GotoCode extends BranchingByteCode{

    private String label; 
    private LabelCode code;    

    //constructor
    public GotoCode() {}

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

    public void execute(VirtualMachine virtualMachine) {
            virtualMachine.setProgCounter(code.getLocation());
    }

    public String toString() {
        return ("GOTO " + getLabelString());
    }
}