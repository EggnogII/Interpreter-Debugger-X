/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;
import interpreter.VirtualMachine;
import interpreter.RunTimeStack;
import interpreter.debugger.DProgram;
import interpreter.debugger.FuncEnvironRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * In charge of running the program, interacting with all of the interpreter
 * @author Imran
 */
public class DVirtualMachine extends VirtualMachine{
    private Vector<String> sourceFile = new Vector(); //Holds the source file 
    private DProgram dprogram; //bytecode program
    private static Stack<FuncEnvironRecord> FEStack = new Stack();
    // maintain the stack of Function Environment Record
    
    //breakpoints
    private Vector<Integer> breakpoints = new Vector();
    private Vector<Integer> validBreaks = new Vector();
    private int prevLine = -99; //set to current line after it has been stopped at that point
    private boolean beenThere = false;
    private boolean isStepCommand = false; //step command flag
    private boolean tracing = false; //function tracing flag
    private String funcTrace = "";
    private int numArgs = 0; //holds the number of param/args before function is called
    
    //constructor
    public DVirtualMachine(DProgram P){
        dprogram = P;
        resetControl();
    }
    
    //Runs the Program
    @Override
    public void exProgram(){
        
        //check if program is running and if there is a step instruction
        
        while (isRun || isStepCommand){
            
            dprogram.getCode(programCounter).execute(this);
            //check if its dumping
            if (isDump){
                //check if you don't have a dumpcode
                if (!(dprogram.getCode(programCounter).getClass().toString().equals("class interpreter.bytecodes.DumpCode"))){
                    System.out.println(dprogram.getCode(programCounter) + "/t/t" /*+  FERPRINTALL FUNCTION */);
                    runStack.dump();; //dump the RTS 
                }
            }
            programCounter++; //iterate
            if(isRun){
                //check for a breakpoint
                isRun = !(isBreakpoint(FERgetCurrentLine()) && !stoppedHere());
                if (FERgetCurrentLine() > 0){
                    prevLine =  FERgetCurrentLine();
                }
                //check if the current line is greater than 0
                //if it is make the previous line the current line
            }
            if (isStepCommand){
                isStepCommand = false;
                isRun = false;
            }
        }
    }
    
    //VM Run methods
    
    //reset control
    public void resetControl(){
        refresh();
        isRun = true;
    }
    
    // further resets the DVM program control
    public void refresh(){
        programCounter = 0;
        runStack = new RunTimeStack();
        isRun = false;
        isDump = false;
        FEStack = new Stack();
        returnAddress = new Stack();
        pushFER(); //put first Function Environment Record onto FEStack
    }
    
    //return program counter
    public int getPC(){
        return programCounter;
    }
    
    //turns source file into String Vector, makes it easy to process
    public Vector<String> processInputFile(String sourceFileName) throws Exception {
        BufferedReader file = new BufferedReader(new FileReader(sourceFileName));
        String line = file.readLine();
        sourceFile.add(" "); //needed so that index will correspond with line number
        
        while (line != null){
            sourceFile.add(line);
            line = file.readLine();
        }
        file.close();
        determineInvalidBreakPTS(sourceFileName + ".cod");
        return sourceFile;
    }
    
    //return the length in lines of the source file
    public int sourceFileLength(){
        return sourceFile.size();
    }
    
    //FER Stack Methods
   
    //set the FER function name to the submitted string
    public void FERsetSubmitFunctionName(String  name){
        FEStack.peek().setFuncName(name);
    }
    
    //set the FER function start line to the submitted int
    public void FERsetSubmitStartLine(int lineNum){
        FEStack.peek().setStartLine(lineNum);
    }
    
    //set the FER function endline to the submitted int
    public void FERsetSubmitEndLine(int lineNum){
        FEStack.peek().setEndLine(lineNum);
    }
    
    //set the FER function current to the submitted int
    public void FERsetSubmitCurrentLine(int lineNum){
        FEStack.peek().setCurrentLine(lineNum);
    }
    
    //get the int representing the current line
    public int FERgetCurrentLine(){
        return FEStack.peek().getCurrentLine();
    }
    
    public int FERgetCurrentLine(int i){
        return FEStack.get(i).getCurrentLine();
    }
    
    //get the int representing the start line
    public int FERgetStartLine(){
        return FEStack.peek().getCurrentLine();
    }
    
    public int FERgetStartLine(int i){
        return FEStack.get(i).getStartLine();
    }
    
    //get the int representing the end line
    public int FERgetEndLine(){
        return FEStack.peek().getEndLine();
    }
    
    //get the name of the function on top of the stack
    public String FERgetFunctionName(){
        return FEStack.peek().getFuncName();
    }
    //same as above for specified index in the stack
    public String FERgetFunctionName(int i){
        return FEStack.get(i).getFuncName();
    }
    
    //return the Vector for the variable at the top of the stack
    public Vector FERgetVar(){
        return FEStack.peek().getSymVar();
    }
    
    //same as above for specified index in stack
    public Vector FERgetVar(int i){
        return FEStack.get(i).getSymVar();
    }
    
    //push a new FER onto the FEStack
    public void pushFER(){
        FEStack.push(new FuncEnvironRecord());
    }
    
    //pop function
    public void popFER(){
        FEStack.pop();
    }
    
    //get size of the FES
    public int FERgetSize(){
        return FEStack.size();
    }
    
    //add literal
    public void FERaddLit(String name,  int val){
        FEStack.peek().addLit(name, val);
    }
    
    //pop literal by a number n
    public void FERpopLit(int n){
        FEStack.peek().popLit(n);
    }
    
    //create a String of all items in FEStack
    public String FERprint(){
        String out = "";
        for (int i = 0;i<FEStack.size();i++){
            out += FEStack.get(i);
        }
        return out;
    }
    
    //Breakpoint methods
    //set breakpoint
    public void setBreakPoint(int b){
        breakpoints.add(b);
    }
    
    //remove breakpoint
    public void removeBreakPoint(int b){
        if (breakpoints.contains(b)){
            breakpoints.remove(breakpoints.indexOf(b));
        }
    } 
    
    //returns a vector containing all breakpoints
    public Vector<Integer> getBreakPoints(){
        return breakpoints;
    }
    
    //return true if the line in the arg is a breakpoint
    public boolean isBreakpoint(int line){
        return breakpoints.contains(line);
    }
    
    //determine invalid breakpoints
    private void determineInvalidBreakPTS(String filename) throws Exception {
        Vector<String> BFile = new Vector();
        BufferedReader file = new BufferedReader(new FileReader(filename));
        String line = file.readLine();
        while (line != null){
            BFile.add(line);
            line = file.readLine();
        }
        file.close();
        
        int linenum = 0;
        StringTokenizer lineTok = null;
        for (int i=0;i<BFile.size();i++){
            lineTok = new StringTokenizer(BFile.get(i));
            line = lineTok.nextToken();
            if(line.equals("LINE")){
                linenum = Integer.parseInt(lineTok.nextToken());
                if((linenum > 0) && (linenum < sourceFile.size()) && !(validBreaks.contains(linenum))){
                    validBreaks.add(linenum);
                }
            }   
        }  
    }
   
    //returns true if given int has not been determined to be invalid breakpoint
    public boolean isValidBreakPT(int bp){
        return (validBreaks.contains(bp) && bp > 0 &&  bp < sourceFile.size());
        //return (validBreaks.contains(bp) && bp > 0 && bp < sourceFile.size()); //maybe here
    }
    
    //Stopped here SHOULD BE BOOLEAN, but for some reason not compatible
    private boolean stoppedHere(){
        return (prevLine == FERgetCurrentLine());
    }
    
    //Function Trace Methods
    //set tracing state according to the parameter
    public void setTracing(boolean isTrace){
        tracing = isTrace;
    }
    
    //return state of tracing
    public boolean isTrace(){
        return tracing;
    }
    
    //return a string representing the function trace
    public String functionTrace(){
        String trace = funcTrace;
        return trace;
    }
    
    public void clearFunctionTrace(){
        funcTrace = "";
    }
    
    //adds the given String to the function trace
    public void functionTrace(String trace){
        for(int i=0;i<FERgetSize();i++){
            funcTrace += " ";
        }
        funcTrace += trace;
    }
    
    //sets and holds onto the number of args in the function to follow
    public void setNumArgs(int num){
        numArgs = num;
    }
    
    //returns the number of args for the function to follow
    public int getNumArgs(){
        return numArgs;
    }
    
    public int runStackSeeItemAt(int i){
        return runStack.seeItemAt(i);
    }
    
    //stepping functions
    //toggles step on/off
    public void stepState(){
       isStepCommand = !isStepCommand;
    }
    
    //return the state of the step 
    public boolean isStep(){
        return isStepCommand;
    }
    
    //Return a string representation of the class of the next ByteCode
    public String peekNextByteCode(){
        return(dprogram.getCode(programCounter).getClass().toString());
    }
    
    //Increments the PC w/o running execute
    public void incrementPC(){
        programCounter++;
    }
    
    //Pop the top frame pointer off the frame pointer stack
    public void runStackPopFramePointer(){
        runStack.popFramePointer();
    }
    
}