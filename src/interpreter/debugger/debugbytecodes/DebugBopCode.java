/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;
import interpreter.bytecodes.BopCode;
import interpreter.debugger.DVirtualMachine;
import interpreter.VirtualMachine;

/**
 *
 * @author Imran Irfan
 */
public class DebugBopCode extends BopCode{
    
    private Exception ArithmeticException;
    
    @Override
    public void execute(VirtualMachine vm){
        execute((DVirtualMachine)vm);
    }
    
    public void execute(DVirtualMachine vm){
        int s = vm.popRTS();
        long result = super.execute(vm.popRTS(), s);
        try{
            if ((result < -2147483648) || (result > 2147483647)) {
                throw ArithmeticException;
            } 
            else {
                vm.pushRTS((int)result);
            }
        } catch (Exception e){
            System.out.println("Your result is out of int type bound");
            System.out.println("RESTARTING PROGRAM");
            vm.refresh();
            vm.setProgCounter(-1);
        }
    }   
}
