/**
 * Created by joey on 11/20/16.
 */
public class GrowableArray {
    protected int[] innerArr = new int[10];
    protected int nextEmptyIndex = 0;
    protected int expandBy = 10;

    public GrowableArray(){
    }

    public GrowableArray(int size){
        this.innerArr = new int[size];
    }

    public void add(int item){
        if(nextEmptyIndex >= innerArr.length - 1){
            increaseSize();
        }
        innerArr[nextEmptyIndex] = item;
        nextEmptyIndex++;
    }

    public void increaseSize(){
        int[] newArr = new int[innerArr.length + expandBy];
        for(int i = 0; i < innerArr.length; i++){
            newArr[i] = innerArr[i];
        }
        innerArr = newArr;
    }

    public int atIndex(int num) throws IndexOutOfBoundsException{
            if (num >= nextEmptyIndex)
                throw new IndexOutOfBoundsException();
            else return innerArr[num];
    }
}
