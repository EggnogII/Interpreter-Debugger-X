/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;

import interpreter.debugger.*;
import java.util.Stack;
import java.util.Vector;
/**
 * FORMAL name offset
 * @author Imran Irfan
 */
public class DebugFormalCode extends DebugByteCode{
    private String name;
    private int offset;
    
    //constructor
    public DebugFormalCode(){}
    
    public void init(Vector args){
        name = (String)args.get(0);
        String arg = (String)args.get(1);
        offset = Integer.parseInt(arg);
    }
    
    //return formalname
    public String getFormalName(){
        return name;
    }
    
    //return offset
    public int getOffset(){
        return offset;
    }
    
    //execute method
    @Override
    public void execute(DVirtualMachine vm){
        Stack<Integer> RTSHolder = new Stack();
        for (int i=0; i < offset;i++){
            RTSHolder.add(vm.popRTS());
        }
        vm.FERaddLit(name, vm.peekRTS());
        for (int i=0;i<offset;i++){
            vm.pushRTS(RTSHolder.pop());
        }
    }
    
    public String toString(){
        return ("FORMAL" + getFormalName() + " " + getOffset());
    }
}
