/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import interpreter.ByteCodeLoader;
import interpreter.bytecodes.ByteCode;
import interpreter.debugger.DProgram;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Debugger Implementation of ByteCodeLoader
 * @author Imran Irfan
 */
public class DebugBCL extends ByteCodeLoader{
   
    public DebugBCL(String file){
        progFile = file;
    }
    
    @Override
    public DProgram loadCodes() throws FileNotFoundException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException{
        BufferedReader file = new BufferedReader(new FileReader(progFile));
        String line = file.readLine();
        String codeClass = "";
        String code = "";
        DProgram dprogram = new DProgram();
        ByteCode B = null; //holds the generic DebugByteCode object
        Vector args = new Vector(); //holds arg for each debug bytecode
        
        StringTokenizer lineTok = new StringTokenizer(line);
        
        while((line != null) && (lineTok.hasMoreTokens())){
            code = lineTok.nextToken();
            while(lineTok.hasMoreTokens()){
                args.add(lineTok.nextToken());
            }
            
            codeClass = DebugCodeTable.get(code);
            B = (ByteCode)(Class.forName(codeClass).newInstance());
            B.init(args);
            dprogram.addByteCode(B); // debugger byte code into debug program
            line = file.readLine(); //get next line
            if (line != null){
                lineTok = new StringTokenizer(line);
            }
            args.removeAllElements(); //empty arg vector
        }
        file.close();
        dprogram.resolveAddress();
        return dprogram;
    }
    
    
    
}
