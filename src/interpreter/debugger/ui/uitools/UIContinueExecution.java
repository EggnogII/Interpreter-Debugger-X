/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;

import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.tools.ContinueExecution;

/**
 * UI implementation of Continue execution
 * @author Imran
 */
public class UIContinueExecution extends ContinueExecution{
    
    @Override
    public void execute(DVirtualMachine vm){
        System.out.println("Executing . . .");
        super.execute(vm);
    }
    
}
