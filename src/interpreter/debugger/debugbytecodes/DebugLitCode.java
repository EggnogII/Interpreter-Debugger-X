/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;

import interpreter.bytecodes.LitCode;
import interpreter.debugger.DVirtualMachine;
import interpreter.VirtualMachine;

/**
 * Debug version of LitCode
 * Lit n: Loads the literal value n
 * Lit 0 i - form of Lit generated to load 0 in the stack in
 * order to initialize variable i to 0 and reserve space on
 * RTS for i
 * @author Imran Irfan
 */
public class DebugLitCode extends LitCode {
    //pushes previous value onto the stack
    public void execute(DVirtualMachine vm){
        if (name != null){
            vm.FERaddLit(name, lit);
        }
        super.execute(vm);
    }
    
    //overloaded execute
    @Override
    public void execute(VirtualMachine vm){
        execute((DVirtualMachine)vm);
    }
    
}
