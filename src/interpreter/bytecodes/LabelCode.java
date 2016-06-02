package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Vector;

//label: branch target

public class LabelCode extends ByteCode {

    private int labelLocation; 
    private String name; 

    //constructer
    public LabelCode() {}

    public void init(Vector args) {
        name = (String)args.get(0);
    }
    
    //return line number of label location
    public int getLocation() {
        return labelLocation;
    }

    //sets label line num for start of program
    public void setLocation(int loc) {
        labelLocation = loc;
    }

    //return label name
    public String getLabelName() {
        return name;
    }

    //return label name in a nicer way
    public String getCleanLabelName() {
        String[] editedLabelName = name.split("<");
        return editedLabelName[0];
    }

    //NOTHING
    @Override
    public void execute(VirtualMachine virtualMachine) {}

    public String toString() {
        return ("LABEL " + getLabelName() + " @ " + getLocation());
    }
}
