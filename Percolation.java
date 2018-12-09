/** Need an additional private attribute for recording
  * the state of a site as full. Also, add to 'open()'
  * method OR create another private method to assign
  * 'full' to said private attribute of an open site.
  * Separate arrays for 'open' and 'full' best?
  * 'state' array for 'closed' vs 'open'; and
  * 'perc' array for 'full' vs 'not full'?
  * 
  * Or is simply having 'open' equal '1' and/or '2' ok...
  * while 'full' only equals '2'?
  * Which answer is actually desired for 'isOpen()'?
  * 
  * Ok, just found the answer:
  * There are 'empty open' sites and 'full open' sites.
  * So, 'open' > 0; 'full' == 2;
  */

/** Ideas from last night:
  * Need private methods that reference adjacent sites by index.
  * Can then use these references to assign 'full' state as needed.
  * Need a function that change a site (or component) from 'open' to 'full'.
  * How to identify entire components? Using their roots.
  * 
  * Above all ---
  * Need to understand the order of processing... that is:
  * 1. a random site gets opened; send site as parameter to 'fill()'
  * 2. if site is in row 1, set state == 2 ('full')
  * 3. check if any adjacent sites are open
  * 3a. unite site with any adjacent open sites/component
  * 4. after each unison with an adjacent open site:
  *    a. if site p's state == 2 and site q's state < 2:
  *       set all sites with site p/q's root to state == 2.
  *           aa. while setting all sites of same root to state == 2,
  *               if site's state already equals 2, pass.
  *    b. else if site q's state == 2 and site p's state < 2:
  *       set site p's state == 2.
  * 
  * 5. grid percolates if any site's state in row n is equal to 2.
  * 
  */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
  private final WeightedQuickUnionUF grid;
  private boolean[] state;
  private final int side;
  private int numopen;
  private final int top;
  private final int bottom;
  
  // Constructor
  public Percolation(int n)
  // create n-by-n grid, with all sites blocked
  {
      if (n < 1) {
          throw new IllegalArgumentException(n + " is less than 1. Invalid.");
      }
      grid = new WeightedQuickUnionUF(n * n + 2);
      side = n;
      numopen = 0;
      state = new boolean[n * n + 2];
      state[n * n] = true;
      state[n * n + 1] = true;
      top = n * n;
      bottom = n * n + 1;
  }
          
  // converts 'row, col' to index for 'state' and 'grid'
  private int index(int x, int y) {
      return side * x + y - side - 1;
  }
  
  // returns site number 'row' coordinate
  private int siterow(int s) {
      return s / side + 1;
  }

  // returns site number 'col' coordinate
  private int sitecol(int s) {
      return s % side + 1;
  }
  
  
  // right
  private int right(int x, int y) {
      return index(x, y) + 1;
  }
  
  // left
  private int left(int x, int y) {
      return index(x, y) - 1;
  }
  
  // top
  private int top(int x, int y) {
      return index(x, y) - side;
  }
  
  // bottom
  private int bottom(int x, int y) {
      return index(x, y) + side;
  }
  
   private void validate(int row, int col) {
       if (row < 1 || row > side) {
           throw new IllegalArgumentException("row " + row + " is not between "
                                                  + "1 and " + side);  
       }
       if (col < 1 || col > side) {
           throw new IllegalArgumentException("col " + col + " is not between "
                                                  + "1 and " + side);
       }
   }

  
  // open site (row, col) if it is not open already
  // set state to 'true'
  public void open(int row, int col) {
      validate(row, col);
      if (!isOpen(row, col)) {
          state[index(row, col)] = true;
          if (row == 1) {
              grid.union(index(row, col), top);
          }
          if (row == side) {
              grid.union(index(row, col), bottom);
          }
          numopen++;
          unionConditionals(row, col);
      }
  }
  
  // unite all adjacent open sites
  private void tryunite(int site, int adj) {
      if (isOpen(siterow(adj), sitecol(adj))) {
          grid.union(site, adj);
      }
  }
  
  // Beginning of possible solution for backwash.
  // tests 16 - 18 failed, backwash bonus question failed.
  // This part to be implemented in 'tryunite()':
                
/* b = bottom; a = component; First time bottom vsite is touched.
 * For component a <= 1, in row = 'side':
 * 'union(a, b);' Sets bottom virt site's root to a's root.
 * Reset b's root to b
 * 
 */
              
  
  // find possible unions per site's position
  private void unionConditionals(int row, int col) {
      int orig = index(row, col);
      if (row < side) {
          tryunite(orig, bottom(row, col));
      }
      if (row > 1) {
          tryunite(orig, top(row, col));
      }
      if (col < side) {
          tryunite(orig, right(row, col));
      }
      if (col > 1) {
          tryunite(orig, left(row, col));
      }
  }
  
  // connected
  private boolean connected(int a, int b) {
      return this.grid.connected(a, b);
  }
  
  // is site (row, col) open?
  public boolean isOpen(int row, int col) {
      validate(row, col);
      return state[index(row, col)];
  }
  
  // is site (row, col) full?
  public boolean isFull(int row, int col) {
      validate(row, col);
      return grid.connected(index(row, col), top);
  }
  
  // returns number of open sites
  public int numberOfOpenSites() {
      return numopen;
  }
  
  public boolean percolates() {            // does the system percolate?
      return grid.connected(bottom, top);
  }

  public static void main(String[] args) {  // test client (optional)
      Percolation test = new Percolation(5);
      test.open(1, 3);
      test.open(1, 2);
      test.open(1, 1);
      test.open(2, 1);
      StdOut.println("Opened four sites. Percolates? " + test.percolates());
      test.open(2, 2);
      test.open(2, 3);
      StdOut.println("Connected:");
      StdOut.println("0 and 2: " + test.connected(0, 2)); // first row
      StdOut.println("5 and 7: " + test.connected(5, 7)); // second row
      StdOut.println("2 and 7: " + test.connected(2, 7)); // third col
      StdOut.println("(1, 1) open? " + test.isOpen(1, 1));
      StdOut.println("(1, 1) full? " + test.isFull(1, 1));
      StdOut.println("(2, 1) open? " + test.isOpen(2, 1));
      StdOut.println("(2, 1) full? " + test.isFull(2, 1));
      StdOut.println("(1, 3) full? " + test.isFull(1, 3));
      StdOut.println("(2, 3) full? " + test.isFull(2, 3));
      // bottom() and top() now uniting/connecting!!
      // open full sites getting recorded as filled!!
      StdOut.println("Nothing in row 5 open, yet:");
      StdOut.println("(5, 5) open? " + test.isOpen(5, 5));
      StdOut.println("(5, 5) full? " + test.isFull(5, 5));
      StdOut.println("Opening (3, 3) (3, 4) (4, 4) (5, 4).");
      test.open(3, 3);
      test.open(3, 4);
      test.open(4, 4);
      test.open(5, 4);
      StdOut.println("Opened (3, 3) (3, 4) (4, 4) (5, 4)");
      StdOut.println("Percolates? " + test.percolates());
      StdOut.println("Number open sites: " + test.numberOfOpenSites());
      test.open(5, 0);
  }
}