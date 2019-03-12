import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class LinkedStack<Item> implements Iterable<Item> {
   private int n;         // size of stack
   private Node first;    // head of stack
   
   // helper linked list class
   private class Node {
      private Item item;
      private Node next;
   }
   
   // Intitializes an empty stack
   public LinkedStack() {
      first = null;
      n = 0;
      assert check();
   }
   
   // Is this stack empty?
   public boolean isEmpty() {
      return first == null;
   }
   
   // Returns number of items in this stack
   public int size() {
      return n;
   }
   
   // Adds item to head of stack
   public void push(Item item) {
      Node oldfirst = first;
      first = new Node();
      first.item = item;
      first.next = oldfirst;
      n++;
      assert check();
   }
   
   // Returns (and removes) item most recently added to stack (head)
   public Item pop() {
      if (isEmpty()) throw new NoSuchElementException("Stack underflow");
      Item item = first.item;    // save item to return
      first = first.next;        // delete first node
      n--;
      assert check();
      return item;               // return saved item
   }
   
   // Returns (without removing) item most recently added (head)
   public Item peek() {
      if (isEmpty()) throw new NoSuchElementException("Stack underflow");
      return first.item;
   }
   
   // Returns string representation of this stack (head to tail)
   public String toString() {
      StringBuilder s = new StringBuilder();
      for (Item item : this)
         s.append(item + " ");
      return s.toString();
      // The "toString()" call on the line above is a StringBuilder class method.
   }
   
   // Returns an iterator to this stack
   // Iterates through the items from head to tail
   public Iterator<Item> iterator() {
      return new ListIterator();
   }
   
   // an iterator; doesn't implement "remove()" since it's optional
   private class ListIterator implements Iterator<Item> {
      private Node current = first;
      public boolean hasNext() { return current != null; }
      public void remove() { throw new UnsupportedOperationException(); }
      
      public Item next() {
         if (!hasNext()) throw new NoSuchElementException();
         Item item = current.item;
         current = current.next;
         return item;
      }
   }
   
   // check internal invariants (???)
   private boolean check() {
      
      // check a few properties of instance variable 'first'
      if (n < 0) {
         return false;
      }
      if (n == 0) {
         if (first != null) return false;
      }
      else if (n == 1) {
         if (first == null) return false;
         if (first.next != null) return false;
      }
      else {
         if (first == null) return false;
         if (first.next == null) return false;
      }
      
      //check internal consistency of instance variable n
      int numberOfNodes = 0;
      for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
         numberOfNodes++;
      }
      if (numberOfNodes != n) return false;
      
      return true;
   }
   
   // Unit tests for LinkedStack data type
   // @param args the command-line arguments
   public static void main(String[] args) {
      LinkedStack<String> stack = new LinkedStack<String>();
      while(!StdIn.isEmpty()) {
         String item = StdIn.readString();
         if (!item.equals("-"))
            stack.push(item);
         else if (!stack.isEmpty())
            StdOut.print(stack.pop() + " ");
      }
      StdOut.println("(" + stack.size() + " left on stack)");
   }
}
   
   
   
   