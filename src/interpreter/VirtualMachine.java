package interpreter;
import interpreter.bytecodes.ByteCode;
import java.util.*;
/*
    Runs program and interfaces with Interpreter
*/
public class VirtualMachine {

    protected RunTimeStack runStack;
    protected int programCounter;         
    protected Stack returnAddress;      //push/pop when call/return functions
    protected boolean isRun;      //flag to check if the VM is running
    protected boolean isDump;      //flag to check whether or not dumping is toggled
    private Program program;        //bytecode program
    protected Stack currentFunctionName = new Stack();
    
    //constructor
    public VirtualMachine() {}

    //begins and loads a program
    VirtualMachine(Program P) {
        program = P;
    }

    //run loaded program
    public void exProgram() {
        programCounter = 0;
        runStack = new RunTimeStack();
        returnAddress = new Stack();
        isRun = true;
        isDump = false;
        while (isRun) {
            ByteCode B = program.getCode(programCounter);
            B.execute(this);
            if (isDump) {
                if (!(B.getClass().toString().equals("class interpreter.bytecodes.DumpCode")) &&
                        !(B.getClass().toString().equals("class interpreter.bytecodes.DummyCode"))) {
                    System.out.println(B);
                    runStack.dump();
                }
            }
            programCounter++;
        }
    }
    
    //move current location in program to desired location, saves the old location
    // returns the args for the called functtion
    public Vector callCounterPush(int location, String functionName) {
        Vector currentFrameContents = runStack.getCurrentFrame();
        returnAddress.push(programCounter);
        setProgCounter(location);
        currentFunctionName.push(functionName);
        return currentFrameContents;
    }

    //return from the called location to where the program was before
    public void returnCounterPop() {
        if (!returnAddress.empty()) {
            setProgCounter((Integer)returnAddress.pop());
            runStack.popFrame();
        }
    }

    //return the current function name and deletes it from the list of 
    //called functions
    public String retrieveCurrentFunctionName() {
        if (!currentFunctionName.empty()) {
            return (String)currentFunctionName.pop();
        } else {
            return ("");
        }
    }
    
    //change the active location of the program to this line
    public void setProgCounter(int loc) {
        programCounter = loc; //GETS SET TO OUT OF BOUNDS FROM GOTO CODE and FROM 
    }
    
    //set the VM flag
    public void setRun(boolean runSet) {
        isRun = runSet;
    }

    //set the dump toggle
    public void setDump(boolean dumpSet) {
        isDump = dumpSet;
    }

    //remove and return the top item from the RTS
    public int popRTS() {
        return runStack.pop();
    }

    //push the given param onto the top of the RTS
    public int pushRTS(int arg) {
        return runStack.push(arg);
    }

    //returns the top item from the RTS, via peek
    public int peekRTS() {
        if (!runStack.empty()) {
            return runStack.peek();
        } else {
            return -1;
        }
    }
    
    //start a new frame at specified location, allocating all items on the RTS
    //beyond that point into new frame
    public void runStackNewFrameAt(int i) {
        runStack.newFrameAt(i);
    }

    //load variable from anywhere in current frame
    public int runStackLoad(int i) {
        return runStack.load(i);
    }

    //store the top item of the stack into this location in the current frame
    public int runStackStore(int i) {
        return runStack.store(i);
    }

}