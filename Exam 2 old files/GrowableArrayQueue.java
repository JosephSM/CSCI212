/**
 * Created by joey on 11/20/16.
 */
public class GrowableArrayQueue extends GrowableArray implements Queue {
    private int nextOnQueue = 0;
    private int Arrlen = 0;


    public void Enqueue(int n) {
        add(n);
        Arrlen++;
    }


    public int Dequeue() {
        int toreturn = innerArr[nextOnQueue];
        Arrlen--;
        nextOnQueue++;
        return toreturn;
    }


    public int length() {
        return Arrlen;
    }
}
