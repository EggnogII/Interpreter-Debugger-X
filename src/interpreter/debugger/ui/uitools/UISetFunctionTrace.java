/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;

import interpreter.debugger.tools.SetFunctionTracing;
import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 *
 * @author Imran Irfan
 */
public class UISetFunctionTrace extends SetFunctionTracing{
    
    @Override
    public void init(Vector args){
        try{
            super.init(args);
            
        } catch(Exception e){
            System.out.println("No arg");
        }
    }
    
    @Override
    public void execute(DVirtualMachine vm){
        super.execute(vm);
        System.out.print("Tracing is ");
        if (tState){
            System.out.println("ON");
        }
        else{
            System.out.println("OFF");
        }
    }
    
    
}
