/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;

import interpreter.bytecodes.WriteCode;
import interpreter.debugger.DVirtualMachine;
import interpreter.VirtualMachine;

/**
 * Debug implementation of write code
 * @author Imran Irfan
 */
public class DebugWriteCode extends WriteCode{
    @Override
    public void execute(VirtualMachine vm){
        execute((DVirtualMachine)vm);
    }
    
    public void execute(DVirtualMachine vm){
        if(vm.isTrace()){
            vm.functionTrace("Write(" +  vm.peekRTS() + ")");
        }
        super.execute(vm);
    }
}
