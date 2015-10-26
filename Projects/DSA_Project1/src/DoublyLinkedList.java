/**
 * Created by hanxi on 7/09/2015.
 */
public class DoublyLinkedList<Character> {
    private DoubleNode<Character> head = null;
    private DoubleNode<Character> tail = null;
    private int size = 0;

    /** create a default list */
    public DoublyLinkedList(){

    }

    /** the element must be Character type and not null */
    public void addCharAtEnd(Character e){
        DoubleNode<Character> newNode = new DoubleNode<Character>(e); //create a new node for e
        if(tail == null){
            head = tail = newNode; //the only node in the list
        }
        else{
            tail.setNextNode(newNode); //link the new node with the last node
            newNode.setPreviousNode(tail); //link the new node with the last node
            tail = newNode; //tail now points to the last node
            tail.setNextNode(null);
        }
        size ++; //increase the size
    }
    /** The element will be added at the end of the list */
    /** Time Complexity: θ(1) */


    /** the element must be Character type and not null */
    public void addCharAtFront(Character e){
        DoubleNode<Character> newNode = new DoubleNode<Character>(e); //create a new node for e
        if(head == null){
            head = tail = newNode; //the only node in the list
        }
        else{
            head.setPreviousNode(newNode); //link the new node with the head node
            newNode.setNextNode(head); //link the new node with the head node
            head = newNode; //head now points to the last node
            head.setPreviousNode(null);
        }
        size++; //increase the size
    }
    /** The element will be added at the head of the list */
    /** Time Complexity: θ(1) */

    /** the element must be Character type and not null */
    public void deleteChar(Character e){
        if(size==0){
            System.out.println("the list is empty");
        }
        else if(size ==1){
            if(head.getElement() ==e && tail.getElement() == e){
                head = tail =null;
                size--;
            }
            else{
                System.out.println("element " + e + " is not in the list");
            }

        }
        else{
            DoubleNode<Character> current = head;
            boolean notExist = true; //the variable will be true if the element is not in the list
            while(current!=null){
                if(current.getElement() == e){
                    DoubleNode<Character> previous = current.getPreviousNode();
                    DoubleNode<Character> next = current.getNextNode();
                    if(previous==null){
                        head = next;
                        head.setPreviousNode(null);
                    }
                    else if(next==null){
                        tail = previous;
                        tail.setNextNode(null);
                    }
                    else{
                        previous.setNextNode(current.getNextNode());
                        next.setPreviousNode(current.getPreviousNode());
                    }
                    size--;
                    notExist = false;
                }
                current = current.getNextNode();
            }
            if(notExist){
                System.out.println("element " + e + " is not in the list");
            }
        }
    }
    /** The element will be deleted from the list */
    /** Time Complexity: θ(n) */

    /** The list must exist */
    public int countNodes(){
        int count=0;

        if(size == 0){
            return 0;
        }
        else{
            DoubleNode<Character> current = head;
            while(current.getNextNode()!=null){
                current = current.getNextNode();
                count++;
            }
            count++;
        }
        return count;
    }
    /** The number of nodes in the list will be counted*/
    /** Time Complexity: θ(n) */


    /** The list must exist */
    public boolean isEmpty(){
        boolean isEmpty=false;
        if(size == 0){
            isEmpty=true;
        }
        return isEmpty;
    }
    /** The method will return true if the list is empty */
    /** Time Complexity: θ(1) */

    /** The list must exist */
    public Character removeCharFromFront(){
        if(size == 0) return null; //nothing to delete
        else{
            DoubleNode<Character> temp = head;
            head = head.getNextNode();
            size--;
            if(head == null) tail = null; //list becomes empty
            return temp.getElement();
        }
    }
    /** The method will pop everything in the list from the head and return the removed elements */
    /** Time Complexity: θ(1) */

    /** The list must exist */
    public Character removeCharAtEnd(){
        if(size == 0) return null;
        else{
            DoubleNode<Character> temp = tail;
            tail = tail.getPreviousNode();
            size--;
            if(tail == null) head = null;
            return temp.getElement();
        }
    }
    /** The method will pop everything in the list from the end and return the removed elements */
    /** Time Complexity: θ(1) */

    /** reverse the list */
    public void reverse(){
        if(size == 0){
            System.out.println("The list is empty.");
        }
        else if(size ==1){

        }
        else{
            DoubleNode<Character> temp = head;
            head = tail;
            tail = temp;

            DoubleNode<Character> node = head;
            while(node!=null){
                temp = node.getNextNode();
                node.setNextNode(node.getPreviousNode());
                node.setPreviousNode(temp);
                node = node.getNextNode();
            }
        }

    }
    /** The list will be reversed */
    /** Time Complexity: θ(n) */

    /** The list much exist */
    public String toString(){
        StringBuilder result = new StringBuilder("List: ");
        DoubleNode<Character> node = head;
        if(size == 0){
            result.append("Empty");
        }
        else{
            while(node!=null){
                result.append(node.getElement());
                result.append(" ");
                node=node.getNextNode();
            }
        }
        return result.toString();
    }
    /** The list will be printed as String */
    /** Time Complexity: θ(n) */

}
