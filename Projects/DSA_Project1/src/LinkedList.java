/**
 * Created by hanxi on 9/09/2015.
 */
public class LinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    int size = 0;

    public int getSize() {
        return size;
    }

    /* a default constructor */
    public LinkedList(){

    }

    /* the size of the list must be larger than or equal to zero */
    public E getElement(int index){
        if(index<0) return null;
        else if(index == 0){
            return head.getElement();
        }
        else{
            Node<E> current = head;
            for(int i=0; i<index; i++){
                current = current.getNext();
            }
            return current.getElement();
        }
    }
    /* the method will return the node associated with the index */
    /** Time Complexity: θ(n) */

    /* the index must larger than or equal to zero */
    public void add(int index, E e){
        if(index == 0){
            addAtHead(e);
        }else{
            Node<E> current = head;
            for(int i=1; i<index; i++){
                current = current.getNext();
            }
            Node<E> temp = current.getNext();
            current.setNext(new Node<E>(e));
            (current.getNext()).setNext(temp);
            size ++;
            //System.out.println("4. " + size + " " + current.getNext().getElement().toString());
        }
    }
    /* the element will be added to the index place */
    /** Time Complexity: θ(n) */

    /* the node must be in E type */
    private void addAtHead(E e){
        Node<E> newNode = new Node<E>(e);
        if(head == null){
            head = tail = newNode;
        }
        else{
            newNode.setNext(head);
            head = newNode;
        }
        size++;
        //System.out.println("4. "+size+" "+head.getElement().toString());
    }
    /* the element will be added to the head of the list */
    /** Time Complexity: θ(1) */

    /* the index must be larger than or equal to zero, and the index should not be larger than the size of the list */
    public E remove(int index){
        if(index == 0) return removeFirstNode();
        else if(index == size -1) return removeLastNode();
        else{
            Node<E> previous = head;
            for(int i=0; i<index; i++){
                previous = previous.getNext();
            }
            Node<E> current = previous.getNext();
            previous.setNext(current.getNext());
            size --;
            return current.getElement();
        }
    }
    /* the element with the index will be removed from the list */
    /** Time Complexity: θ(n) */

    /* the size of the list must be larger than zero */
    private E removeLastNode(){
        if(size == 0) return null;
        else if(size ==1){
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.getElement();
        }
        else{
            Node<E> current = head;
            for(int i=0; i<size; i++){
                current = current.getNext();
            }
            Node<E> temp = tail;
            tail = current;
            tail.setNext(null);
            size --;
            return temp.getElement();

        }
    }
    /* the last node of this list will be removed */
    /** Time Complexity: θ(n) */

    /* the size of the list must be larger than zero */
    private E removeFirstNode(){
        if(size == 0) return null;
        else if(size == 1){
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.getElement();
        }
        else{
            Node<E> temp = head;
            head = temp.getNext();
            size --;
            return temp.getElement();
        }
    }
    /* the first node of this list will be removed */
    /** Time Complexity: θ(1) */
}
