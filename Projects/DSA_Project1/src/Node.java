/**
 * Created by hanxi on 9/09/2015.
 */
public class Node<E> {
    private E element;
    private Node<E> next;

    /* build a default constructor */
    public Node(){

    }
    /** Time Complexity: theta(1) */

    /* element must be E type */
    public Node(E e){
        element = e;
    }
    /* A Node will be created */
    /** Time Complexity: θ(1) */

    /* the element has already beeen set */
    public E getElement() {
        return element;
    }
    /* the element of this node will be return */
    /** Time Complexity: θ(1) */

    /* this node should not be the last one, and if so it will return null */
    public Node<E> getNext() {
        return next;
    }
    /* next node will be return */
    /** Time Complexity: θ(1) */

    /* the noxt node must be E type */
    public void setNext(Node<E> next) {
        this.next = next;
    }
    /* next node will be set */
    /** Time Complexity: θ(1) */

    /* the element of this node must be set */
    public String toString(){
        StringBuilder result = new StringBuilder("");
        result.append(element);
        return result.toString();
    }
    /* the element of this node will be return as a string */
    /** Time Complexity: θ(1) */
}
