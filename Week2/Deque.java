import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> { //implements Iterable<Item> {
    
   private int size = 0;
   private Node first, penult, last;
   
   // inner class
   private class Node {
      Item item;
      Node next;
   }
   
   // construct an empty deque
//   public Deque() {
//      Deque test = new Deque();
//   }
    
   // is the deque empty?
   public boolean isEmpty() {
      return first == null;
   }
    
   // return the number of items on the deque
   public int size() {
      return size;
   }
    
   // add the item to the front (push)
   public void addFirst(Item item) {
      Node oldfirst = first;
      first = new Node();
      first.item = item;
      first.next = oldfirst;
      size++;
   }
    
   // add the item to the end (enqueue)
   public void addLast(Item item) {
      Node oldlast = last;
      last = new Node();
      last.item = item;
      last.next = null;
      if (isEmpty()) first = last;
      else oldlast.next = last;
      size++;
   }
    
   // remove and return the item from the front (dequeue)
   public Item removeFirst() {
      Item item = first.item;
      first = first.next;       
      if (isEmpty()) last = null;
      size--;
      return item;
   }
    
   // remove and return the item from the end
   public Item removeLast() {
      Item item = last.item;
      first = first.next;
      if (isEmpty()) last = null;
      size--;
      return item;
   }
    
   // return an iterator over items in order from front to end
//   public Iterator<Item> iterator() {
//        
//   }
    
   // unit testing (optional)
   public static void main(String[] args) {
       Deque test = new Deque();
       StdOut.println("Deque empty? " + test.isEmpty());
       StdOut.println("Deque size:  " + test.size());
   }
}
