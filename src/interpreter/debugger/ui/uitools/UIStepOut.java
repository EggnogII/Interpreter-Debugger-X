/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;

import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.tools.StepOut;

/**
 *
 * @author Imran Irfan
 */
public class UIStepOut extends StepOut{
    
    @Override
    public void execute(DVirtualMachine vm){
        System.out.println("Stepping Out...");
        super.execute(vm);
    }
    
}
