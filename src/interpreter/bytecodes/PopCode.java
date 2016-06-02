package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Vector;

//pop the top level/'s of runtime stack
public class PopCode extends ByteCode {

    protected int num; //items to pop

    //constructor
    public PopCode() {}

    public void init(Vector args) {
        String arg = (String)args.get(0);
        Integer integerArg = Integer.parseInt(arg);
        num = integerArg;
    }
    
    //pop the initialized num of items off the stack
    @Override
    public void execute(VirtualMachine virtualMachine) {
        for (int i = 1; i <= num; i++) {
            virtualMachine.popRTS();
        }
    }

    public String toString() {
        return ("POP " + num);
    }
}
