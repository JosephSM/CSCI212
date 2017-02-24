/**
 * Created by joey on 11/20/16.
 */
public class LinkedList{
    protected Node headNode = null;
    protected Node endNode = null;
    protected int listLength = 0;

    //construct a linked list with no nodes
    public LinkedList(){
    }

    //construct a linked list with one node
    public LinkedList(int value){
        Node newnode = new Node(value);
        headNode = newnode;
        endNode = newnode;
        listLength++;
    }

    //I did not implement any insert or remove functions in this class
    //this design choice was due to the fact that my stack and queue classes
    //have their own names (push vs enqueue).  Also I did think that a stack or queue
    //should be able to remove or insert to arbitrary locations in the linkedlist

    //These functionalities were put in a subclass of LinkedList called LinkedListExtra

}
