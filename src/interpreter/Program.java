package interpreter;
import interpreter.bytecodes.branchingbytecodes.FalsebranchCode;
import interpreter.bytecodes.branchingbytecodes.GotoCode;
import interpreter.bytecodes.branchingbytecodes.BranchingByteCode;
import interpreter.bytecodes.branchingbytecodes.CallCode;
import interpreter.bytecodes.LabelCode;
import interpreter.bytecodes.ByteCode;
import java.util.*;

//hold the bytecode program
//resolve addresses in program
public class Program {

    private ArrayList program = new ArrayList(); //contains all the bytecodes loaded
    private Hashtable<String, LabelCode> labelTable = new Hashtable(); //labels for connecting to branchingbytecodes
    private Vector<BranchingByteCode> connectingCodes = new Vector(); //contains branchingbytecodes.
    protected static int addCounter = -1; //Begins program counter at -1.

    //constructor
    public Program() {}

    //looks for the labels to connect to branchingbytecodes and 
    //gives the respective labelcode
    public void resolveAddress() {
        BranchingByteCode BB;
        while (!connectingCodes.isEmpty()) {
            BB = (BranchingByteCode)connectingCodes.get(0);
            BB.setLabel(labelTable.get(BB.getLabelString()));
            connectingCodes.remove(0);
        }
    }
    
    //loads program with bytecodes
    //if bytecode is a labelcode, goto, falsbranch, or callcode
    //it will try to resolve its address, if not it gets logged
    public void addByteCode(ByteCode B) {
        addCounter++;
        program.add(B);
        String codeName;
        if (B instanceof LabelCode) {
            LabelCode labelCodeType = (LabelCode)B;
            codeName = labelCodeType.getLabelName();
            labelCodeType.setLocation(addCounter);
            labelTable.put(codeName, labelCodeType);
        }
        if(B instanceof GotoCode || B instanceof FalsebranchCode || B instanceof CallCode) {
             BranchingByteCode branchingByteCode = (BranchingByteCode)B;
             if(labelTable.contains(branchingByteCode.getLabelString())){
                 branchingByteCode.setLabel(labelTable.get(branchingByteCode.getLabelString()));
             } else {
                 connectingCodes.add(branchingByteCode);
             }
        }
    }
    
    //return bytecode at given line in program
    public ByteCode getCode(int pc) {
        return (ByteCode)program.get(pc);
    }

}
