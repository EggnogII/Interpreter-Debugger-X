/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;

import interpreter.bytecodes.ArgsCode;
import interpreter.debugger.DVirtualMachine;
import interpreter.VirtualMachine;

/**
 *
 * @author Imran Irfan
 */
public class DebugArgCode extends ArgsCode{
    
    public void execute(DVirtualMachine vm){
        super.execute(vm);
        vm.setNumArgs(newFrameLocation);
    }
    
    //overloaded execute method
    @Override
    public void execute(VirtualMachine vm){
        execute((DVirtualMachine)vm);
    }
}
