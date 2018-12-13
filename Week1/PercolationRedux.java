import edu.princeton.cs.algs4.WeightedQuickUnionUF;

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

public class PercolationRedux {
  
  private int n;
  private int[] grid;
  private int[] state;
  private int side;
  private boolean percolates = false;
  
  // Constructor
  public Percolation(int n)
  // create n-by-n grid, with all sites blocked
  {
      WeightedQuickUnionUF grid = new WeightedQuickUnionUF(n * n);
      side = n;
      state = new int[n * n];
      for (int x = 0; x < (n * n); x++) {
          state[x] = 0;  // blocked=0, open=1, full=2
    }
  }
          
  // converts 'row, col' to index for 'state' and 'grid'
  private int index(int x, int y) {
      // return sitenum(x, y) - 1;
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
  
  
  //right
  private int right(int x, int y) {
      return index(x, y) + 1;
  }
  
  //left
  private int left(int x, int y) {
      return index(x, y) - 1;
  }
  
  //top
  private int top(int x, int y) {
      return index(x, y) - n;
  }
  
  //bottom
  private int bottom(int x, int y) {
      return index(x, y) + n;
  }
  
  // open site (row, col) if it is not open already
  public void open(int row, int col) {
      state[index(row, col)] = 1;   // assigning '1', which means 'open'
      union_conditionals(row, col);
      
      // *** THIS IS NEXT. ***
      
      // update states of new component
  }   // Need extra code to designate as full...?
  
  //unite all adjacent open sites
  private void tryunite(int site, int adj) {
      if (isOpen(siterow(adj), sitecol(adj))) {
          union(site, adj);
          fill(site, adj);
      }
  }
  
  // update appropriate site states to 'full'
  private void fill(int site, int adj) {
     if (state[site] == 2 ^ state[adj] == 2) {
         for (int x = 0; x < grid.length; x++) {
             if (grid.find(x) == grid.find(site) && state[x] < 2) {
                 state[x] = 2;
                 if (percolates == false && x >= n * (n - 1)) {
                         percolates = true;
                 }
             }
         }
     }
  }
  
  // find possible unions per site's position
  private void union_conditionals(int row, int col) {
      int orig = index(row, col);
      if (col < side) {
          tryunite(orig, right(row, col));
      }
      if (col > 1) {
          tryunite(orig, left(row, col));
      }
      if (row < side) {
          tryunite(orig, bottom(row, col));
      }
      if (row > 1) {
          tryunite(orig, top(row, col));
      }
  }
      
  // is site (row, col) open?
  public boolean isOpen(int row, int col) {
      return state[index(row, col)] > 0;
  }
  
  // is site (row, col) full?
  public boolean isFull(int row, int col) {
      return state[index(row, col)] == 2;
  }
  
  // returns number of open sites
  public int numberOfOpenSites() {
      int n = state.length;
      int numopen = 0;
      for (int x = 0; x < n; x++) {
          if (state[x] > 0) {
              numopen++;
          }
      }
      return numopen;
  }
  
  public boolean percolates() {            // does the system percolate?
      return percolates;
  }

  public static void main(String[] args) {  // test client (optional)
  }
}




 
  

//  
//  // converts 'row, col' to site number between 1 and n
//  private int sitenum(int x, int y) {
//      return side * x + y - side;
//  }