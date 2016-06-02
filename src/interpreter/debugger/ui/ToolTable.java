/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui;
import java.util.Hashtable;

/**
 *holds and initializes the ability to translate codes to create instances
 * 
 * @author Imran Irfan
 */
public class ToolTable {
   public static Hashtable<String, String> vmTools = new Hashtable();
   public static Hashtable<String, String> uiTools = new Hashtable();
   
     public ToolTable() {
        //HELP
        uiTools.put("HELP","UIHelp");
        uiTools.put("?","UIHelp");

        //EXIT
        uiTools.put("EXIT", "UIExit");
        uiTools.put("X", "UIExit");

        //SET FUNCTION TRACING
        vmTools.put("SET TRACING","UISetFunctionTrace");
        vmTools.put("T","UISetFunctionTrace");
        
        //PRINT CALL STACK
        uiTools.put("PRINT CALL STACK","UIPrintCallStack");
        uiTools.put("P","UIPrintCallStack");

        //DISPLAY LOCAL VARIABLES
        uiTools.put("DISPLAY LOCAL VARIABLES","UIDisplayVars");
        uiTools.put("D","UIDisplayVars");

        //DISPLAY FUNCTION
        uiTools.put("DISPLAY FUNCTION","UIDisplayFunction");
        uiTools.put("F","UIDisplayFunction");

        //DISPLAY FUNCTION
        uiTools.put("DISPLAY SOURCE CODE","UIDisplaySource");
        uiTools.put("S","UIDisplaySource");

        //LIST BREAKPOINTS
        uiTools.put("LIST BREAKPOINTS","UIListBreakPoints");
        uiTools.put("L","UIListBreakPoints");

        //SET BREAKPOINT
        vmTools.put("SET BREAKPOINT","UISetBreakPoint");
        vmTools.put("B","UISetBreakPoint");

        //CLEAR BREAKPOINT
        vmTools.put("CLEAR BREAKPOINT","UIClearBreakPoints");
        vmTools.put("R","UIClearBreakPoints");

        //CONTINUE EXECUTION
        vmTools.put("CONTINE EXECUTION","UIContinueExecution");
        vmTools.put("GO","UIContinueExecution");
        vmTools.put("C","UIContinueExecution");

        //QUIT EXECUTION
        vmTools.put("HALT EXECUTION","UIHaltExecution");
        vmTools.put("H","UIHaltExecution");

        //STEP OVER
        vmTools.put("STEP OVER","UIStepOver");
        vmTools.put("V","UIStepOver");

        //STEP INTO
        vmTools.put("STEP INTO","UIStepIn");
        vmTools.put("I","UIStepIn");

        //STEP OUT
        vmTools.put("STEP OUT","UIStepOut");
        vmTools.put("O","UIStepOut");

        //The user will not be informed about these commands.

        //DISPLAY GLOBAL VARIABLES
        uiTools.put("DISPLAY VARIABLES","UIDisplayGlobalVars");
        uiTools.put("DV","UIDisplayGlobalVars");

        //SET DUMPING
        uiTools.put("DUMPING","UIDumping");
        uiTools.put("DUMP","UIDumping");

    }
     
      /**
     * Returns the translation of the Command.
     * @param code A String representing the Command to be processed.
     * @return A String representing the name of the class corresponding to the Command.
     */
    public String get(String code) {
        if (isVMTool(code)) {
            return vmTools.get(code);
        } else if (isUITool(code)) {
            return uiTools.get(code);
        } else {
            return null;
        }
    }

    /**
     * Returns true if code is found in the UICommand Hashtable.
     * @param code string to search the Hashtable for
     * @return true if code is found in the UICommand Hashtable
     */
    public boolean isUITool (String code) {
        return (uiTools.containsKey(code));
    }

    /**
     * Returns true if code is found in the VMCommand Hashtable.
     * @param code string to search the Hashtable for
     * @return true if code is found in the VMCommand Hashtable
     */
    public boolean isVMTool (String code) {
        return (vmTools.containsKey(code));
    }
   
}
