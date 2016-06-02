package interpreter.debugger.tools;

import interpreter.debugger.DVirtualMachine;
import java.util.Vector;

/**
 *
 * @author Imran Irfan
 */
public class SetFunctionTracing extends Tool {

    protected boolean tState; //If true, the program trace will print out. If false, it won't.

    @Override
    public void init(int arg) {}

    //changes tracing state
    @Override
    public void init(Vector args) {
        String state = (String)args.get(0);
        if (state.equalsIgnoreCase("ON")) {
            tState = true;
        } else {
            tState = false;
        }
    }

    @Override
    public void execute(DVirtualMachine vm) {
        vm.setTracing(tState);
    }
    

}
