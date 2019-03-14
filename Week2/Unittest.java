import edu.princeton.cs.algs4.StdOut; 

public class Unittest {
   public static void main(String[] args) {
      RandomizedQueue<Integer> rand = new RandomizedQueue<Integer>();
      rand.enqueue(0);
      rand.enqueue(1);
      rand.enqueue(2);
      rand.enqueue(3);
//      rand.enqueue(4);
//      rand.enqueue(5);
//      rand.enqueue(6);
      for (Integer b : rand) {
         for (Integer y : rand) {
            StdOut.print(y);
         }
         StdOut.println();
      }
      StdOut.println();
      for (Integer c : rand) StdOut.print(c);
      StdOut.println();
      for (Integer d : rand) StdOut.print(d);
      
   }
   
      
//      StdOut.println("Empty? " + rand.isEmpty()); // true
//      StdOut.println("Size? " + rand.size()); // 0
//      StdOut.println("Enqueue-ing integer 48.");
//      rand.enqueue(48);
//      StdOut.println("Dequeue: " + rand.dequeue()); // 48
//      StdOut.println("Size? " + rand.size()); // 0
//      StdOut.println("Enqueue-ing integer 18.");
//      rand.enqueue(18);
//      StdOut.println("Enqueue-ing integer 25.");
//      rand.enqueue(25);
//      StdOut.println("Size? " + rand.size());
//      StdOut.println("Dequeue: " + rand.dequeue());
//      StdOut.println("Dequeue: " + rand.dequeue());
//      StdOut.println("Enqueue-ing integer 10.");
//      rand.enqueue(10);
//      StdOut.println("Size? " + rand.size());
//      StdOut.println("Dequeue: " + rand.dequeue());
//      StdOut.println("Size? " + rand.size());

      
      
      
//      int n = 0;
//      Deque<Integer> ascend = new Deque<Integer>();
//      while (n < 10) {
//         ascend.addFirst(n);
//         n++;
//      }
//      StdOut.println("Empty? " + ascend.isEmpty());
//      StdOut.println("Size: " + ascend.size());
//      StdOut.println(ascend.removeFirst());
//      StdOut.println(ascend.removeFirst());
//      ascend.addLast(9);
//      StdOut.println(ascend.removeLast());
//      StdOut.println("Size: " + ascend.size());
//      
//      int x = 0;
//      Deque<Integer> descend = new Deque<Integer>();
//      while(x < 11) {
//         descend.addLast(x);
//         x++;
//      }
//      StdOut.println(descend.isEmpty());
//      StdOut.println(descend.removeLast());
//      StdOut.println(descend.removeLast());
//      descend.addFirst(9);
//      StdOut.println(descend.removeFirst());
//   }
      
//      RandomizedQueue<String> rand = new RandomizedQueue<String>();
      
//      rand.enqueue("a");
//      rand.enqueue("b");
//      rand.enqueue("c");
//      rand.enqueue("d");
//      for (int x = 0; x < 10; x++) {
//         StdOut.print(rand.sample() + " ");
//      }
//      
//      int trials = 20;
//      for (int z = 0; z < trials; z++) {
//         rand.enqueue("a");
//         rand.enqueue("b");
//         rand.enqueue("c");
//         rand.enqueue("d");
//         StdOut.print(z + 1 + ". ");
//         while (!rand.isEmpty()) {
//            StdOut.print(rand.dequeue());
//         }
//         StdOut.println();
//      }
   
      
}