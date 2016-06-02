/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;

import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.tools.Tool;
import interpreter.debugger.ui.UI;
import java.util.Vector;

/**
 * UI version of the tool class
 * @author Imran Irfan
 */
public abstract class UITool extends Tool{
    //initializes the tool
    public abstract void init(Vector Args);
    
    @Override
    public void init(int arg){}
    
    //executes the tool
    public abstract void execute(UI ui);
    
    @Override
    public void execute(DVirtualMachine vm){}
    
}
