package interpreter;
import java.util.Hashtable;

//holds the ability to translate codes to create class instances
public class CodeTable {

    protected static Hashtable<String, String> ByteCodes = new Hashtable(); //Holds the commands and their translations.

    //constructor
    public CodeTable() {}

    //return translation of bytecode command
    public static String get(String code) {
        return ByteCodes.get(code);
    }

    public static boolean contains(String code) {
        return ByteCodes.containsKey(code);
    }

    //fill in all the values for bytecodes
    public static void init() {
        ByteCodes.put("ARGS", "ArgsCode");
        ByteCodes.put("BOP", "BopCode");
        ByteCodes.put("CALL", "branchingbytecodes.CallCode");
        ByteCodes.put("DUMP", "DumpCode");
        ByteCodes.put("FALSEBRANCH", "branchingbytecodes.FalsebranchCode");
        ByteCodes.put("GOTO", "branchingbytecodes.GotoCode");
        ByteCodes.put("HALT", "HaltCode");
        ByteCodes.put("LABEL", "LabelCode");
        ByteCodes.put("LIT", "LitCode");
        ByteCodes.put("LOAD", "LoadCode");
        ByteCodes.put("POP", "PopCode");
        ByteCodes.put("READ", "ReadCode");
        ByteCodes.put("RETURN", "ReturnCode");
        ByteCodes.put("STORE", "StoreCode");
        ByteCodes.put("WRITE", "WriteCode");
    }

}