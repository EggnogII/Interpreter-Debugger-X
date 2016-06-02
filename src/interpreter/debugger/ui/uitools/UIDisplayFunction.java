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
 *
 * @author Imran Irfan
 */
public class UIDisplayFunction extends UITool{
    
    @Override
    public void init(Vector args){}
    
    @Override
    public void execute(DVirtualMachine vm){}
    
    @Override
    public void execute(UI ui){
        if (ui.getVM().FERgetSize() == 1 &&  ui.getVM().FERgetFunctionName() == null){
            ui.printInputFile(1, ui.getVM().sourceFileLength()-1);
        }
        else{
            ui.printInputFile(ui.getVM().FERgetStartLine(), ui.getVM().FERgetEndLine());
        }
    }
}
