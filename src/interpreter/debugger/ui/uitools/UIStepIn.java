/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;

import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.tools.StepIn;

/**
 *
 * @author Imran Irfan
 */
public class UIStepIn extends StepIn{
    
    @Override
    public void execute(DVirtualMachine vm){
        System.out.println("STEPPING IN");
        super.execute(vm);
    }
    
}
