package interpreter.bytecodes;
import interpreter.VirtualMachine;
import java.util.Hashtable;
import java.util.Vector;


/** binary operator bytecodes
 *  basically pops two levels of the stack and carries out ops
 *  ops include + - / * == !=, less than or equal, greater than or equal,
 *  less than, greater than, and the logical ops | and &
 * @author Imran
 */

public class BopCode extends ByteCode {

    private String operand; //Holds the operand to be used.
    private Exception ArithmeticException;

  //constructor
    public BopCode() {}

    public void init(Vector args) {
        operand = (String)args.get(0);
    }
    //carries out the operation previously requested
    @Override
    public void execute(VirtualMachine virtualMachine) {
        int second = virtualMachine.popRTS();
        long result = execute(virtualMachine.popRTS(), second);
        try{
            if ((result < -2147483648) || (result > 2147483647)) {
                throw ArithmeticException;
            } else {
                virtualMachine.pushRTS((int)result);
            }
        } catch (Exception e) {
            System.out.println("Error, result is outside of the int type's bounds.");
            System.exit(1);
        }
    }

    protected long execute(int first, int second) {
         Hashtable<String, Long> operHash = new Hashtable();
        operHash.put("+", addOp(first, second));
        operHash.put("-", subOp(first, second));
        operHash.put("*", multOp(first, second));
        operHash.put("/", divOp(first, second));
        operHash.put("==", equalOp(first, second));
        operHash.put("!=", notEqualOp(first, second));
        operHash.put("<=", lessThanOrEqualOp(first, second));
        operHash.put("<", lessThanOp(first, second));
        operHash.put(">=", greaterThanOrEqual(first, second));
        operHash.put(">", greaterThanOp(first, second));
        operHash.put("&", andOp(first, second));
        operHash.put("|", orOp(first, second));
        return operHash.get(operand).longValue();
    }

    public String toString() {
        return ("BOP " + operand);
    }

    private long addOp(int first, int second) {
        return (long)first + (long)second;
    }

    private long subOp(int first, int second) {
        return (long)first - (long)second;
    }

    private long multOp(int first, int second) {
        return (long)first * (long)second;
    }

    private long divOp(int first, int second) {
        return (long)first / (long)second;
    }
    
    private long equalOp(int first, int second) {
        if (first == second) {
            return 1;
        } else {
            return 0;
        }
    }

    private long notEqualOp(int first, int second) {
        if (first != second) {
            return 1;
        } else {
            return 0;
        }
    }

    private long lessThanOrEqualOp(int first, int second) {
        if (first <= second) {
            return 1;
        } else {
            return 0;
        }
    }

    private long lessThanOp(int first, int second) {
        if (first < second) {
            return 1;
        } else {
            return 0;
        }
    }

    private long greaterThanOrEqual(int first, int second) {
        if (first >= second) {
            return 1;
        } else {
            return 0;
        }
    }

    private long greaterThanOp(int first, int second) {
        if (first > second) {
            return 1;
        } else {
            return 0;
        }
    }

    private long andOp(int first, int second) {
        return (first & second);
    }

    private long orOp(int first, int second) {
        return (first | second);
    }
}