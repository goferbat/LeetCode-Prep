package Design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

class StockPrice {
    
    class Entry{
        int price;
        int timestamp;
        
        public Entry(int p, int t){
            price = p;
            timestamp = t;
        }
        public int getPrice () {return this.price;}
    }
    
    private int latesTime = 0;
    private PriorityQueue<Entry> maxHeap = new PriorityQueue<>(Comparator.comparing(Entry::getPrice).reversed());
    private PriorityQueue<Entry> minHeap = new PriorityQueue<>(Comparator.comparing(Entry::getPrice));;
    private Map<Integer, Integer> map = new HashMap<>();

    public StockPrice() {
    }
    
    public void update(int timestamp, int price) {
        if(timestamp > latesTime){
            latesTime = timestamp;
        }
        map.put(timestamp, price);
        maxHeap.offer(new Entry(price, timestamp));
        minHeap.offer(new Entry(price, timestamp));
    }
    
    public int current() {
        return map.get(latesTime);
    }
    
    public int maximum() {
        return head(maxHeap);
    }
    
    public int minimum() {
        return head(minHeap);
    }
    
    private int head(PriorityQueue<Entry> pq){
        while(!pq.isEmpty()){
            Entry e = pq.peek();
            if(map.get(e.timestamp) != e.price)
                pq.poll();
            else 
                return e.price;
        }
        return -1;
    }

    public static void main (String args[]) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
        stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
        assertEquals(stockPrice.current(), 5);
        stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
        assertEquals(stockPrice.maximum(), 10);
        stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
        stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
                                  // Timestamps are [1,2] with corresponding prices [3,5].
        stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
        assertEquals(stockPrice.maximum(), 5);
        stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
        stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.
        assertEquals(stockPrice.minimum(), 2);
    }
    
}
