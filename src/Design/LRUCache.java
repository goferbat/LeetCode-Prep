package Design;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LRUCache {

    public HashMap<Integer, Node> hm;
    class Node {
        int key;
        int val;
        Node next;
        Node prev;
        Node () {}
        Node (int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    Node head;
    Node tail;
    int cache_capacity;

    public LRUCache (int capacity) {
        hm = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        cache_capacity = capacity;
    }
    
    public int get (int key) {
        Node node = hm.get(key);
        int ret = -1;
        if (node != null) {
            remove(node);
            ret = node.val;
            add(node);
        }
        return ret;
    }
    
    public void put (int key, int value) {
        Node node = hm.get(key);
        if (node != null) {
            remove(node);
            node.val = value;
            add(node);

        } else {
            if (hm.size() == cache_capacity) {
                hm.remove(tail.prev.key);
                remove(tail.prev);
            }

            Node toAdd = new Node(key, value);
            hm.put(key, toAdd);
            add(toAdd);
        }
    }

    public void remove(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public void add(Node node) {
        Node head_next = head.next;
        head_next.prev = node;
        node.next = head_next;
        node.prev = head;
        head.next = node;
    }

    public static void main (String args[]) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        assertEquals(lRUCache.get(1), 1);
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        assertEquals(lRUCache.get(2), -1);
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        assertEquals(lRUCache.get(1), -1);
        assertEquals(lRUCache.get(3), 3);
        assertEquals(lRUCache.get(4), 4);
        
    }
}