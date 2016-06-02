/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;
import interpreter.debugger.DVirtualMachine;
import java.util.Vector;
/**
 *
 * @author Imran Irfan
 */
public class DebugLineCode extends DebugByteCode {
    private int line;
    
    //constructor
    public DebugLineCode(){}
    
    public void init(Vector args){
        String arg =(String)args.get(0);
        line = Integer.parseInt(arg);
    }
    
    //return the line
    public DebugLineCode getLine(){
        return this;
    }
    
    //return linenum
    public int getLineNum(){
        return line;
    }
    
    //set current FER current line to this line code
    public void execute(DVirtualMachine vm){
        vm.FERsetSubmitCurrentLine(getLineNum());
    }
    
    public String toString(){
        return ("LINE" + getLineNum());
    }
}
