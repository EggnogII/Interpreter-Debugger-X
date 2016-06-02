/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;
import java.util.Vector;
import java.util.HashMap;
/**
 *
 * @author Imran
 */
class Binder {
    private Object val;
    private String prevtop; //last string in scope
    private Binder end;    //last binder for same string
    
    Binder (Object v, String p, Binder e){
        val = v;
        prevtop = p;
        end = e;
    }
    Object getValue(){
      return val;  
    }
    String getPreviousTop(){
        return prevtop;
    }
    Binder getEnd(){
        return end;
    }
}

public class SymbolTable {
    private HashMap<String, Binder> Strings = new java.util.HashMap<String, Binder>();
    private String top; //refer to last String added to current scope, think start of linked list
    
    private Binder mOs; //mark of scope, we have a stack of these, push for new scope, pop when scope closes
    
    //default constructor
    public SymbolTable() {}
    
    @Override
    public String toString(){
        String printS = "";
        Object keys[] = Strings.keySet().toArray();
        for (int i=0;i<keys.length;i++){
            printS = ("<" + keys[i] + "," + get((String)keys[i]) + ">") + printS;
        }
        return printS;
    }
    //get the object associated with the desired String in table
    public Object get(String k){
        Binder b = Strings.get(k);
        return b.getValue();
    }
    
    //begin scope: remember current state of table: push new mOs on stack
    public void beginScope(){
        mOs = new Binder(null, top, mOs);
        top = null;
    }
    
    //endscope, restore the table to the most recent beginscope that hasn't ended
    public void endScope(){
        while (top != null){
            Binder b = Strings.get(top);
            if (b.getEnd() != null){
                Strings.put(top, b.getEnd());
            }
            else
                Strings.remove(top);
            top = b.getPreviousTop();
        }
        top = mOs.getPreviousTop();
        mOs = mOs.getEnd();
    }
    
    //return a set of the table's strings
    public java.util.Set<String> keys(){
        return Strings.keySet();
    }
    //put the desired value into the table, bind to the specific String
    //Maintain the list of strings in the scope
    //add it to the list of String sin prior scope
    public void put(String k, Object val){
        if (Strings.get(k)!= null){
            Strings.remove(k);
        }
        Strings.put(k, new Binder(val, top, Strings.get(k)));
        top = k;
    }
    
    //pop
    //creates an array of keys, acquires the final key
    void pop(Integer i){
        if (!Strings.isEmpty() && i !=0){ 
            Object keys[] = Strings.keySet().toArray();
            String dump = (String)keys[0]; //this is the final key
            
            top = Strings.get(dump).getPreviousTop(); //set the top to the last top
            Strings.remove(dump);
            i--;    //traverse down
            if (i != 0){ //check if complete, if not recursively do it again
                pop(i);
            }
        }
    }
    
    //getVar: returns a vector where every object is a variable name, and has value
    public Vector getVar(){
        Vector var = new Vector();
        Object keys[] = Strings.keySet().toArray();
        
        for(int i=0;i<keys.length;i++){
            var.add(keys[i]);
            var.add(get((String) keys[i]));
        }
        return var;
    }
    
}
