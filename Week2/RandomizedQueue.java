import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] q;     // queue array
   private int n;        // number of elements in queue
   private int first;    // index of first element of queue
   private int last;     // index of next available slot

   
   public RandomizedQueue() {               // construct an empty randomized queue
      q = (Item[]) new Object[2];
      n = 0;
      first = 0;
      last = 0;
   }
  
   public boolean isEmpty() {               // is the randomized queue empty?
      return n == 0;
   }
      
   public int size() {                      // return the number of items on the randomized queue
      return n;
   }
   
   // For some reason, the else statement of the while loop
   // said 'first++', the same as the if statement.
   // Changed it to 'first = 0'. Hopefully this fixes a lot!
   
   private void resize(int capacity) {  // changed from public
      assert capacity >= n;
      Item[] temp = (Item[]) new Object[capacity];
      for (int i = 0; i < n; i++) {
         while (q[first] == null) {
            if (first < q.length - 1) first++;
            else first = 0;
            }
         temp[i] = q[first];
         if (first < q.length - 1) first++;
         else first = 0;
      }
      q = temp;
      first = 0;
      last = n;
      

   }
   
   public void enqueue(Item item) {         // add the item
      if (n == q.length) resize(2 * q.length);  // double array size in needed
      q[last++] = item;                         // add item
      if (last == q.length) last = 0;           // wrap-around
      n++;
   }
   
   public Item dequeue() {                  // remove and return a random item
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      
      // possible solution to reduce calls to 'StdRandom';
      // but will this still be uniformly at random?
      // Is it necessary to have an parameter argument that
      // includes only array indices that hold items?
      
      int num = StdRandom.uniform(q.length);
      
      // Maybe the below is what is 'not constant'.
      // Without it, null could be returned; problem? Yes...
      
      // Don't know how to fix the returning of 'null'; @#%$!
      // Ditching the Permutation modification below:
      
//      // Permutation now has a filter for a return of 'null'.
//      // And nothing changes in the array if null is returned.
      
//      if (q[num] == null) return null;
      
      // Reinstating this filter; now with the resize() working correctly.
      while (q[num] == null) {
         if (num < q.length - 1) num++;
         else num = 0;
      }
      
      Item item = q[num];
      q[num] = null;      // to avoid loitering
      n--;
      if (first == num) first++;
      if (first == q.length) first = 0;   // wrap-around
      while (q[first] == null && !this.isEmpty()) {
         first++;
         if (first == q.length) first = 0;   // wrap-around
      }
      // shrink array size if necessary
      if (n > 0 && n == q.length/4) resize(q.length/2);
      return item;      
   }
   
   public Item sample() {                   // return a random item (but do not remove it)
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      return q[StdRandom.uniform(n)];
   }
   
   public Iterator<Item> iterator() {       // return an independent iterator over items in random order
      return new ArrayIterator();
   }
   
   private class ArrayIterator implements Iterator<Item> {  // changed to private
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
   
   public static void main(String[] args) { // unit testing (optional)
      RandomizedQueue<String> rand = new RandomizedQueue<String>();
      rand.enqueue("a");
      rand.enqueue("b");
      rand.enqueue("c");
      rand.enqueue("d");
//      for (int x = 0; x < 10; x++) {
//         StdOut.print(rand.sample() + " ");
//      }
      while (!rand.isEmpty()) {
         StdOut.println(rand.dequeue());
      }
   }
}





