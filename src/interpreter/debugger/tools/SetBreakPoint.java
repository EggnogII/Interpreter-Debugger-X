/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.tools;

import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 * Sets the breakpoint requested by the user, if the requested breakpoint is valid
 * @author Imran Irfan
 */
public class SetBreakPoint extends Tool {
    private int breakpoints;
    //constructor
    public SetBreakPoint(){}
    
    public void init(int bp){
        breakpoints = bp;
    }
    
    @Override
    public void execute(DVirtualMachine vm){
        if(vm.isValidBreakPT(breakpoints)){
            vm.setBreakPoint(breakpoints);
        }
    }
    
    @Override
    public void init(Vector args){}
    
}
