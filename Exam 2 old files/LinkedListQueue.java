/**
 * Created by joey on 11/20/16.
 */
public class LinkedListQueue extends LinkedList implements Queue{


    public void Enqueue(int value){
        Node newNode = new Node(value);
        //if linked list is empty
        if(headNode == null){
            headNode = newNode;
            endNode = newNode;
        }
        else {
            endNode.setNext(newNode);
            endNode = newNode;
        }
        listLength++;
    }


    public int Dequeue() {
        int toreturn = headNode.getValue();
        if(headNode == null)
            throw new IndexOutOfBoundsException();
        else if(listLength == 1){
            headNode = null;
            endNode = null;
        }
        else{
            headNode = headNode.getNext();
        }
        listLength--;
        return toreturn;
    }


    public int length() {
        return listLength;
    }
}
