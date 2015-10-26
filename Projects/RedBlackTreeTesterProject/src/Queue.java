/**
 * Created by hanxi on 18/09/2015.
 */
public class Queue {
    int size = 10;
    int items = 0;
    Object[] array;
    private int front;
    private int rear;

    /**
     * Create an empty queue that lives in a small array.
     * Pre: Memory is available.
     * Post: Array created and indexes established.
     */
    public Queue(){
        this.array = new Object[size];
    }

    /**
     * Object method removes and returns reference in front of queue.
     * Pre-condition: queue not empty
     * @return object in front of queue.
     * @throws Exception
     */
    public Object deQueue() throws Exception {
        Object item = null;
        try{
            if(rear<size){
                item = array[front];
                front = (front+1) % size;
                items--;
            }
            else {
                throw new Exception("overflow");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Add an object reference to the rear of the queue.
     * Pre-condition Memory is available for doubling queue capacity when full.
     * Post-condition: queue now contains x in the rear.
     * @param x - is an object to be added to the rear of the queue.
     */
    public void enqueue(Object x){
        if(isFull()){
            size  = size * 2;
            Object[] newArray = new Object[size];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }

        if(isEmpty()) front = rear = 0;
        else{
            rear = (rear + 1) % size;
        }
        array[rear] = x;
        //System.out.println(array[rear]+" front "+front+" rear "+rear);
        //System.out.println(size);
        items++;
    }

    /**
     * Method getFront returns the front of the queue without removing it.
     * Pre-condition: queue not empty
     * @return the queue front without removal.
     */
    public Object getFront(){
        Object item = null;
        try{
            if(front != rear){
                item = array[front];
            }
            else {
                throw new Exception("overflow");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Boolean method returns true on empty queue, false otherwise.
     * Pre: None
     * @return Returns true if queue is empty.
     */
    public boolean isEmpty(){
        return items==0;
    }

    /**
     * Boolean method returns true if queue is currently at capacity, false otherwise.
     * If full returns true and additional enqueue calls are made, the queue will expand in size.
     * Pre: None
     * @return Returns true if queue is at current capacity.
     */
    public boolean isFull(){
        return items==size;
    }

    /**
     * The toString method returns a String representation of the current queue contents.
     * @return a string representation of the queue. It shows the front of the queue first. It then shows the second and third and so on.
     */
    public String toString(){
        String output="";
        if(rear <= front){
            for(int i=front; i<items; i++){
                output += array[i].toString() + " ";
            }
            for(int i=0; i<rear; i++){
                output += array[i].toString() + " ";
            }
        }
        else{
            for(int i=front; i<rear+1; i++){
                output += array[i].toString() + " ";
            }
        }
        return output;
    }

    /**
     * main is for testing the queue routines.
     * @param args - Command line parameters are not used.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Queue queue = new Queue();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.enqueue("7");
        System.out.println(queue.toString());
        System.out.println(queue.getFront());
        queue.deQueue();
        System.out.println(queue.toString());
        System.out.println(queue.getFront());
        queue.deQueue();
        System.out.println(queue.toString());
        System.out.println(queue.getFront());
        queue.deQueue();
        System.out.println(queue.toString());
        System.out.println(queue.getFront());
    }
}
