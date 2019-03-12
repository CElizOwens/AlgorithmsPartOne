import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStack<Item> implements Iterable<Item> {
   private Item[] a;        // array of items
   private int n;           // number of elements on stack
   
   // Initializes an empty stack
   public ResizingArrayStack() {
      a = (Item[]) new Object[2];
      n = 0;
   }
   
   // Is this stack empty?
   public boolean isEmpty() {
      return n == 0;
   }
   
   // Returns number of items on stack
   public int size() {
      return n;
   }
   
   // Resize the underlying array
   private void resize(int capacity) {
      assert capacity >= n;
      
      // textbook implementation
      Item[] temp = (Item[]) new Object[capacity];
      for (int i = 0; i < n; i++) {
         temp[i] = a[i];
      }
      a = temp;
      
      // alternative implementation:
      // a = java.util.Arrays.copyOf(a, capacity);
   }
   
   // Adds item to this stack
   public void push(Item item) {
      if (n == a.length) resize(2*a.length);  // double array size if necessary
      a[n++] = item;
   }
   
   // Removes and returns most recently added item from stack
   public Item pop() {
      if (isEmpty()) throw new NoSuchElementException("Stack underflow");
      Item item = a[n - 1];
      a[n - 1] = null;       // to avoid loitering
      n--;
      // shrink array size if necessary
      if (n > 0 && n == a.length/4) resize(a.length/2);
      return item;
   }
   
   // Returns (without removing) most recently added item on stack
   public Item peek() {
      if (isEmpty()) throw new NoSuchElementException("Stack underflow");
      return a[n - 1];
   }
   
   // Returns an iterator to this stack that iterates items in LIFO order
   public Iterator<Item> iterator() {
      return new ReverseArrayIterator();
   }
   
   // an iterator; doesn't implement 'remove()' since it's optional
   private class ReverseArrayIterator implements Iterator<Item> {
      private int i;
      
      public ReverseArrayIterator() {
         i = n - 1;
      }
      
      public boolean hasNext() {
         return i >= 0;
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
      
      public Item next() {
         if (!hasNext()) throw new NoSuchElementException();
         return a[i--];
      }
   }
   
   // Unit tests for ResizingArrayStack data type
   // @param args the command-line arguments
   public static void main(String[] args) {
      ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
      while (!StdIn.isEmpty()) {
         String item = StdIn.readString();
         if (!item.equals("-")) stack.push(item);
         else if (!stack.isEmpty()) StdOut.print(stack.pop() + " ");
      }
      StdOut.println("(" + stack.size() + " left on stack)");
   }
}
   
   
   
   