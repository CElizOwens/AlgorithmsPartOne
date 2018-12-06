public class QuickUnionUF
{
  
  private int[] id;
  // private int[] sz;
  
  
  public QuickUnionUF(int N)  // Constructor
  /* set id of each object to itself
   * N array accesses */
  { 
    id = new int[N];
    for (int i = 0; i < N; i++)
      id[i] = i;
   /* sz = new int[N];
    * for (int i = 0; i < N; i++)
    * sz[i] = 1; */
    
  }
  
  
  private int root(int i)
  /* chase parent pointer until reach root
   * depth of i array accesses */
  {
    while (i != id[i])
      /* id[i] = id[id[i]];
       * Makes every other node in path point
       * to it's grandparent, halving path length. */
      i = id[i];
    return i;
  }
  
  
  public boolean connected(int p, int q)
  /* check if p and q have same root
   * depth of p and q array accesses */
  {
    return root(p) == root(q);
  }
  
  
  public void union(int p, int q)
  /* change root of p to point to root of q
   * depth of p and q array accesses */
  {
    int i = root(p);
    int j = root(q);
    /* --> For Weighted QuickUnion, add these lines of code:
     * if (i == j) return;
     * if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
     * else [ id[j] = i; sz[i] += sz[j]; }
     * --> Then remove the line below. */
    id[i] = j;
  }
}