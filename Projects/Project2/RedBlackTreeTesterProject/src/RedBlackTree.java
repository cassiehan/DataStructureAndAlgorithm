/**
 * Created by hanxi on 18/09/2015.
 */

public class RedBlackTree<E> {
    RedBlackNode<E> root;
    private static final int B=1;
    private static final int R=0;
    private int size = 0;

    /**
     * Construct a RedBlackTree without data
     */
    public RedBlackTree() {

    }

    /**
     * post: The method closeBy(v) returns a value close to v in the tree. If v is found in the tree it returns v.
     * pre: none
     * time complexity: Big Theta (n)
     * @param v the value to search close by for.
     * @return the value closes to the param
     */
    public String closeBy(String v){
        RedBlackNode current = root;
        String output = "";
        while(true){
            if(v.compareTo(current.getData())<0){
                if(current.getLc()!=null && v.compareTo(current.getLc().getData())>0){
                    output = current.getData();
                    break;
                }
                if(current.getLc() == null){
                    output = current.getData();
                    break;
                }
                current = current.getLc();
            }
            else if(v.compareTo(current.getData())>0){
                if(current.getRc()!=null && v.compareTo(current.getRc().getData())<0){
                    output = current.getData();
                    break;
                }
                if(current.getRc() == null){
                    output = current.getData();
                    break;
                }
                current = current.getRc();
            }
        }
        return output;
    }



    private int count=0;

    /**
     * post: The boolean contains() returns true if the String v is in the RedBlackTree and false otherwise. It counts each comparison it makes in the variable recentCompares.
     * pre: none
     * time complexity: Big Theta (n)
     * @param v
     * @return
     */
    public boolean contains(String v){
        count=0;
        RedBlackNode current = root;
        boolean contain = false;
        //String v1 = v.toLowerCase();
        while (v.compareTo(current.getData())!=0){

            if(v.compareTo(current.getData())<0){
                current = current.getLc();
                count++;
            }
            else if(v.compareTo(current.getData())==0 || v.equalsIgnoreCase(current.getData())){
                return true;
            }
            else{
                current = current.getRc();
                count++;
            }

            if(current == null){
                count=0;
                return false;
            }
        }
        return true;
    }

    /**
     * pre: the method must be used after using the contains method
     * post: return number of comparisons made in last call on the contains method.
     * time complexity: Big Theta (1)
     * @return number of comparisons made in last call on the contains method.
     */
    public int getRecentCompares(){
        return count;
    }

    /**
     * pre: at least one node has been inserted into the tree
     * post return the number of values inserted into the tree.
     * time complexity: Big Theta (1)
     * @return number of values inserted into the tree.
     */
    public int getSize(){
        return size;
    }

    /**
     * post: This method calls the recursive method.
     * pre: the tree has a node
     * time complexity: Big Theta (log(n))
     * @return the height of the red black tree.
     */
    public int height(){
        return height(root);
    }

    /**
     * post:This a recursive routine that is used to compute the height of the red black tree.
     * pre: the node exists
     * time complexity: Big Theta (log(n))
     * @param node
     * @return the height of node t
     */
    public int height(RedBlackNode node){
        if(node == null) return -1;
        int height = Math.max(height(node.getLc()),height(node.getRc()))+1;
        return height;
    }

    /**
     * post: The no argument inOrderTraversal() method calls the recursive inOrderTraversal(RedBlackNode) - passing the root.
     * pre: the recursive inOrderTraversal(RedBlackNode) method exists
     * time complexity: Big Theta (n)
     */
    public void inOrderTraversal(){
        inOrderTraversal(root);
    }

    /**
     * post: Perfrom an inorder traversal of the tree.
     * pre: the no argument inOrderTraversal() method has been called
     * time complexity: Big Theta (n)
     * @param node
     */
    public void inOrderTraversal(RedBlackNode node){
        if(node != null){
            if(node.getLc()!=null){
                inOrderTraversal(node.getLc());
            }

            System.out.print("[data = " + node.getData() +
                    ": Color = " + node.getColor());
            if(node.getP()!=null){
                System.out.print(": Parent = "+node.getP().getData());
            }
            else{
                System.out.print(": Parent = " + "-1");
            }
            if(node.getLc()!=null){
                System.out.print(": LC = " + node.getLc().getData());
            }
            else{
                System.out.print(": LC = " + "-1");
            }
            if(node.getRc()!=null){
                System.out.print(": RC = " + node.getRc().getData());
            }
            else{
                System.out.print(": RC = " + "-1");
            }
            System.out.print("]");
            System.out.println();

            if(node.getRc()!=null){
                inOrderTraversal(node.getRc());
            }

        }
    }

    /**
     * post: The insert() method places a data item into the tree.
     * pre: none
     * time complexity: Big Theta (n)
     * @param value
     */
    public void insert(String value){
        if(root == null){
            root = new RedBlackNode<E>(value);
            root.setColor(0);
            size++;
        }
        else{
            RedBlackNode current = root;
            while(true){
                if(value.compareTo(current.getData())<0){
                    if (current.getLc()==null){
                        RedBlackNode newNode = new RedBlackNode(value);
                        current.setLc(newNode);
                        newNode.setP(current);
                        RBInsertFixup(current.getLc());
                        break;
                    }
                    current = current.getLc();
                }
                else if(value.compareTo(current.getData())>0){
                    if(current.getRc()==null){
                        RedBlackNode newNode = new RedBlackNode(value);
                        current.setRc(newNode);
                        newNode.setP(current);
                        RBInsertFixup(current.getRc());
                        break;
                    }
                    current = current.getRc();
                }
                else{
                    System.out.println("The word '" + value + "' is already in the dictionary");
                    return;
                }
            }
            size++;
        }
    }

    /**
     * post: leftRotate() performs a single left rotation.
     * pre: the node and its parent are both red, and its parent does not have sibling and is its grandparents rightchild
     * time complexity: Big Theta (1)
     * @param node
     */
    public void leftRotate(RedBlackNode node){
        // let child be node's right child
        RedBlackNode child = node.getRc();
        //node's right child = child's left child
        node.setRc(child.getLc());
        if(child.getLc()!=null){
            child.getLc().setP(node);
        }


        //child replace node as its parent's child
        if(node!=root){
            if(node.getP().getRc()==node){
                node.getP().setRc(child);
                child.setP(node.getP());
            }
            else{
                node.getP().setLc(child);
                child.setP(node.getP());
            }
        }
        else{
            child.setP(null);
            node.setP(child);
            root=child;
        }
        //child's left child = node
        child.setLc(node);
        node.setP(child);
    }

    /**
     * post: rightRotate() performs a single right rotation
     * pre: the node and its parent are both red, and its parent does not have sibling and is its grandparents leftchild
     * time complexity: Big Theta (1)
     * @param node
     */
    public void rightRotate(RedBlackNode node){
        // let child be node's left child
        RedBlackNode child = node.getLc();
        //node's left child = child's right child
        node.setLc(child.getRc());
        if(child.getRc()!=null){
            child.getRc().setP(node);
        }

        //child replace node as its parent's child
        if(node!=null){
            if(node.getP().getRc()==node){
                node.getP().setRc(child);
                child.setP(node.getP());
            }
            else{
                node.getP().setLc(child);
                child.setP(node.getP());
            }
        }
        else{
            child.setP(null);
            node.setP(child);
            root=child;
        }
        //child's right child = node
        child.setRc(node);
        node.setP(child);
    }

    /**
     * post: This method displays the RedBlackTree in level order.
     * pre: the tree is not empty
     * time complexity: Big Theta (n)
     * @throws Exception
     */
    public void levelOrderTraversal() throws Exception {
        Queue queue = new Queue();
        RedBlackNode node = root;
        queue.enqueue(node);
        //System.out.println("enqueue "+node.getData());

        while(!queue.isEmpty()){
            RedBlackNode p = (RedBlackNode) queue.deQueue();

            System.out.print("[data = " + p.getData() +
                    ": Color = " + p.getColor());
            if(p.getP()!=null){
                System.out.print(": Parent = "+p.getP().getData());
            }
            else{
                System.out.print(": Parent = " + "-1");
            }
            if(p.getLc()!=null){
                System.out.print(": LC = " + p.getLc().getData());
            }
            else{
                System.out.print(": LC = " + "-1");
            }
            if(p.getRc()!=null){
                System.out.print(": RC = " + p.getRc().getData());
            }
            else{
                System.out.print(": RC = " + "-1");
            }
            System.out.print("]");
            System.out.println();

            if(p.getLc()!=null){
                queue.enqueue(p.getLc());
                //System.out.println("enqueue " + p.getLc().getData());
            }
            if(p.getRc()!=null){
                queue.enqueue(p.getRc());
                //System.out.println("enqueue " + p.getRc().getData());
            }
        }
    }

    /**
     * post: the method return the node's sibling
     * pre: none
     * time complexity: Big Theta (1)
     * @param node
     * @return the node's sibling
     */
    private RedBlackNode siblingOf(RedBlackNode node){
        if(node==null || node.getP()==null){
            return null;
        }
        else{
            if(node == node.getP().getLc()){
                return node.getP().getRc();
            }
            else{
                return node.getP().getLc();
            }
        }
    }

    /**
     * post: Fixing up the tree so that Red Black Properties are preserved.
     * pre: a new node was inserted into the tree
     * time complexity: Big Theta (n)
     * @param node
     */
    public void RBInsertFixup(RedBlackNode node) {
        if (node != null && node != root && node.getP().getColor().equals("Red")) {
            /*recolor*/
            if(siblingOf(node.getP())!=null && siblingOf(node.getP()).getColor().equals("Red")){
                //System.out.println("recolor");
                node.getP().setColor(0);
                siblingOf(node.getP()).setColor(0);
                node.getP().getP().setColor(1);
                RBInsertFixup(node.getP().getP());
            }
            else if(node.getP() == node.getP().getP().getRc()) {
                if(node == node.getP().getLc()){
                    //System.out.println("rightrotate");
                    rightRotate(node=node.getP());
                }
                node.getP().setColor(0);
                node.getP().getP().setColor(1);
                //System.out.println("leftrotate");
                leftRotate(node.getP().getP());
            }
            else if(node.getP() == node.getP().getP().getLc()){
                if(node == node.getP().getRc()){
                    //System.out.println("leftrotate");
                    leftRotate(node = node.getP());
                }
                node.getP().setColor(0);
                node.getP().getP().setColor(1);
                //System.out.println("rightrotate");
                rightRotate(node.getP().getP());
            }
            root.setColor(0);
        }
    }

    /**
     * post: The no argument reverseOrderTraversal() method calls the recursive reverseOrderTraversal(RedBlackNode) - passing the root.
     * pre: The reverseOrderTraversal(RedBlackNode) method exists
     * time complexity: Big Theta (n)
     */
    public void reverseOrderTraversal(){
        reverseOrderTraversal(root);
    }

    /**
     * post: Perform a reverseOrder traversal of the tree.
     * pre: The no argument inOrderTraversal() method has been called
     * time complexity: Big Theta (n)
     * @param node
     */
    public void reverseOrderTraversal(RedBlackNode node){
        if(node != null){
            if(node.getRc()!=null){
                reverseOrderTraversal(node.getRc());
            }

            System.out.print("[data = " + node.getData() +
                    ": Color = " + node.getColor());
            if(node.getP()!=null){
                System.out.print(": Parent = "+node.getP().getData());
            }
            else{
                System.out.print(": Parent = " + "-1");
            }
            if(node.getLc()!=null){
                System.out.print(": LC = " + node.getLc().getData());
            }
            else{
                System.out.print(": LC = " + "-1");
            }
            if(node.getRc()!=null){
                System.out.print(": RC = " + node.getRc().getData());
            }
            else{
                System.out.print(": RC = " + "-1");
            }
            System.out.print("]");
            System.out.println();

            if(node.getLc()!=null){
                reverseOrderTraversal(node.getLc());
            }

        }
    }
    /*Perform a reverseOrder traversal of the tree.*/

    /**
     * Test the RedBlack tree.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        RedBlackTree rbt = new RedBlackTree();

        for(int j = 1; j <= 5; j++) rbt.insert(""+j);

        // after 1..5 are inserted
        System.out.println("RBT in order");
        rbt.inOrderTraversal();
        System.out.println("RBT level order");
        rbt.levelOrderTraversal();


        // is 3 in the tree
        if(rbt.contains("44")) System.out.println("Found 44");
        else System.out.println("No 4 found, maybe you mean "+ rbt.closeBy("44"));

        // display the height
        System.out.println("The height is " + rbt.height());
        rbt.insert("aam");
        System.out.println(rbt.contains("AAM"));
    }
}
