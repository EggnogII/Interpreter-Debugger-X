/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;

import interpreter.VirtualMachine;
import interpreter.bytecodes.ReturnCode;
import interpreter.debugger.DVirtualMachine;

/**
 * debugger version of ReturnCode
 * 
 * @author Imran
 */
public class DebugReturnCode extends ReturnCode {
    
    //return from the current function, save the return value
    public void execute(DVirtualMachine vm){
        if (vm.isTrace()){
            vm.functionTrace("exit: " + vm.FERgetFunctionName() + ": " + vm.peekRTS());
        }
        vm.popFER();
        super.execute(vm);
    }
    
    //overloaded execute
    @Override
    public void execute(VirtualMachine vm){
        execute ((DVirtualMachine)vm);
    }
    
}
