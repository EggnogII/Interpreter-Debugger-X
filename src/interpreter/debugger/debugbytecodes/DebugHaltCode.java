/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;
import interpreter.bytecodes.HaltCode;
import interpreter.VirtualMachine;
import interpreter.debugger.DVirtualMachine;

/**
 * Debug implementation of HALT CODE
 * @author Imran Irfan
 */
public class DebugHaltCode extends HaltCode{
    
    public void execute (DVirtualMachine vm){
        super.execute(vm);
        vm.refresh();
        vm.setProgCounter(-1);
    }
    
    //overloaded execute
    @Override
    public void execute(VirtualMachine vm){
        execute((DVirtualMachine)vm);
    }
    
}
