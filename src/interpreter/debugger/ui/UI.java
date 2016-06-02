/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui;


import interpreter.debugger.Debugger;
import interpreter.debugger.DVirtualMachine;
import interpreter.debugger.tools.Tool;
import interpreter.debugger.ui.uitools.UITool;
import java.io.*;
import java.util.*;

/**
 * Debugger UI
 * @author Imran Irfan
 */
public class UI extends Debugger{ 
    private Vector<String> source = new Vector();
    private boolean isRun = true;
    
    //constructor
    //Points to debugger constructor
    public UI(String file){
        super(file);
    }
    
    //executes the program
    //almost like VM
    public void run() throws Exception{
        BufferedReader br;
        String inputC, command;
        StringTokenizer lineTok;
        ToolTable toolTable = new ToolTable();
        Vector args = new Vector();
        UITool uiToolCode;
        Tool toolCode;
        
        source = vM.processInputFile(filename);
        
        System.out.println("Enter HELP or command");
        //printInputFile(1, (vM.sourceFileLength() -1 ));
        while(isRun){
            if(vM.isTrace()){
                if (!vM.functionTrace().equals("")){
                    System.out.println(vM.functionTrace());
                    vM.clearFunctionTrace();
                }
            }
        
            if (getVM().FERgetSize() == 1 && getVM().FERgetFunctionName() == null){
                printInputFile(1, getVM().sourceFileLength()-1);
            }
            else{
                printInputFile(getVM().FERgetStartLine(), getVM().FERgetEndLine()-1);
            }
        
            System.out.print(">>");
            br = new BufferedReader(new InputStreamReader(System.in));
            inputC = br.readLine();
            lineTok = new StringTokenizer(inputC);
        
            if (!inputC.equals("")){
                command = lineTok.nextToken().trim().toUpperCase();
                while (lineTok.hasMoreTokens()){
                    args.add(lineTok.nextToken().trim().toUpperCase());
                }
            
                if (toolTable.isUITool(command)){
                    uiToolCode = (UITool)(Class.forName("interpreter.debugger.ui.uitools." + toolTable.get(command)).newInstance());
                    uiToolCode.init(args);
                    uiToolCode.execute(this);
                }
                else if (toolTable.isVMTool(command)){
                    toolCode = (Tool)(Class.forName("interpreter.debugger.ui.uitools." + toolTable.get(command)).newInstance());
                    toolCode.init(args);
                    toolCode.execute(getVM());
                }
                else{
                    System.out.println("invalid command");
                }
            
                args.removeAllElements(); //empty the arg vector
            
            }
        
            else{
                System.out.println("invalid command");
            }
        }
        
        System.out.println();
    System.exit(1);
    }
    
    //prints the source file from a specific line to another specific line
    public void printInputFile(int s, int e){
        Vector<Integer> breakpoints = vM.getBreakPoints();
        for (int i = s;i<=e;i++){
            if(vM.isBreakpoint(i)){
                System.out.print(" *");
            }
            else {
                System.out.print("   ");
            }
            if (i < 10){
                System.out.print("  " + i + ".");
            }
            else if (i < 100){
                System.out.print(" " + i + ".");
            }
            else{
                System.out.print(i + ".");
            }
            System.out.print(source.get(i));
            if (i == vM.FERgetCurrentLine() || (vM.getPC() == 0 && i == 1) || (vM.FERgetCurrentLine() == -1 && i == vM.FERgetCurrentLine() - 2) ){
                System.out.println("   <-----");
            }
            else{
                System.out.println("");
            }
        } 
    }
    
    //set the isRun flag to false
    public void stopRun(){
        isRun = false;
    }
    
    //returns the DVirtualMachine of this object
    public DVirtualMachine getVM(){
        return vM;
    }
}
    

