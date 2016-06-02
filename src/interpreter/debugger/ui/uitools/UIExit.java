/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;

import interpreter.debugger.ui.UI;
import java.util.Vector;

/**
 *
 * @author Imran Irfan
 */
public class UIExit extends UITool{
    
    @Override
    public void init(Vector args){}
    
    @Override
    public void execute(UI ui){
        System.out.println("Exiting...");
        ui.stopRun();
    }
    
}
