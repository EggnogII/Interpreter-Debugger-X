/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;

import interpreter.bytecodes.ReadCode;
import interpreter.debugger.DVirtualMachine;
import interpreter.VirtualMachine;
import java.io.*;


/**
 * Debug implementation of ReadCode
 * @author Imran Irfan
 */
public class DebugReadCode extends ReadCode{
    
    @Override
    public void execute(VirtualMachine vm){
        execute((DVirtualMachine)vm);
    }
    
    public void execute(DVirtualMachine vm){
        try{
            System.out.print("Enter a integer: "); //prompt user for input
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            Integer arg = Integer.parseInt(input);
            vm.pushRTS(arg);
        } catch(Exception e){
            System.out.println("The data is invalid, try again.");
            execute(vm);
        }
    }
}
