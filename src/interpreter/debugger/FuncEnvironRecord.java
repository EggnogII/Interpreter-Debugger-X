/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
/**
 * Function Environment Record
 * records all named literals, function name, start, end, and current line
 * 
 * @author Imran
 */
public class FuncEnvironRecord {
    
    private SymbolTable sT = new SymbolTable();
    private int current, start, end; //startline, endline, and currentline
    private String funcName = null;
    private String printS = ("<(");
    
    //default Constructor
    public FuncEnvironRecord(){}
    
    //another constructor
    public FuncEnvironRecord(int s, int e){
        start = s;
        end = e;
        current = s;
    }
    
    //setFuncName
    public void setFuncName(String f){
        funcName = f;
    }
    
    //setStartLine
    public void setStartLine(int s){
        start = s;
    }
    
    //setEndLine
    public void setEndLine(int e){
        end = e;
    }
    
    //setCurrentLine
    public void setCurrentLine(int c){
        current = c;
    }
    
    //getFuncName
    public String getFuncName(){
        return funcName;
    }
    //getStartLine
    public int getStartLine(){
        return start;
    }
    //getEndLine
    public int getEndLine(){
        return end;
    }
    //getCurrentLine
    public int getCurrentLine(){
        return current;
    }
    
    //getSymbolVar get the variables from the symbol table
    public Vector getSymVar(){
        return sT.getVar();
    }
    
    //addLit
    //Add a literal to the symbol table
    public void addLit(String litName, int litVal){
        sT.put(litName, litVal);
    }
    //popLit
    //pop the given number of literals
    public void popLit(int n){
        sT.pop(n);
    }
    //overided toString()
    //return a string that represents this function environment record
    @Override
    public String toString(){
        printS += sT.toString();
        if (funcName != null){
            printS += (")," + getFuncName() + ", " + getStartLine() + ", " + getEndLine() + ", " + getCurrentLine());
        }
        else{
            printS += "),-,-,-,-";
        }
        return printS;
    }
      
    
    //castToInt
    //cast an object to int
    private static int castToInt(Object s){
        return Integer.parseInt((String)s);
    }
  /*  
    public static void main(String[] args) throws FileNotFoundException, IOException {
        boolean isRun = true;
        BufferedReader inputFile = new BufferedReader(new FileReader(args[0]));
        String line = inputFile.readLine(), com;
        String p = "";
        StringTokenizer lineTokenizer;
        Vector lineA = new Vector(); //vector for line arguments
        FuncEnvironRecord fctEnvRecord = new FuncEnvironRecord(0, 1);
        
        while (line != null && isRun){
            line = inputFile.readLine();
            System.out.print(line + "\t\t");
            lineTokenizer = new StringTokenizer(line);
            while (lineTokenizer.hasMoreTokens()){
                lineA.add(lineTokenizer.nextToken());
            }
            com = (String)lineA.get(0);
            if (com.equals("BS")){
                fctEnvRecord.sT.beginScope();
            }
            else if (com.equals("FUNCTION")){
                fctEnvRecord.setFuncName((String)lineA.get(1));
                fctEnvRecord.setStartLine(castToInt(lineA.get(2)));
                fctEnvRecord.setEndLine(castToInt(lineA.get(3)));
            }
            else if (com.equals("LINE")){
                fctEnvRecord.setCurrentLine(castToInt(lineA.get(1)));
            }
            else if (com.equals("LIT") || com.equals("Enter") && (lineA.size() > 2)){
                fctEnvRecord.sT.put((String)lineA.get(1), castToInt(lineA.get(2)));
            }
            else if (com.equals("POP")){
                fctEnvRecord.sT.pop(castToInt(lineA.get(1)));
            }
            else if (com.equals("HALT")){
                isRun = false;
            }
            p += fctEnvRecord.sT.toString();
            if (fctEnvRecord.getFuncName() != null){
                p += (")," + fctEnvRecord.getFuncName() + ", " + fctEnvRecord.getStartLine() + ", " + fctEnvRecord.getCurrentLine());
            }
            else{
                p += "),-,-,-,-";
            }
            System.out.println(p);
            lineA.removeAllElements();
            
        }
        inputFile.close();
        
    }
*/
}
