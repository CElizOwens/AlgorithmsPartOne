import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
   public static void main(String[] args) {
      int k = Integer.parseInt(args[0].substring(0, 1));
      int n = 0;
      RandomizedQueue<String> queue = new RandomizedQueue<String>();
      while (!StdIn.isEmpty()) {
         String item = StdIn.readString();
         queue.enqueue(item);
      }
      while (n < k) {
         // Not sure if this will be acceptable
         String s = queue.dequeue();
         if (s != null) {
            StdOut.println(s);
            n++;
         }
      }
         
         
      // Original code, below, for this while loop.
         
//         StdOut.println(queue.dequeue());
//         n++;
//      }
   }
}