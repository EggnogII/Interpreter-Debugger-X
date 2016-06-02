/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;

import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.tools.HaltExecution;

/**
 *UI implementation of Halt Execution
 * @author Imran
 */
public class UIHaltExecution extends HaltExecution{
    
    @Override
    public void execute(DVirtualMachine vm){
        super.execute(vm);
        System.out.println("Halted..reset");
    }
    
}
