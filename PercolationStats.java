import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

private final int trials;
private final double[] thresharray;
private final static double conf = 1.96;
private final double mean;
private final double stddev;
// private double opensites;

   public PercolationStats(int n, int trials) {  // perform trials independent experiments on an n-by-n grid
       if (n <= 0) {
           throw new IllegalArgumentException(n + "is less than 1. Invalid.");
       }
       if (trials <= 0) {
           throw new IllegalArgumentException(trials + "is less than 1. Invalid.");
       }
       this.trials = trials;
       int row, col;
       final double numsites = n * n;
       thresharray = new double[trials]; // array of all percolation threshholds
       for (int x = 0; x < trials; x++) {
           Percolation sample = new Percolation(n);
           while (!sample.percolates()) {
               row = StdRandom.uniform(n) + 1;
               col = StdRandom.uniform(n) + 1;
               if (!sample.isOpen(row, col)) {
                   sample.open(row, col);
               }
           }
           // for testing
//           opensites = sample.numberOfOpenSites();
           thresharray[x] = sample.numberOfOpenSites() / (numsites);
//           StdOut.println("open sites and threshhold = " +
//                          opensites + ", " + thresharray[x]);
       }
       mean = StdStats.mean(thresharray);
       stddev = StdStats.stddev(thresharray);
   }
   
   // sample mean of percolation threshold
   public double mean() {
       return mean;
   }
   
   // sample standard deviation of percolation threshold
   public double stddev() {
       return stddev;
   }
   
   // low  endpoint of 95% confidence interval
   public double confidenceLo() {
//       StdOut.println("trials                  = " + trials);
       return mean - conf * stddev / Math.sqrt(trials);
   }
   
   // high endpoint of 95% confidence interval
   public double confidenceHi() {
//       StdOut.println("trials                  = " + trials);
       return mean + conf * stddev / Math.sqrt(trials);
   }

   
   // test client
   public static void main(String[] args) {
       // Still don't understand how to fix:
   /* [WARN] PercolationStats.java:1: The number (0) of calls to 
    * 'Integer.parseInt()' must equal the number (2) of integer 
    * command-line arguments. [CommandLineArgument]
    */
       
       int n = StdIn.readInt(); // grid of size (n * n)
       int t = StdIn.readInt(); // number of trials
       
       PercolationStats run = new PercolationStats(n, t);
       StdOut.println("mean                    = " + run.mean());
       StdOut.println("stddev                  = " + run.stddev());
       StdOut.println("95% confidence interval = ["
                          + run.confidenceLo() + ", " + run.confidenceHi() + "]");
   }
}