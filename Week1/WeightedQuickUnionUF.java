/**
 *  The WeightedQuickUnionUF class represents a union–find data type
 *  (also known as the disjoint-sets data type).
 *  It supports the union and find operations,
 *  along with a connected operation for determining whether
 *  two sites are in the same component and a count operation that
 *  returns the total number of components.
 *  
 *  The union–find data type models connectivity among a set of n
 *  sites, named 0 through n–1.
 *  The is-connected-to relation must be an 
 *  equivalence relation:
 *  
 *  Reflexive: p is connected to p.
 *  Symmetric: If p is connected to q,
 *       then q is connected to p.
 *  Transitive: If p is connected to q
 *       and q is connected to r, then
 *       p is connected to r.
 *  
 *  
 *  An equivalence relation partitions the sites into
 *  equivalence classes (or components). In this case,
 *  two sites are in the same component if and only if they are connected.
 *  Both sites and components are identified with integers between 0 and
 *  n–1. 
 *  Initially, there are n components, with each site in its
 *  own component.  The component identifier of a component
 *  (also known as the root, canonical elent, leader,
 *  or set representative) is one of the sites in the component:
 *  two sites have the same component identifier if and only if they are
 *  in the same component.
 *  
 *  union(p, q) adds a
 *  connection between the two sites p and q.
 *  If p and q are in different components,
 *  then it replaces
 *  these two components with a new component that is the union of
 *  the two.
 *  
 *  find(p) returns the component
 *  identifier of the component containing p.
 *  
 *  connected(p, q)
 *  returns true if both p and q
 *  are in the same component, and false otherwise.
 *  
 *  count() returns the number of components.
 *  
 *  
 *  The component identifier of a component can change
 *  only when the component itself changes during a call to
 *  union—it cannot change during a call
 *  to find, connected, or count.
 *  
 *  This implentation uses weighted quick union by size (without path compression).
 *  Initializing a data structure with n sites takes linear time.
 *  Afterwards, the union, find, and connected
 *  operations  take logarithmic time (in the worst case) and the
 *  count operation takes constant time.
 *  For alternate implentations of the same API, see
 *  UF, QuickFindUF, and QuickUnionUF.
 *
 *  
 *  For additional documentation, see (https://algs4.cs.princeton.edu/15uf) Section 1.5 of
 *  Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne.
 *
 *  author Robert Sedgewick
 *  author Kevin Wayne
 */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components

    /**
     * Initializes an empty union–find data structure with n sites
     * 0 through n-1. Each site is initially in its own 
     * component.
     *
     * n represents the number of sites
     * Constructor throws IllegalArgumentException if n < 0
     */
    public WeightedQuickUnionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Returns the number of components (between 1 and n)
     */
    public int count() {
        return count;
    }
  
    /**
     * Returns the component identifier for the component containing site p.
     *
     * p the integer representing one object
     * throws IllegalArgumentException unless 0 <= p < n
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));  
        }
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * p the integer representing one site
     * q the integer representing the other site
     * return true if the two sites p and q are in the same component;
     *         false otherwise
     * throws IllegalArgumentException unless
     *         both 0 <= p < n and 0 <= q < n
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site p with the 
     * the component containing site q.
     *
     * p the integer representing one site
     * q the integer representing the other site
     * throws IllegalArgumentException unless
     *         both 0 <= p < n and 0 <= q < n
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    /**
     * Reads in a sequence of pairs of integers (between 0 and n-1) from standard input, 
     * where each integer represents some object (site);
     * if the sites are in different components, merge the two components
     * and print the pair to standard output.
     *
     * args the command-line arguments
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

}
