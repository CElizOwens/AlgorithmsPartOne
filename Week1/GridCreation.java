import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
    
public class GridCreation {
  
  private int[][] sitesarray;

  public GridCreation(int n)  // Constructor
  // create n-by-n grid, with all sites blocked
//  {
    
//  }
  
  
    
    
    
    
    
    
    
    
    
    
  {
    sitesarray = new int[n*n][2];
    // initialarray = new int[n*n];
    WeightedQuickUnionUF initialarray = new WeightedQuickUnionUF(n*n);
    int[] xyarray = new int[2];
    int a, x, y;  //a is a given site's cardinal number
    
    for (int i = 0; i < (n*n); i++)
    {
      a = initialarray.find(i) + 1;
      y = a % n;
      if (a <= n) x = 1;
      else if (a/(double) n > a/n) x = a/n + 1;
      else x = a/n;
      xyarray[0] = x;
      xyarray[1] = y;
      sitesarray[i] = xyarray;
    }
    this.sitesarray = sitesarray;
    StdOut.println(this.sitesarray);
  }
  
  public static void main(String[] args)
  {
    int n = StdIn.readInt();
    GridCreation mygrid = new GridCreation(n);
    // StdOut.println(mygrid.sitesarray);
  } //Location of object is output? but compilable...
}



//public class WeightedQuickUnionUF
//{
//  
//  private int[] id;
//  private int[] sz;
//  
//  
//  public QuickUnionUF(int N)  // Constructor
//  /* set id of each object to itself
//   * N array accesses */
//  { 
//    id = new int[N];
//    for (int i = 0; i < N; i++)
//      id[i] = i;
//    sz = new int[N];
//    for (int i = 0; i < N; i++)
//      sz[i] = 1;
//    
//  }
//  
//  
//  private int root(int i)
//  /* chase parent pointer until reach root
//   * depth of i array accesses */
//  {
//    while (i != id[i])
//      id[i] = id[id[i]];
//      /* Makes every other node in path point
//       * to it's grandparent, halving path length. */
//      i = id[i];
//    return i;
//  }
//  
//  
//  public boolean connected(int p, int q)
//  /* check if p and q have same root
//   * depth of p and q array accesses */
//  {
//    return root(p) == root(q);
//  }
//  
//  
//  public void union(int p, int q)
//  /* change root of p to point to root of q
//   * depth of p and q array accesses */
//  {
//    int i = root(p);
//    int j = root(q);
//
//     if (i == j) return;
//     if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
//     else [ id[j] = i; sz[i] += sz[j]; }
//     
//  }
//}