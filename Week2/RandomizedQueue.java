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
//      StdOut.println("Enqueue-ing " + item);
      if (item == null) throw new IllegalArgumentException("Argument cannot be null");
      if (n == q.length) resize(2 * q.length);  // double array size if needed
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
      
      int num = StdRandom.uniform(n);
//      StdOut.println("n: " + n + " and Random num: " + num);
      // Maybe the below is what is 'not constant'.
      // Without it, null could be returned; problem? Yes...
      
      // Don't know how to fix the returning of 'null'; @#%$!
      // Ditching the Permutation modification below:
      
//      // Permutation now has a filter for a return of 'null'.
//      // And nothing changes in the array if null is returned.
      
//      if (q[num] == null) return null;
      
      // No longer using this filter; now with the resize() working correctly.
//      while (q[num] == null) {
//         if (num < q.length - 1) num++;
//         else num = 0;
//      }
   
      Item item = q[num];
//      StdOut.println("Item in q[" + num + "] : " + item);
      q[num] = q[n - 1];   // to fill in the gap
//      StdOut.println("q[n - 1]: " + q[n - 1] + ". Being placed in q[" + num + "]");
      q[n - 1] = null;   // to avoid loitering
      if (last == 0) last = n - 1;
      else last--;
      n--;
      if (first == num) first++;
      if (first == q.length) first = 0;   // wrap-around
      
      // Won't need this while loop now that there won't be gaps.
      
//      while (q[first] == null && !this.isEmpty()) {
//         first++;
//         if (first == q.length) first = 0;   // wrap-around
//      }
      // shrink array size if necessary
      if (n > 0 && n == q.length/4) resize(q.length/2);
      return item;      
   }
   
   public Item sample() {                   // return a random item (but do not remove it)
      if (isEmpty()) throw new NoSuchElementException("Queue underflow");
      return q[StdRandom.uniform(n)];
   }
   
   public Iterator<Item> iterator() {       // return an independent iterator over items in random order
      // **could do 'q = q[0:n]' instead of the change in 'public Item next()'?
      // **the above would change 'q' itself, though...
      return new ArrayIterator();
   }
   
   private class ArrayIterator implements Iterator<Item> {  // changed to private
      private Item[] r;
      public ArrayIterator() {               // construct a copy of randomized queue
         StdRandom.shuffle(q, 0, n);
         r = (Item[]) new Object[n];
         for (int i = 0; i < n; i++)
            r[i] = q[i];
      }
      private int i = 0;
      public boolean hasNext() { return i < n; }
      public void remove() { throw new UnsupportedOperationException(); }
      
      public Item next() {
         if (!hasNext()) throw new NoSuchElementException();
         Item item = r[(i + first) % n];  // **changed '% q.length' to '% n'
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





