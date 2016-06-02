/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;

import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.ui.UI;
import java.util.Vector;

/**
 * Displays the source code of the program
 * @author Imran
 */
public class UIDisplaySource extends UITool{
    @Override
    public void init(Vector args){}
    
    @Override
    public void execute(DVirtualMachine vm){}
    
    @Override
    public void execute(UI ui){
        ui.printInputFile(1, (ui.getVM().sourceFileLength() - 1));
    }
    
}
