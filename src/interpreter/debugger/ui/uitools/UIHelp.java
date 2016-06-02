/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui.uitools;
import interpreter.debugger.ui.UI;
import java.util.Vector;

/**
 * gives the user a list of commands which the debugger can
 * execute and how to use these commands
 * @author Imran
 */
public class UIHelp extends UITool{
    
    public UIHelp(){}

    @Override
    public void execute(UI ui){
        System.out.println("This is a list of things which this debugger has and the commands to execute them");
        System.out.println("The format to enter commands is this: ");
        System.out.println("COMMAND ARGUMENT [ARGUMENT]\n" + "Please note this is NOT case-sensitive");
        System.out.println("Command:                    Description:                                Example:");
        System.out.println("#####################################################################################");
        System.out.println("HELP, ?                     Help                                       None");
        System.out.println("EXIT, X                     Exit                                       None");
        System.out.println("T                           Set Function Tracing                       T ON, t OFF");
        System.out.println("B                           Set Break Points                           B 1, B 2 3");
        System.out.println("R                           Clear Breakpoints                          R 1, R 1 2, R ALL");
        System.out.println("P                           Print Call Stack                           None");
        System.out.println("L                           List Breakpoints                           None");
        System.out.println("GO, C                       Continue Execution                         None");
        System.out.println("H                           Halt Execution                             None");
        System.out.println("D                           Display Local Variables                    None");
        System.out.println("S                           Display Source Code                        None");
        System.out.println("F                           Display Function                           None");
        System.out.println("O                           Step Out                                   None");
        System.out.println("V                           Step Over                                  None");
        System.out.println("I                           Step In                                    None");
       //System.out.println("DUMPING, DUMP               Set Dump State                           ON, OFF");
        
        
    }
    
    @Override
    public void init(Vector args){}
    
}
