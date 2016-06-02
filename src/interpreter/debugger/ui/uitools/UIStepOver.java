/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;

import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.tools.StepOver;

/**
 *
 * @author Imran Irfan
 */
public class UIStepOver extends StepOver{
    
    @Override
    public void execute(DVirtualMachine vm){
        System.out.println("STEPPING OVER");
        super.execute(vm);
    }
}
