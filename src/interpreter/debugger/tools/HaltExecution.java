/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.tools;

import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 * Halts execution
 * @author Imran Irfan
 */
public class HaltExecution extends Tool{
    //constructor
    public HaltExecution(){}
    
    @Override
    public void init(int arg){}
    
    //set element in DVM to be as though it hasn't been run
    @Override
    public void execute(DVirtualMachine vm){
        vm.resetControl();
    }
    
    @Override
        public void init(Vector args){}
}
