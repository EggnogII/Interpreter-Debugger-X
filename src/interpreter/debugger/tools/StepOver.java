package interpreter.debugger.tools;

import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 *
 * @author Imran Irfan
 */
public class StepOver extends Tool {

    @Override
    public void init(int arg) {}

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(DVirtualMachine vm) {
        int currentLine = vm.FERgetCurrentLine();
        int envSize = vm.FERgetSize();

        //Turn stepping on
        vm.stepState();

        while (((envSize <= vm.FERgetSize()) && (currentLine == vm.FERgetCurrentLine())) || (vm.FERgetCurrentLine() == 0)) {
            vm.exProgram();
            vm.stepState();
        }


        //Turn stepping off
        vm.stepState();
    }

}
