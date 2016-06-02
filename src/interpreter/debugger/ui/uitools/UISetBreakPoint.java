/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;

import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.tools.SetBreakPoint;
import java.util.Vector;

/**
 * Sets breakpoints at valid locations
 *
 * @author Imran
 */
public class UISetBreakPoint extends SetBreakPoint{
    
    private Vector<Integer> breakpoints = new Vector();
    
    //constructor
    public UISetBreakPoint(){}
    
    @Override
    public void init(Vector args){
        try{
            for (int i=0; i< args.size();i++){
                String arg = (String)args.get(i);
                breakpoints.add(Integer.parseInt(arg));
            }
            
        } catch(Exception e){
            System.out.println("Invalid arg(s)");
        }
    }
    
    @Override
    public void execute(DVirtualMachine vm){
        try{
            Vector<Integer> invalidBPs = new Vector();
            for (int i = 0; i < breakpoints.size();i++){
                if(!vm.isValidBreakPT(breakpoints.get(i))){
                    invalidBPs.add(breakpoints.get(i));
                }
            }
            for(int i=0;i<invalidBPs.size();i++){
                breakpoints.remove(invalidBPs.get(i));
            }
            if (breakpoints.isEmpty()){
                System.out.println("No breakpoints set");
            }
            else{
                System.out.println("Breakpoint(s) set at ");
                for (int i=0;i<breakpoints.size();i++){
                    if (!vm.isBreakpoint(breakpoints.get(i))){
                        super.init(breakpoints.get(i));
                        super.execute(vm);
                    }
                    System.out.print(breakpoints.get(i));
                    if ((breakpoints.size()-1) != i){
                        System.out.print(", ");
                    }
                }
                System.out.print("\n");
            }
            if (!invalidBPs.isEmpty()){
                for (int i = 0;i < invalidBPs.size();i++){
                    System.out.print(invalidBPs.get(i));
                    if((invalidBPs.size()-1) == i){
                        if(invalidBPs.size() == 1){
                            System.out.print(" is invalid \n");
                        }
                        else{
                            System.out.print(" are invalid \n");
                        }
                    }
                    else{
                        System.out.print(", ");
                    }
                }
            }
        } catch(Exception e){
            System.out.println("Invalid arg(s)");
        }
    }
}
