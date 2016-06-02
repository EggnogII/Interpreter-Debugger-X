/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;

import interpreter.bytecodes.PopCode;
import interpreter.debugger.DVirtualMachine;
import interpreter.VirtualMachine;

/**
 * Debugger implementation of PopCode
 * 
 * @author Imran Irfan
 */
public class DebugPopCode extends PopCode{
   //pops the initialize number of items
    public void execute(DVirtualMachine vm){
        vm.FERpopLit(num);
        super.execute(vm);
    }
    
    //overloaded execute
    
    @Override
    public void execute(VirtualMachine vm){
        execute((DVirtualMachine)vm);
    }
    
}
