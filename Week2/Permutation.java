import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
   public static void main(String[] args) {
      int k = Integer.parseInt(args[0].substring(0, 1));
      int n = 0;
      RandomizedQueue<String> queue = new RandomizedQueue<String>();
//      while (!StdIn.isEmpty()) {
//         String item = StdIn.readString();
//         queue.enqueue(item);
//      }
      
      while (queue.size() < k) {
         String[] words = StdIn.readAllStrings();
         queue.enqueue(words[StdRandom.uniform(words.length)]);
      }
         
      
      while (n < k) {
         // This doesn't help the RandomizedQueue 'null' problem
         // when RQ gets tested alone.
//         String s = queue.dequeue();
//         if (s != null) {
//            StdOut.println(s);
//            n++;
//         }
//      }
         
         
      // Back to the original code, below, for this while loop.
         
         StdOut.println(queue.dequeue());
         n++;
      }
   }
}