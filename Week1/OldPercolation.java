import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class OldPercolation {

  private WeightedQuickUnionUF ufobject;
  
  public Percolation(int n)  // Constructor
  // create n-by-n grid, with all sites blocked
  {
    ufobject = WeightedQuickUnionUF(n*n);
  }
  
  private int xytositenum(int x, int y)
  {
    int sitenum = n*x + y - n;
    return sitenum;
  }
  
  public void open(int row, int col)    // open site (row, col) if it is not open already
  {
    int sitetoopen = xytositenum(row, col);
    
  }
  
  
  public boolean isOpen(int row, int col)  // is site (row, col) open?
  public boolean isFull(int row, int col)  // is site (row, col) full?
  public     int numberOfOpenSites()       // number of open sites
  public boolean percolates()              // does the system percolate?

  public static void main(String[] args)   // test client (optional)
}



