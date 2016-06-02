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
public class UIListBreakPoints extends UITool{

    @Override
    public void init(Vector Args) {}

    @Override
    public void execute(DVirtualMachine vm){
        Vector<Integer> breakpoints = vm.getBreakPoints();
        if(!breakpoints.isEmpty()){
            System.out.print("Breakpoint(s) set at ");
            for (int i=0;i<breakpoints.size();i++){
                System.out.print(breakpoints.get(i));
                if((breakpoints.size()-1) != i){
                    System.out.print(", ");
                }
            }
        }
        else{
            System.out.println("NO BREAKPOINTS SET.");
        }
        System.out.println();
    }
    @Override
    public void execute(UI ui) {
        execute(ui.getVM());
    }
    
}
