/**
 * Created by joey on 11/20/16.
 */
public class LinkedListStack extends LinkedList implements Stack {

    public void Push(int value){
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


    public int Pop() throws IndexOutOfBoundsException{
        int toreturn = endNode.getValue();
        //if only no elements in linked list throw exception
        if(length() < 1) throw new IndexOutOfBoundsException();

        //if list has 1 elem
        else if(length() == 1){
            headNode = null;
            endNode = null;

        }
        //point second to last node to null
        //and set that to endnode
        else {
            Node tempNode = headNode;
            int secondToLast = listLength - 2;
            for (int i = 0; i < secondToLast; i++) {
                tempNode = tempNode.getNext();
            }
            tempNode.setNext(null);
            endNode = tempNode;
        }
        listLength--;
        return toreturn;
    }

    public int length(){
        return listLength;
    }




}
