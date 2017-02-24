/**
 * Created by joey on 11/20/16.
 */
public class GrowableArrayStack extends GrowableArray implements Stack {


    public void Push(int n) {
        add(n);
    }


    public int Pop() {
        int toreturn = atIndex(nextEmptyIndex - 1);
        nextEmptyIndex--;
        return toreturn;
    }


    public int length() {
        return nextEmptyIndex;
    }
}
