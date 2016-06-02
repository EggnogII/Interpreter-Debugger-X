/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.tools;
import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 * abstract class outlining methods to be implemented by debugger
 * @author Imran Irfan
 */
public abstract class Tool {
    //initiate command with given args
    public abstract void init(int arg);
    
    public abstract void init(Vector args);
    
    //executes
    public abstract void execute(DVirtualMachine vm);
    
}
