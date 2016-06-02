/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;
import interpreter.debugger.Debugger;
import java.io.*;

/**
 *  Takes care of running VM
 * @author Imran
 */
public class Interpreter {
    ByteCodeLoader bcl;
    
    public Interpreter(){}
    
    public Interpreter(String codeFile) {
		try {
			CodeTable.init();
			bcl = new ByteCodeLoader(codeFile);
		} catch (IOException e) {
			System.out.println("**** " + e);
		}
    }
    
    void run() throws FileNotFoundException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException {
		Program program = bcl.loadCodes();
		VirtualMachine vm = new VirtualMachine(program);
		vm.exProgram();
    }
    
    public static void main(String args[]) throws Exception {
        if (args.length == 0) {
            System.out.println("***Incorrect usage, try: java Interpreter.Interpreter <file>");
            System.exit(1);
		}
        if (args[0].equals("-d")){
            if (args.length < 2){
                System.out.println("***Incorrect usage, try: java Interpreter.Interpreter -d <filename>");
                System.exit(1);
            }
            else{

                (new Debugger()).run(args[1]);
            }
        }
        else {
            (new Interpreter(args[0])).run();
        }
    }
    
    
    
}
