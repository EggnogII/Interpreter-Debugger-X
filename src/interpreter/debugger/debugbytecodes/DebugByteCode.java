/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.debugbytecodes;
import interpreter.bytecodes.ByteCode;
import interpreter.debugger.DVirtualMachine;
import interpreter.VirtualMachine;

/**
 *
 * @author Imran Irfan
 */
public abstract class DebugByteCode extends ByteCode {
    //run
    public abstract void execute(DVirtualMachine virtualMachine);
    //overloaded exe function
    @Override
    public void execute(VirtualMachine virtualMachine){
        execute((DVirtualMachine)virtualMachine);
    }
}
