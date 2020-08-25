import java.util.ArrayList;

/**
 * Priority Queue Class with Linked List implementation [DEFAULT]:
 * Methods: insert, getFront, deleteMax & deleteAll
 */
public class PQLinkedList {
    //number of item in List
    public int numberOfElements = 0;


    //Node class
    public static class Node {
        public String item;
        public Node next;
        public int priority; //priority of item
    }

    Node front;// top node of linked list

    //initialize priority Queue
    public PQLinkedList() {
        front = null;
    }

    /**
     * INSERTION METHOD:
     * Add node according to their priorities. if priority of given node is higher
     * than current node. Put it in front of current node otherwise after current.
     * @param priority : priority of item
     * @param item : inserted word
     */
    void insert(int priority, String item) {
        Node temp, newNode;
        newNode = new Node();
        newNode.priority = priority;
        newNode.item = item;
        if (front == null || priority > front.priority) {
            newNode.next = front;
            front = newNode;
        } else {
            temp = front;
            while (temp.next != null && temp.next.priority >= priority)
                temp = temp.next;
            newNode.next = temp.next;
            temp.next = newNode;
        }
        numberOfElements++;
    }

    //return front item of list
    String getFront() {
        if (front == null)
            System.out.println("is empty");
        return front.item;
    }

    //remove and return front item(Max priority item) from list
    String deleteMax() {
        if (front == null)
            System.out.println("is empty");
        Node temp = front;
        front = front.next;
        return temp.item;
    }

    //remove all item and print one by one until list is empty
    void removeAll() {
        int count = 0;
        Node curr = front;
        if(front == null)
            System.out.println("[DEBUG] list is Empty");
        while (curr != null) {
            count++;
            // System.out.print(" priority :" + curr.priority);
            System.out.println("["+count+ "] -> " + deleteMax());
            curr = curr.next;
        }
    }

    public void updateList(ArrayList<String> topSuggestion) {
        int count = 0;
        Node curr = front;
        if(front == null)
            System.out.println("[DEBUG] list is Empty");
        while (curr != null) {
            topSuggestion.set(count,deleteMax());
            count++;
            curr = curr.next;
        }
    }
}
