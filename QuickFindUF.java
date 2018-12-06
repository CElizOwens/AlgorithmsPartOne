public class QuickFindUF
{
    private int[] id;
    
    public QuickFindUF(int N)
    {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
    
    
    /* 0 1 2 3 4 5 6 7 8 9  = i, ps and qs
     * 0 8 8 8 4 5 6 7 8 9  = id[i]
     */
        
    public boolean connected(int p, int q)
    {  return id[p] == id[q];  }
    
    /* true or false:
     * Does the value that id[p] (the position) holds
     * equal value that id[q] holds?
       if p = 1, q = 3, the values are 1 and 3, respectively */
    
    
    public void union(int p, int q ) /* change p's id to the same as q's */
    {
        //int[] groups = this.id;
        
        int pid = id[p]; /* pid = 1, 2, 3, 5 */
        int qid = id[q]; /* qid = 3, 3, 8, 8 */
        for (int i = 0; i < id.length; i++)
            if (id[i] == pid) id[i] = qid;
          /* when id[i] = 6, change id[i] to 3 */
    }
    
    /* pid = 3; id[3]
     * if id[3] holds qid, then make id[3] hold qid
     */
}