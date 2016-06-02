/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;

import interpreter.bytecodes.StoreCode;
import interpreter.debugger.DVirtualMachine;
import interpreter.VirtualMachine;

/**
 * Debugger implementation of StoreCode
 * @author Imran Irfan
 */
public class DebugStoreCode extends StoreCode{
    //pushes previously given value onto the stack
    //adds lit given a name intot he FER
    public void execute(DVirtualMachine vm){
        vm.FERaddLit(id, vm.peekRTS());
        super.execute(vm);
    }
    
    //overloaded execute method
    @Override
    public void execute(VirtualMachine vm){
        execute((DVirtualMachine)vm);
    }
}
