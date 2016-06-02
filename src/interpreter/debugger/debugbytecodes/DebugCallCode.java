/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;

import interpreter.bytecodes.branchingbytecodes.CallCode;
import interpreter.debugger.*;
import interpreter.VirtualMachine;
import java.util.Stack;

/**
 *
 * @author Imran Irfan
 */
public class DebugCallCode extends CallCode{
    
    public void execute(DVirtualMachine vm){
    
        vm.pushFER();
        
        if (vm.isTrace() && !code.getCleanLabelName().equals("Write")){
            Stack<Integer> poppedRTS = new Stack();
            int numArgs = vm.getNumArgs();
            String tString = code.getCleanLabelName() + "(";
            while(numArgs > 0){
                poppedRTS.push(vm.popRTS());
                numArgs--;
            }
            while(!poppedRTS.isEmpty()){
                tString +=  poppedRTS.peek();
                if (poppedRTS.size() > 1){
                    tString += ", ";
                }
                vm.pushRTS(poppedRTS.pop());
            }
            tString += ")";
            vm.functionTrace(tString);
        }
        super.execute(vm);
    }
    
    //overloaded execute
    public void execute(VirtualMachine vm){
        execute((DVirtualMachine)vm);
    }
    
}
