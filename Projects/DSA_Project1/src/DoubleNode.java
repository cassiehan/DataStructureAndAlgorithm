/**
 * Created by hanxi on 7/09/2015.
 */
public class DoubleNode<Character> {
    private Character element;
    private DoubleNode<Character> next;
    private DoubleNode<Character> previous;

    /* build a default constructor */
    public DoubleNode(){

    }

    /* element must be Character */
    public DoubleNode(Character e){
        element = e;
    }
    /* A doubleNode will be created */
    /** Time Complexity: θ(1) */

    /* The previous node should be set as a DoubleNode or null*/
    public DoubleNode getPreviousNode(){
        return previous;
    }
    /* the previous node will be returned */
    /** Time Complexity: θ(1) */

    /* The parameter must be a DoubleNode */
    public void setPreviousNode(DoubleNode newNode){
        this.previous = newNode;
    }
    /* This node will be connected to a previous node */
    /** Time Complexity: θ(1) */

    /* The previous node should be set as a DoubleNode or null*/
    public DoubleNode getNextNode(){
        return next;
    }
    /* the next node will be returned */
    /** Time Complexity: θ(1) */

    /* The parameter must be a DoubleNode */
    public void setNextNode(DoubleNode newNode){
        this.next = newNode;
    }
    /* This node will be connected to a new node */
    /** Time Complexity: θ(1) */

    /* The element of this node should be set as a Character type */
    public Character getElement(){
        return element;
    }
    /* the element of this node will be returned */
    /** Time Complexity: θ(1) */

    /* The parameter must be a Character */
    public void setElement(Character element){
        this.element = element;
    }
    /* the element of this node will be set */
    /** Time Complexity: θ(1) */

}
