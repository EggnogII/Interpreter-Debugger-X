/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;

import interpreter.bytecodes.LoadCode;
import interpreter.debugger.DVirtualMachine;
import interpreter.VirtualMachine;

/**
 * Debugger implementation of LoadCode
 *
 * @author Imran Irfan
 */
public class DebugLoadCode extends LoadCode{
    //pushes previously given value onto the stack
    //adds the literal value, given it has a name into the FER
    public void execute(DVirtualMachine vm){
        super.execute(vm);
        vm.FERaddLit(id, vm.peekRTS());
    }
    
    
    //overloaded execute
    public void execute(VirtualMachine vm){
        execute((DVirtualMachine)vm);
    }
}
