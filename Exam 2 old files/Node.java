/**
 * Created by joey on 11/20/16.
 */
public class Node {
    private int value = 0;
    private Node next = null;

    public Node(int value){
        this.value = value;
    }
    public Node(int value, Node next){
        this.value = value;
        this.next = next;
    }

    public int getValue(){
        return this.value;
    }
    public Node getNext(){
        return this.next;
    }
    public void setValue(int value){
        this.value = value;
    }
    public void setNext(Node next){
        this.next = next;
    }
}
