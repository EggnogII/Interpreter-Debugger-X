/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;
import interpreter.Interpreter;
import interpreter.debugger.ui.UI;


/**
 *
 * @author Imran Irfan
 */
public class Debugger{ //used to extend interpreter
    protected DebugBCL bcl;
    protected static DVirtualMachine vM;
    protected String filename;
    
    //constructor
    public Debugger(){}
    
    //constructor. Takes the file at the location given an begins
    //processing and the Debug ByteCodes therein, then initiates the Debug
    //Virtual Machine
    
    public Debugger(String name){
        filename = name + ".x";
        DebugCodeTable.init();
        bcl = new DebugBCL((name + ".x.cod"));
        try{
           
            DProgram dp = bcl.loadCodes(); 
            // debug printout System.out.println("LOADCODES SUCCESSFUL"); //skips
            vM = new DVirtualMachine(dp); 
        } catch (Exception e){}
    }
    
    //runs the ui
    public void run(String file) throws Exception {
        (new UI(file)).run();
    }
    
}
