/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;

import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 * FUNCTION name start end
 * @author Imran Irfan
 */
public class DebugFunctionCode extends DebugByteCode{
    private String name;
    private int start, end; 
    
    //constructor
    public DebugFunctionCode(){}
    
    public void init(Vector args){
        name = (String)args.get(0);
        String arg = (String)args.get(1);
        start = Integer.parseInt(arg);
        arg = (String)args.get(2);
        end = Integer.parseInt(arg);
    }
    
    //return a string denoting the name of the function
    public String getName(){
        return name;
    }
    
    //return start line
    public int getStart(){
        return start;
    }
    
    //return end line
    public int getEnd(){
        return end;
    }
    
    //fills the start, end and name fields of current FER
    @Override
    public void execute(DVirtualMachine vm){
        vm.FERsetSubmitFunctionName(name);
        vm.FERsetSubmitStartLine(start);
        vm.FERsetSubmitEndLine(end);
    }
    
    public String toString(){
        return ("FUNCTION" + getName() + " " + getStart() + " " + getEnd());
    }
}
