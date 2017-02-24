/**
 * Created by joey on 11/20/16.
 */

//This implementation include methods for adding pushing, inserting and removing nodes
public class LinkedListExtra extends LinkedList{

    public int length(){
        return listLength;
    }

    public Node getLast(){
        return endNode;
    }

    public void insertNode(int index, int newNode){
        Node newnode = new Node(newNode);
        if(index > listLength -1){
            System.out.println("OOB");
            return;
        }
        if(index == 0){
            Node oldhead = headNode;
            headNode = newnode;
            headNode.setNext(oldhead);
        }else{
            Node tempNode = headNode;
            while(index - 1 != 0){
                tempNode = tempNode.getNext();
                index--;
            }
            newnode.setNext(tempNode.getNext());
            tempNode.setNext(newnode);
        }
        listLength++;
    }

    public void removeNode(int index){
        if(index > listLength -1){
            System.out.println("OOB");
            return;
        }
        if(index == 0){
            headNode = headNode.getNext();
        }
        else{
            Node tempNode = headNode;
            while(index - 1 != 0){
                tempNode = tempNode.getNext();
                index--;
            }
            if(index == listLength - 1) {
                tempNode.setNext(null);
                endNode = tempNode;
            }else{
                tempNode.setNext(tempNode.getNext().getNext());
            }
        }
        listLength--;
    }

    public void pushNode(int value){
        Node newNode = new Node(value);
        endNode.setNext(newNode);
        endNode = newNode;
        listLength++;
    }


}
