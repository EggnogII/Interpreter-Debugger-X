/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;
import interpreter.bytecodes.branchingbytecodes.FalsebranchCode;
import interpreter.bytecodes.branchingbytecodes.GotoCode;
import interpreter.bytecodes.branchingbytecodes.BranchingByteCode;
import interpreter.bytecodes.LabelCode;
import interpreter.bytecodes.ByteCode;
import interpreter.Program;
import interpreter.debugger.debugbytecodes.DebugCallCode;
import java.util.*;

/**
 * Debugger implementation of Program
 *
 * @author Imran
 */
public class DProgram extends Program {
    private ArrayList dprog = new ArrayList(); //hold the bytecodes
    private Hashtable<String, LabelCode> labelTable = new Hashtable(); //contain all labels for link to BranchByte codes
    private Vector connectingCodes = new Vector(); //contains the BranchByte codes for link
    
    //resolveAddress
    @Override
    public void resolveAddress(){
        BranchingByteCode BB;
        while(!connectingCodes.isEmpty()){ //while the codes to link Vector is empty
            BB = (BranchingByteCode)connectingCodes.get(0);
            BB.setLabel(labelTable.get(BB.getLabelString()));
            connectingCodes.remove(0);
        }
    }
    
    //addByteCode
    @Override
    public void addByteCode(ByteCode B){
        addCounter++;
        dprog.add(B); //might be ambigous
        String name;
        if (B instanceof LabelCode){
            LabelCode type = (LabelCode)B;
            name = type.getLabelName();
            type.setLocation(addCounter);
            labelTable.put(name, type);
        }
        if(B instanceof GotoCode || B instanceof FalsebranchCode || B instanceof DebugCallCode){ //later add condition "B instanceof DebuggerCallCode"
            BranchingByteCode BB = (BranchingByteCode)B;
            if (labelTable.contains(BB.getLabelString())){
                BB.setLabel(labelTable.get(BB.getLabelString()));
            }
            else{
                connectingCodes.add(BB); //add BranchingByteCode for code linkage
            }
        }
    }
//getCode
   @Override
   public ByteCode getCode(int pc){
       return (ByteCode)dprog.get(pc);
   }
    
}      
       
        