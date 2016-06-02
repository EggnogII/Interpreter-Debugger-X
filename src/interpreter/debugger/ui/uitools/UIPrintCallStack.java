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
public class UIPrintCallStack extends UITool{
    
    @Override
    public void init(Vector args){}
    
    @Override
    public void execute(DVirtualMachine vm){
        for (int i=vm.FERgetSize()-1; i >= 0; i--){ //go through the FER backwards
            if (vm.FERgetFunctionName(i) == null){
                System.out.println("Unnamed Function at line:" + vm.FERgetCurrentLine(i));
            }
            else{
                System.out.println(vm.FERgetFunctionName(i));
            }
        }
    }
    
    @Override
    public void execute(UI ui){
        execute(ui.getVM());
    }
    
}
