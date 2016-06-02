package interpreter.debugger.tools;

import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 * Step In - Steps into the function on the current line, handles the
 * formals then stops but if there is no function call on the next line, it
 * does one of the following things:
 *  -If there is a return statement, it performs a StepOut.
 *  -If there is no return statement, it simply stops once it has processed
 *      the line it is on.
 * 
 * @author Imran Irfan
 */
public class StepIn extends Tool {

    @Override
    public void init(int arg) {}

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(DVirtualMachine vm) {
        vm.stepState();

        int envSize = vm.FERgetSize(),
                currentLine = vm.FERgetCurrentLine();
        
        //Search the current line
        while(!vm.peekNextByteCode().equals("class interpreter.debugger.debugbytecodes.DebugFunctionCode") &&
                    !vm.peekNextByteCode().equals("class interpreter.debugger.debugbytecodes.DebugReturnCode") &&
                     (envSize == vm.FERgetSize()) &&
                     (currentLine == vm.FERgetCurrentLine())) {

            //If neither ReturnCode, FunctionCode, nor CallCode have been found, continue processing ByteCodes as normal.
            vm.exProgram();
            vm.stepState();
        }

        //If we found a FunctionCode or a new FER was pushed
        if ((envSize != vm.FERgetSize()) ||
                    vm.peekNextByteCode().equals("class interpreter.debugger.debugbytecodes.DebugFunctionCode")) {

            //Advance to the FunctionCode if we've due to a new FER.
            while(!vm.peekNextByteCode().equals("class interpreter.debugger.debugbytecodes.DebugFunctionCode")) {
                vm.exProgram();
                vm.stepState();
            }

            //Process through the FunctionCode and any FormalCodes following.
            for (int i = (vm.getNumArgs() + 1); i != 0; i--) {
                vm.exProgram();
                vm.stepState();
            }

        }

        //If we found a ReturnCode in that line, return to the caller (i.e. step out).
        if (vm.peekNextByteCode().equals("class interpreter.debugger.debugbytecodes.DebugReturnCode")) {
            envSize = vm.FERgetSize();

            //If we are starting at a breakpoint, we have to move past it before we can contine with the out-stepping.
            while((envSize <= vm.FERgetSize()) &&
                    (currentLine == vm.FERgetCurrentLine()) &&
                    (vm.isBreakpoint(vm.FERgetCurrentLine()))) {
                vm.exProgram();
                vm.stepState();
            }

            //If we did not stop because we stepped out on the breakpoint line, we must continue until we have stepped out!
            if (!vm.isBreakpoint(vm.FERgetCurrentLine())) {
                while ((envSize <= vm.FERgetSize()) && !vm.isBreakpoint(vm.FERgetCurrentLine())) {
                    vm.exProgram();
                    vm.stepState();
                }
            }
        }

        //Turn stepping off.
        vm.stepState();
    }

}
