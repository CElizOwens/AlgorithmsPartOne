import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

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
         StdOut.println(queue.dequeue());
         n++;
      }
   }
}