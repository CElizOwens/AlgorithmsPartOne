import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   public PercolationStats(int n, int trials) {  // perform trials independent experiments on an n-by-n grid
       private double[] thresharray = new double[n * n]; // array of all percolation threshholds
       private double[] threshsum = 0;
       for (int x = 0; x < trials; x++) {
           Percolation sample = new Percolation(n);
           double percthresh = 0;
           while (sample.percolates() == false) {
               sample.open(StdRandom.uniform(n) + 1, StdRandomuniform(n) + 1);
           }
           percthresh = sample.numberOfOpenSites() / (n * n);
           thresharray[x] = percthresh;
           threshsum += percthresh;
       }
       // The below are not necessary inside of the constructor.
       // The test client will call these, itself. (?)
//       mean(threshsum, trials);
//       stddev();
//       confidenceLo();
//       confidenceHi();
   }
   
   public double mean() {                        // sample mean of percolation threshold
       return threshsum / t;
   }
   
   public double stddev() {                      // sample standard deviation of percolation threshold
       double z = mean();
       double square = 0;
       for (int x = 0; x > t; x++) {
           square += x - z;
       }
       
       // *** STOPPED HERE ***
       return // square root of 'square';
              // 'square' represents 's squared' in the assignment instructions
           
   }
   
   public double confidenceLo() {                // low  endpoint of 95% confidence interval
       
   }
   
   public double confidenceHi() {                // high endpoint of 95% confidence interval
           
   }

   public static void main(String[] args) {       // test client (described below)
       int n = StdIn.readInt(); // grid size (n * n)
       int t = StdIn.readInt(); // number of trials

       PercolationStats run = new PercolationStats(n, t);
       StdOut.println("Mean: " + run.mean());
       StdOut.println("Standard deviation: " + run.stddev());
       StdOut.println("Confidence (low): " + run.confidenceLo());
       StdOut.println("Confidence (hi): " + run.confidenceHi());
   }
}