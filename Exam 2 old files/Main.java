import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MultiHashtable;
import com.sun.org.apache.xpath.internal.operations.Mult;
import java.util.Arrays;

/**
 * Created by joey on 11/19/16.
 */
public class Main {

    public static void main(String[] args){

        // -------------------------------Part 1 - Highest Number -----------------------------------//
        //helper methods at bottom of page;


        int[] myNumbers = randList(15);

        // Inheritance Implementation                   Array , # of threads
        Thread[] threadList = makeThreadsInheritance(myNumbers, 4);
        startAll(threadList);
        joinAll(threadList);
        System.out.println("Highest number (Inheritance):"  + MultiInherit.highestNumber);


        // Interface Implementation                    Array , # of threads
        Thread[] threadList2 = makeThreadsInterface(myNumbers, 4);
        startAll(threadList2);
        joinAll(threadList2);
        System.out.println("Highest number (Interface):"  + MultiInterface.highestNumber);
        System.out.println("Array of random Numbers" + Arrays.toString(myNumbers));

        //--------------------------------- Part 2 - Stacks and Queues ------------------------------------------//

        //To keep the code more readable and to adhere to the instructions
        //directly I left out some try-catch blocks
        //Note: exceptions are thrown in various stacks and queues
        //This was done intentionally for readability not design, otherwise they'd be there.

        Stack myStack1 = new LinkedListStack();
        Stack myStack2 = new GrowableArrayStack();

        System.out.println("Pushing items into stack:");

        for(int i = 0; i < 5; i++){
            myStack1.Push(i);
            myStack2.Push(i);
            System.out.println(i); //Alternatively, you can output this message in your push method
        }

        System.out.println("Popping items out of stack:");

        for(int i = 0; i < 5; i++){
            System.out.println(myStack1.Pop());
            System.out.println(myStack2.Pop());
        }

        Queue myQueue1 = new LinkedListQueue();
        Queue myQueue2 = new GrowableArrayQueue();

        System.out.println("Enqueueing items into queue:");

        for(int i = 0; i < 5; i++){
            myQueue1.Enqueue(i);
            myQueue2.Enqueue(i);
            System.out.println(i); //Alternatively, you can output this message in your enqueue method

        }

        System.out.println("Dequeuing items from queue:");

        for(int i = 0; i < 5; i++){
            System.out.println(myQueue1.Dequeue());
            System.out.println(myQueue2.Dequeue());

        }

        // ---------------------------------------- Part 3 - Generics --------------------------------//

        //This class is a Generic class which allows it to work with various types of objects
        // <Integer>, <String>, <Node>, etc.

        GenericGrowableArray<String> arr = new GenericGrowableArray<String>();
        GenericGrowableArray<Integer> arr2 = new GenericGrowableArray<Integer>();

        //implementing generic container
        // new arrays must begin as Object arrays and then casted to type (T[]).
        // See GenericGrowableArray class for full implementation.
        //methods and properties must be set to take a generic type;

        String[] str = {"Hello", "Professor", "Lee", "!"};
        Integer[] ints = {1, 2, 3, 4};

        for(String string: str)
            arr.add(string);

        for(Integer integer: ints)
            arr2.add(integer);

        for(int i = 0; i < 4; i++){
            System.out.print(arr.atIndex(i) + " ");
            System.out.print(arr2.atIndex(i) + " ");
        }
    }
    //------------------------------------------ Helper Methods --------------------------------------//

    private static void startAll(Thread[] threads){
        for(Thread thread : threads){
            thread.start();
        }
    }


    private static void joinAll(Thread[] threads){
        for(Thread thread: threads){
            try {
                thread.join();
            }catch(Exception ex){}
        }
    }


    private static int[] randList(int length){
        int[] out = new int[length];
        for (int i = 0; i < length; i++){
            out[i] = (int)(Math.random() * 100);
        }
        return out;
    }


    private static Thread[] makeThreadsInterface(int[] arr, int numThreads){
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++){
            int length = arr.length / numThreads;
            int begin = i == 0 ? 0 : i * length;
            int end = (i == numThreads - 1 ? arr.length : begin + length) - 1;
            threads[i] = new Thread(new MultiInterface(arr, begin, end));
        }
        return threads;
    }


    private static Thread[] makeThreadsInheritance(int[] arr, int numThreads) {
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++){
            int length = arr.length / numThreads;
            int begin = i == 0 ? 0 : i * length;
            int end = (i == numThreads - 1 ? arr.length : begin + length) - 1;
            threads[i] = new MultiInherit(arr, begin, end);
        }

        return threads;
    }

}
