package Problems;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

public class LFUCache {
    
    public class Node {
        int key;
        int val;
        int count;
        Node next;
        Node prev;

        public Node(){};
        public Node(int count) {
            this.count = count;
        }
        public Node(int key, int val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    HashMap<Integer,Node> hm;
    Node head = new Node(Integer.MAX_VALUE);
    Node tail= new Node(Integer.MIN_VALUE);
    int cache_capacity;

    public LFUCache(int capacity) {
        hm = new HashMap<>();
        head.next = tail;
        tail.prev = head;
        cache_capacity = capacity;
    }
    
    public int get(int key) {
        Node node = hm.get(key);
        int res = -1;
        if (node != null) {
            remove(node);
            node.count = node.count + 1;
            add(node);
            res = node.val;
        }
        return res;
    }
    
    public void put(int key, int value) {
        Node node = hm.get(key);
        if (node != null) {
            remove(node);
            node.key = key;
            node.val = value;
            node.count = node.count + 1;
            add(node);
        } else {
            if (hm.size() == cache_capacity) {
                if (cache_capacity == 0) return;
                hm.remove(tail.prev.key);
                remove(tail.prev);
            }

            Node newNode = new Node (key, value, 1);
            hm.put(key, newNode);
            add(newNode);
        }
        
    }

    // NEED TO SOMEHOW MAKE THIS O(1)
    public void add(Node node) { // adding node based on count frequency
        // this is O(n)
        Node dummyHead = head.next;
        while (dummyHead != null) {
            if (node.count >= dummyHead.count) {
                Node head_prev = dummyHead.prev;
                dummyHead.prev = node;
                node.next = dummyHead;
                head_prev.next = node;
                node.prev = head_prev;
                break;
            }
            dummyHead = dummyHead.next; 
        }
    }

    public void remove(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    public static void main (String args[]) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        assertEquals(lfu.get(1), 1);      // return 1
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        
        assertEquals(lfu.get(2), -1);   
        assertEquals(lfu.get(3), 3);   
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        assertEquals(lfu.get(1), -1);   
        assertEquals(lfu.get(3), 3);   
        assertEquals(lfu.get(4), 4);   
    }
}
