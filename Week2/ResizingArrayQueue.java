import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
   private Item[] q;     // queue array
   private int n;        // number of elements in queue
   private int first;    // index of first element of queue
   private int last;     // index of next available slot
   
   // Initializes an empty queue
   public ResizingArrayQueue() {
      q = (Item[]) new Object[2];
      n = 0;
      first = 0;
      last = 0;
   }
   
   // Is this queue empty?
   public boolean isEmpty() {
      return n == 0;
   }
   
   // Returns number of items in queue
   public int size() {
      return n;
   }
   
   // Resize underlying array
   public void resize(int capacity) {
      assert capacity >= n;
      Item[] temp = (Item[]) new Object[capacity];
      for (int i = 0; i < n; i++) {
         temp[i] = q[(first + i) % q.length];
      }
      q = temp;
      first = 0;
      last = n;
   }
   
   // Adds item to queue
   public void enqueue(Item item) {
      // double array size if necessary; recopy to front of array
      if (n == q.length) resize(2*q.length);  // double array if necessary
      q[last++] = item;                  // add item
      if (last == q.length) last = 0;    // wrap-around
      n++;
   }
   
   // Removes and returns least recently added item in queue
   public Item dequeue() {
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      Item item = q[first];
      q[first] = null;      // to avoid loitering
      n--;
      first++;
      if (first == q.length) first = 0;   // wrap-around
      // shrink array size if necessary
      if (n > 0 && n == q.length/4) resize(q.length/2);
      return item;
   }
   
   // Returns (without removing) least recently added item in queue
   public Item peek() {
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      return q[first];
   }
   
   // Returns an iterator; iterates over items in queue in FIFO order
   public Iterator<Item> iterator() {
      return new ArrayIterator();
   }
   
   // an iterator; doesn't implement 'remove()' since it's optional
   public class ArrayIterator implements Iterator<Item> {
      private int i = 0;
      public boolean hasNext() { return i < n; }
      public void remove() { throw new UnsupportedOperationException(); }
      
      public Item next() {
         if (!hasNext()) throw new NoSuchElementException();
         Item item = q[(i + first) % q.length];
         i++;
         return item;
      }
   }
   
   // Unit tests for ResizingArrayQueue data type
   // @param args the command-line arguments
   public static void main(String[] args) {
      ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
      while (!StdIn.isEmpty()) {
         String item = StdIn.readString();
         if (!item.equals("-")) queue.enqueue(item);
         else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
      }
      StdOut.println("(" + queue.size() + " left on queue)");
   }
   
}
   
   
   
   
   
   
   
   
   
   