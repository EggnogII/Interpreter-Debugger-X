/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.tools;

import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 * Continues execution from current point
 * @author Imran Irfan
 */
public class ContinueExecution extends Tool {
    //constructor
    public ContinueExecution(){}
    
    @Override
    public void init(int arg){}
    
    @Override
    public void execute(DVirtualMachine vm){
        vm.setRun(true);
        vm.exProgram();
    }
    
    @Override
    public void init(Vector args){}
    
}
