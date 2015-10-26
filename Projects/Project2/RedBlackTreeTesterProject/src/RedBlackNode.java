/**
 * Created by hanxi on 18/09/2015.
 */
public class RedBlackNode<E> {

    private String data="-1";
    private int color=1; // black is 0, and red is 1
    private RedBlackNode p=null;
    private RedBlackNode lc=null;
    private RedBlackNode rc=null;

    /**
     * Construct a RedBlackNode without data
     */
    public RedBlackNode(){

    }

    /**
     * Construct a RedBlackNode with data
     * @param data a simple value held in the tree
     */
    public RedBlackNode(String data){
        this.data = data;
    }

    /**
     * Construct a RedBlackNode with data, color, parent pointer, left child pointer and right child pointer.
     * @param data a simple value held in the tree
     * @param p the parent pointer
     * @param color either RED or BLACK
     * @param lc the pointer to the left child (will be null only for the node that represents all external nulls.
     * @param rc the pointer to the right child (will be null only for the node that represents all external nulls.
     */
    public RedBlackNode(String data, RedBlackNode p, int color, RedBlackNode lc, RedBlackNode rc) {
        this.data = data;
        this.p = p;
        this.color = color;
        this.lc = lc;
        this.rc = rc;
    }

    /**
     * Post: The getData() method returns the data in the node.
     * Pre: the node exists
     * @return the data value in the node
     */
    public String getData() {
            return data;
    }

    /**
     * Post: The setData() method sets the data or key of the RedBlackNode.
     * Pre: the data should be a string
     * @param data a string holding a node's data value
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * post:The getColor() method returns RED or BLACK.
     * pre: the color has been set
     * @return the color value (RED or BLACK)
     */
    public String getColor() {
        if(color == 0){
            return "Black";
        }
        else{
            return "Red";
        }

    }

    /**
     * post: The setColor() method sets the color of the RedBlackNode.
     * pre: the node exists
     * @param color is either RED or BLACK
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * post: The getP() method returns the parent of the RedBlackNode.
     * pre: the node has a parent
     * @return the parent field
     */
    public RedBlackNode getP() {
        return p;
    }

    /**
     * post: The setP() method sets the parent of the RedBlackNode.
     * pre: the node exists
     * @param p
     */
    public void setP(RedBlackNode p) {
        this.p = p;
    }

    /**
     * post: The getLc() method returns the left child of the RedBlackNode.
     * pre: the node exists
     * @return the left child field
     */
    public RedBlackNode getLc() {
        return lc;
    }

    /**
     * post: The setLc() method sets the left child of the RedBlackNode.
     * pre: the node exists
     * @param lc establishes a left child for this node
     */
    public void setLc(RedBlackNode lc) {
        this.lc = lc;
    }

    /**
     * post: The getRc() method returns the right child of the RedBlackNode.
     * pre: the node exists
     * @return the right child field
     */
    public RedBlackNode getRc() {
        return rc;
    }

    /**
     * post: The setRc() method sets the right child of the RedBlackNode.
     * pre: the node exists
     * @param rc establishes a right child for this node.
     */
    public void setRc(RedBlackNode rc) {
        this.rc = rc;
    }

}
