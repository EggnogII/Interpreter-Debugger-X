package interpreter.debugger.tools;

import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 * Steps out of the current function's activation.
 * (i.e. Returns to its caller.)
 *
 * @author Imran Irfan
 */
public class StepOut extends Tool {

    @Override
    public void init(int arg) {}

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(DVirtualMachine vm) {
        int envSize = vm.FERgetSize();
        int currentLine = vm.FERgetCurrentLine();

        vm.stepState();
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
        vm.stepState();
    }


}
