import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
   private int n;
   private Node first;
   private Node last;
   
   // inner class
   private class Node {
      private Item item;
      private Node next;
      private Node previous;
   }
   
   // construct an empty deque
   public Deque() {
      first = null;
      last = null;
      n = 0;
      assert check();
   }
    
   // is the deque empty?
   public boolean isEmpty() {
      return first == null && last == null;
   }
    
   // return the number of items on the deque
   public int size() {
      return n;
   }
    
   // add the item to the front (push)
   public void addFirst(Item item) {
      if (isNull(item)) throw new IllegalArgumentException("Cannot add null item.");
      // adding a reference to the ever-changing last node
      if (this.isEmpty()) {
         Node oldfirst = first;
         first = new Node();
         first.item = item;
         last = first;
         first.next = oldfirst;
         n++;
         assert check();
      }
      else {
         // regular instructions for adding to head
         Node oldfirst = first;
         first = new Node();
         first.item = item;
         first.next = oldfirst;
         oldfirst.previous = first;
         // instruction below is a mistake
//         if (this.size() == 2) last.previous = first;
         n++;
         assert check();
      }
   }
    
   // add the item to the end (enqueue)
   public void addLast(Item item) {
      // adding reference for the ever-changing first node
      if (this.isEmpty()) {
         Node oldlast = last;
         last = new Node();
         last.item = item;
         first = last;
         last.previous = oldlast;
         n++;
         assert check();
      }
      else {
         // regular instructions for adding to tail
         Node oldlast = last;
         last = new Node();
         last.item = item;
         last.previous = oldlast;
         oldlast.next = last;
         // instruction below is a mistake
//         if (this.size() == 2) last.previous = first;
         n++;
         assert check();
      }
   }      
//      // original-ish version
//      if (isNull(item)) throw new IllegalArgumentException("Cannot add null item.");
//      Node oldlast = last;
//      last = new Node();
//      last.item = item;
//      last.next = oldlast;
//      if (isEmpty()) first = last;
//      else oldlast.previous = last;
//      n++;
//      assert check();
// 
   
   // is client argument 'null'?
   public boolean isNull(Item item) {
      return item == null;
   }
   
   // remove and return the item from the front (dequeue)
   public Item removeFirst() {
      if (isEmpty()) throw new NoSuchElementException("Deque is empty.");
      Item item = first.item;
      if (first == last) {
         first = null;
         last = null;
      }
      else { first = first.next; }      
//      if (isEmpty()) last = null;
      n--;
      assert check();
      return item;
   }
    
   // remove and return the item from the end
   public Item removeLast() {
      if (isEmpty()) throw new NoSuchElementException("Deque is empty.");
      Item item = last.item;
      if (last == first) {
         last = null;
         first = null;
      }
      else { last = last.previous; }
//      if (isEmpty()) first = null;
      n--;
      assert check();
      return item;
   }

   // Returns string representation of queue, in order, separate by spaces
   public String toString() {
      StringBuilder s = new StringBuilder();
      for (Item item : this)
         s.append(item + " ");
      return s.toString();
   }   
   
   // check internal invariants
   private boolean check() {
      if (n < 0) {
         return false;
      }
      else if (n == 0) {
         if (first != null) return false;
         if (last != null) return false;
      }
      else if (n == 1) {
         if (first == null || last == null) return false;
         if (first != last) return false;
         if (first.next != null) return false;
      }
      
      else {
         if (first == null || last == null) return false;
         if (first == last)      return false;
         if (first.next == null) return false;
         if (last.next  != null) return false;
         
         // check internal consistency of instance variable n
         int numberOfNodes = 0;
         for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
         }
         if (numberOfNodes != n ) return false;
         
         // check internal consistency of instance variable last
         Node lastNode = first;
         while (lastNode.next != null) {
            lastNode = lastNode.next;
         }
         if (last != lastNode) return false;
      }
      
      return true;
   }  
   
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator() { return new ListIterator(); }
      
   private class ListIterator implements Iterator<Item> {
      private Node current = first;
      public boolean hasNext() { return current != null; }
      public void remove() { throw new UnsupportedOperationException("Operation not supported."); }
      public Item next() {
         if (!hasNext()) throw new NoSuchElementException("No item.");
         Item item = current.item;
         current = current.next;
         return item;
      }
   }
   
//   // Unit tests for LinkedQueue data type
//   // @param args the command-line arguments
//   public static void main(String[] args) {
//      LinkedQueue<String> queue = new LinkedQueue<String>();
//      while (!StdIn.isEmpty()) {
//         String item = StdIn.readString();
//         if (!item.equals("-"))
//            queue.enqueue(item);
//         else if (!queue.isEmpty())
//            StdOut.print(queue.dequeue() + " ");
//      }
//      StdOut.println("(" + queue.size() + " left on queue)");
//      
//   }   
   
//   unit testing (optional)
   public static void main(String[] args) {
      Deque<Integer> test = new Deque<Integer>();
      StdOut.println("Deque empty? " + test.isEmpty());
      for (int x = 0; x < 10; x++) {
         test.addFirst(x);
      }
      StdOut.println(test.removeFirst());
      StdOut.println("Deque empty? " + test.isEmpty());
      StdOut.println("Deque size:  " + test.size());
      while (!test.isEmpty()) {
         StdOut.println(test.removeLast() + " ");
         StdOut.print("Deque size: " + test.size());
         StdOut.println("  Deque empty? " + test.isEmpty());
      }
   }
}
