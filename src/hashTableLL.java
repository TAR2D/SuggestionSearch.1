import java.util.Arrays;

public class hashTableLL {
    /*
    a hash table with separate chains to resolve conflicts
     */
    private static class Node {
        private User id;
        private Node next;

        public Node(User newUser, Node link) {
            id = newUser;
            next = link;
        }

        public void getData() {
            System.out.println(id.toString());
        }
    }

    private final int prime = 13;
    private final Node[] hashDataArray;
    int numberOfIDs;

    public hashTableLL(int size) {
        hashDataArray = new Node[size];
        Arrays.fill(hashDataArray, null);
    }

    private int hash(String key) {
        int hashIndex = 0;
        for (int i = 0; i < key.length(); i++) {
            hashIndex = (hashIndex * prime) % hashDataArray.length + (int) key.charAt(i);
            hashIndex = hashIndex % hashDataArray.length;
        }
        return hashIndex;
    }

    public void insert(User ID) {
        int index = hash(ID.getName());
        if (!search(ID.getName()))
            hashDataArray[index] = new Node(ID, null);
        numberOfIDs++;
        System.out.println("[DEBUG] : User added to location:" + index);
    }

    public boolean search(String key) {
        int hashIndex = hash(key);
        return inLinkedList(key, hashDataArray[hashIndex]);
    }

    private boolean inLinkedList(String key, Node start) {
        Node temp = start;
        boolean keyFound = false;

        while (temp != null && !keyFound) {
            keyFound = temp.id.getName().equals(key);
            temp = temp.next;
        }
        return keyFound;
    }

    public User getUser(String key) {
        return hashDataArray[hash(key)].id;
    }

    public void printAllData() {
        Node temp;
        for (Node node : hashDataArray) {
            temp = node;
            if (temp != null)
                temp.getData();
        }
        System.out.println("Total no. of users: " + numberOfIDs);
    }
}
