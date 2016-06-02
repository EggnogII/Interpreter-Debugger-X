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
 * displays variables of the function running
 * @author Imran
 */
public class UIDisplayVars extends UITool{
    Vector vars = new Vector();
    
    //constructor
    public UIDisplayVars(){}
    
    //constructor overloaded. sets the vars vector to the given vector
    public UIDisplayVars(Vector v){
        vars = v;
    }
    
    public void init(Vector args){}

    //print out a list of all variables in current function
    public void execute(){
        if (vars.isEmpty()){
            System.out.print("No variables");
        }
        while (!vars.isEmpty()){
            if (vars.size() > 2){
                System.out.print(vars.get(0) + " = " + vars.get(1) + ", ");
            }
            else{
                System.out.print(vars.get(0) + " = " + vars.get(1));
            }
            vars.remove(0);
            vars.remove(0);
        }
        System.out.print("\n");
    }
    
    @Override
    public void execute(DVirtualMachine vm){
        vars = vm.FERgetVar();
        execute();
    }
    @Override
    public void execute(UI ui) {
        execute(ui.getVM());
    }
    
}
