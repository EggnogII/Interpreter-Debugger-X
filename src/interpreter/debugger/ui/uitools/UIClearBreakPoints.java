/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;

import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.tools.ClearBreakPoints;
import java.util.Vector;

/**
 * clears the breakpoint
 * @author Imran Irfan
 */
public class UIClearBreakPoints extends ClearBreakPoints{   
    private Vector<Integer> breakpoints = new Vector();
    private boolean clearAll = false;
    
    //constructor
    public UIClearBreakPoints(){}
    
    @Override
    public void init(Vector args){
        try{
            if (args.size() > 0){
                String arg = (String)args.get(0);
                if (arg.equals("ALL") || arg.equals("all")){
                    clearAll = true;
                }
                else{
                    for (int i=0;i<args.size();i++){
                        arg = (String)args.get(i);
                        breakpoints.add(Integer.parseInt(arg));
                    }
                }
            }
        } catch (Exception e){
            System.out.println("Invalid arg(s)");
        }
    }
    
    @Override
    public void execute(DVirtualMachine vm){
        try{
            if (breakpoints.isEmpty() && !clearAll){
                System.out.println("No breakpoints cleared.");
            }
            else{
                if (clearAll){
                    Vector<Integer> breakpoints = vm.getBreakPoints();
                    while(!breakpoints.isEmpty()){
                        super.init(breakpoints.get(0));
                        super.execute(vm);
                        breakpoints = vm.getBreakPoints();
                    }
                    System.out.println("All breakpoints cleared");
                }
                else{
                    System.out.print("Breakpoint");
                    if(breakpoints.size() > 1){
                        System.out.print("s ");
                    }
                    else{
                        System.out.print(" ");
                    }
                    for(int i = 0;i< breakpoints.size();i++){
                        super.init(breakpoints.get(i));
                        super.execute(vm);
                        System.out.print(breakpoints.get(i));
                        if ((breakpoints.size() - 1) == i){
                            if(breakpoints.size() == 1){
                                System.out.print(" cleared. \n");
                            }
                        }
                        else {
                            System.out.print(", ");
                        }
                        
                    }
                }
            } 
        }
            catch(Exception e) {
                System.out.println("Invalid arg(s)");
        }
    }
    
}
