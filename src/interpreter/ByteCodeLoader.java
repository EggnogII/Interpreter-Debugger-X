package interpreter;
import interpreter.bytecodes.DummyCode;
import interpreter.bytecodes.ByteCode;
import java.io.*;
import java.io.IOException;
import java.util.*;
/*
    Loads the Bytecodes into the Virtual Machine
    Imran Irfan
*/
public class ByteCodeLoader {

    protected String progFile;
    
    //constructor
    public ByteCodeLoader() {
        progFile = "";
    }
    //constructor
    //saves name of file to be processed
    public ByteCodeLoader(String filename) throws IOException {
         progFile = filename;
    }

    //load all codes from file, resolve branch addresses from symbols to
    //address in code memory
    public Program loadCodes() throws FileNotFoundException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException {
        BufferedReader file = new BufferedReader(new FileReader(progFile));
        String line = file.readLine(), codeClass, code;
        Program p = new Program();
        ByteCode b = null;           //holds the bytecode obj
        Vector args = new Vector();         //holds args for bytecodes

        StringTokenizer lineTokenizer = new StringTokenizer(line);

        while ((line != null) && (lineTokenizer.hasMoreTokens())) {
            code = lineTokenizer.nextToken();

            if (CodeTable.contains(code)) {
                while (lineTokenizer.hasMoreTokens()) {
                    args.add(lineTokenizer.nextToken());
                }
                codeClass = CodeTable.get(code);
                b = (ByteCode)(Class.forName("interpreter.bytecodes."+codeClass).newInstance());
                b.init(args);
                p.addByteCode(b);  //put bytecodes into program
            } else {
                p.addByteCode(new DummyCode());
            }

            line = file.readLine();         
            if (line != null) {
                lineTokenizer = new StringTokenizer(line);
            }
            args.removeAllElements();       //empty argument vector
        }
        file.close();                       
        p.resolveAddress();           //call from program to resolve addresses
        return p;
    }

}
