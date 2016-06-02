/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;
import interpreter.CodeTable;

/**
 * Debugger implementation of Code Table
 * @author Imran Irfan
 */
public class DebugCodeTable extends CodeTable {
    public static void init() {
        ByteCodes.put("ARGS", "interpreter.debugger.debugbytecodes.DebugArgCode");
        ByteCodes.put("BOP", "interpreter.debugger.debugbytecodes.DebugBopCode");
        ByteCodes.put("CALL", "interpreter.debugger.debugbytecodes.DebugCallCode");
        ByteCodes.put("DUMP", "interpreter.bytecodes.DumpCode");
        ByteCodes.put("FALSEBRANCH", "interpreter.bytecodes.branchingbytecodes.FalsebranchCode");
        ByteCodes.put("FORMAL", "interpreter.debugger.debugbytecodes.DebugFormalCode");
        ByteCodes.put("FUNCTION", "interpreter.debugger.debugbytecodes.DebugFunctionCode");
        ByteCodes.put("GOTO", "interpreter.bytecodes.branchingbytecodes.GotoCode");
        ByteCodes.put("HALT", "interpreter.debugger.debugbytecodes.DebugHaltCode");
        ByteCodes.put("LABEL", "interpreter.bytecodes.LabelCode");
        ByteCodes.put("LINE", "interpreter.debugger.debugbytecodes.DebugLineCode");
        ByteCodes.put("LIT", "interpreter.debugger.debugbytecodes.DebugLitCode");
        ByteCodes.put("LOAD", "interpreter.debugger.debugbytecodes.DebugLoadCode");
        ByteCodes.put("POP", "interpreter.debugger.debugbytecodes.DebugPopCode");
        ByteCodes.put("READ", "interpreter.debugger.debugbytecodes.DebugReadCode");
        ByteCodes.put("RETURN", "interpreter.debugger.debugbytecodes.DebugReturnCode");
        ByteCodes.put("STORE", "interpreter.debugger.debugbytecodes.DebugStoreCode");
        ByteCodes.put("WRITE", "interpreter.debugger.debugbytecodes.DebugWriteCode");
    }
    
}
