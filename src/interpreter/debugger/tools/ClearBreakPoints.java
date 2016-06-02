/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.tools;

import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 * clears one or all break pts
 * 
 * @author Imran Irfan
 */
public class ClearBreakPoints extends Tool {
    private int breakpoints;
    
    //constructor
    public ClearBreakPoints(){}
    
    @Override
    public void init(int arg){
        breakpoints = arg;
    }
    
    @Override
    public void execute(DVirtualMachine vm){
        vm.removeBreakPoint(breakpoints);
    }
    
    @Override
    public void init(Vector args){}
    
}
