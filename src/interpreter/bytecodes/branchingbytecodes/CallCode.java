package interpreter.bytecodes.branchingbytecodes;
import interpreter.bytecodes.LabelCode;
import interpreter.VirtualMachine;
import java.util.Vector;

/*
    Transfers control to indicated function
    @Author Imran Irfan
*/

public class CallCode extends BranchingByteCode {

    protected String label; 
    protected LabelCode code;    
    protected Vector arg;   //args used in the function call.

    //constructor
    public CallCode() {}

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
        arg = virtualMachine.callCounterPush(code.getLocation(), code.getCleanLabelName());
    }

    public String toString() {
        String outString = "", outString2 = "";
        outString = ("CALL " + getLabelString() + "    " +  code.getCleanLabelName() + "(");
        for (int i = 0; i < arg.size(); i++) {
            outString2 = arg.get(i) + outString2;
            if ((i + 1) != arg.size()) {
                outString2 = "," + outString2;
            }
        }
        outString2 += ")";
        return (outString + outString2);
    }
}
