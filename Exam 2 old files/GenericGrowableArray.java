/**
 * Created by joey on 11/20/16.
 */
public class GenericGrowableArray<T> {


    protected T[] innerArr;
    protected int nextEmptyIndex = 0;
    protected int expandBy = 10;


    public GenericGrowableArray(){
        T[] inputarr = (T[])new Object[10];
        innerArr = inputarr;
    }


    public GenericGrowableArray(int size){
        T[] inputarr = (T[])new Object[size];
        innerArr = inputarr;
    }


    public void add(T item){
        if(nextEmptyIndex >= innerArr.length - 1){
            increaseSize();
        }
        innerArr[nextEmptyIndex] = item;
        nextEmptyIndex++;
    }


    public void increaseSize(){
        T[] newArr = (T[])new Object[innerArr.length + expandBy];
        for(int i = 0; i < innerArr.length; i++){
            newArr[i] = innerArr[i];
        }
        innerArr = newArr;
    }


    public T atIndex(int num) throws IndexOutOfBoundsException{
            if (num >= nextEmptyIndex)
                throw new IndexOutOfBoundsException();
            else return innerArr[num];
    }
}
