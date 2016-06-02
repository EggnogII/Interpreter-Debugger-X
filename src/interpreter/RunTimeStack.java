package interpreter;
import java.util.*;

//logs and processes the stack of frames
public class RunTimeStack {

    private Stack framePointers = new Stack(), framePointersHolder, framePointersHolder2;
    private Vector runStack = new Vector();

    public RunTimeStack() {
        framePointers.add((Integer)0);
    }

    //dump the RTS
    public void dump() {
        int framePointersSize = (Integer)framePointers.size(); //Size will chance, so saving size
        boolean printComma = true;
        if (!runStack.isEmpty()) {
            framePointersHolder = new Stack();
            framePointersHolder2 = new Stack();
            for(int i = 0; i < framePointersSize; i++) { //Reversing stack
                framePointersHolder.push(framePointers.peek());
                framePointersHolder2.push(framePointers.pop());
            }
            for(int i = 0; i < framePointersSize; i++) { //Reversing stack
                framePointers.push(framePointersHolder2.pop());
            }
            System.out.print("[");
            for(int i = 0; i < runStack.size(); i++) {
                  if (!framePointersHolder.empty()) {
                       if (((Integer)framePointersHolder.peek() == i)) {
                            if (i != 0) {
                                System.out.print("] [");
                            }
                            framePointersHolder.pop();
                        }
                  }
                  if (!framePointersHolder.empty()) {
                       if (((Integer)framePointersHolder.peek() == (i + 1))) {
                            printComma = false;
                        }
                  }
                System.out.print(runStack.get(i));
                    if ((runStack.size() != 1) && ((runStack.size() - 1) != i) && printComma){
                            System.out.print(",");
                    }
                if (!printComma) {
                    printComma = true;
                }
            }
            System.out.println("]");
        }
    }

    //return top item of runStack by peeking
    public int peek() {
        Integer integerArg = (Integer)runStack.get((runStack.size() - 1));
        int item = integerArg.intValue();
        return item;
    }

    //pop the top item in the RTS, return it
    public int pop() {
        int item = peek();
        runStack.remove((runStack.size()-1));
        return item;
    }

    //pushes an int i onto the RTS, returns pushed item
    public int push(int i) {
        runStack.addElement(i);
        return i;
    }

    //starts new frame at the specified location
    public void newFrameAt(int offset) {
        framePointers.push(runStack.size() - offset);
    }
    
    //pops the top frame when returning from a function
    public void popFrame() {
        int returnValue = pop(), //return value to push back on the stack
        popTo = (Integer)framePointers.pop(); //index of last number, needs to be discarded
        for (int i = (runStack.size()); i > popTo; i--) {
            pop();
        }
        push(returnValue);
    }

    //pop the top of frame pointer stack
    public void popFramePointer() {
        framePointers.pop();
    }

    //return the value of top frame by peek
    public int peekFrame() {
        return (Integer)framePointers.peek();
    }

    //store the top item of stack into specified location in current frame
    public int store (int offset) {
        runStack.set((offset + peekFrame()), pop());
        return (Integer)runStack.get((offset + peekFrame()));
    }

    //used to load variables from locations anywhere in current frame
    public int load(int offset) {
        return push((Integer)runStack.get((offset + peekFrame())));
    }

    public int seeItemAt(int offset) {
        return (Integer)runStack.get((runStack.size() - offset - 1));
    }

    //push integer onto the RTS, return pushed item
    public Integer push(Integer x) {
        return push((int)x);
    }

    //Returns a vector containing the top frame.
    public Vector getCurrentFrame() {
        Vector currentFrameContents = new Vector();
        int popTo = (Integer)framePointers.pop(); //index of last item in frame
        int lastIndex = 0;
        while (runStack.size() > popTo) {
            currentFrameContents.add(pop());
        }
        framePointers.push(popTo);
        for (int i = 0; i < currentFrameContents.size(); i++) {
            push((Integer)currentFrameContents.get(i));
        }
        return currentFrameContents;
    }

    public boolean empty() {
        return runStack.isEmpty();
    }

}
