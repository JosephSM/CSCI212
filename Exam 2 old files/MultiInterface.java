/**
 * Created by joey on 11/19/16.
 */
public class MultiInterface implements Runnable{

    private int begin;
    private int end;
    private int[] theArr;
    public static int highestNumber = 0;


    public MultiInterface(int[] arr, int begin, int end) {
        this.begin = begin;
        this.end = end;
        this.theArr = arr;
    }


    public void run(){
        for (int i = begin; i <= end; i++) {
            int numToCheck = theArr[i];
            if(numToCheck > highestNumber) {
                this.setHighestNumber(numToCheck);
            }
        }
    }


    synchronized void setHighestNumber(int numToCheck){
        if(numToCheck > highestNumber){
            MultiInterface.highestNumber = numToCheck;
        }
    }

}
