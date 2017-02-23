import edu.princeton.cs.algs4.*;

/**
 * This Sap method use BFS to implement.
 * Using two queues alternatively, and store the visited vertices in two BSTs
 * alternatively, once we have searched the nearest one.
 * <p>
 * The idea mentioned in the above proved to be wrong, there is one problem
 * that cannot be resolved, however a new idea which is to create a new data
 * structure to store the direction of the graph turns out to contradict the
 * interface.
 * <p>
 * The idea at the moment is to store the reverse digraph, and you should
 * enter both digraphs to find the shortest path.
 * Created by qwe95 on 2/22/2017.
 */
public class SAP {
    /*  private static final int INFINITY = Integer.MAX_VALUE;
      private boolean[] marked_1;  // marked[v] = is there an s->v path?
      private int[] edgeTo_1;      // edgeTo[v] = last edge on shortest s->v path
      private int[] distTo_1;      // distTo[v] = length of shortest s->v path
      private boolean[] marked_2;  // marked[v] = is there an s->v path?
      private int[] edgeTo_2;      // edgeTo[v] = last edge on shortest s->v path
      private int[] distTo_2;      // distTo[v] = length of shortest s->v path
      private RedBlackBST vertices_1; // store the visited information of vertex v;
      private RedBlackBST vertices_2; // store the visited information of vertex v;
      private Digraph graph;


      // constructor takes a digraph (not necessarily a DAG)
      public SAP(Digraph G) {
          graph = G;
          marked_2 = new boolean[G.V()];
          distTo_2 = new int[G.V()];
          edgeTo_2 = new int[G.V()];
          marked_1 = new boolean[G.V()];
          distTo_1 = new int[G.V()];
          edgeTo_1 = new int[G.V()];
          for (int v = 0; v < G.V(); v++) {
              distTo_1[v] = INFINITY;
              distTo_2[v] = INFINITY;
          }
          vertices_1 = new RedBlackBST();
          vertices_2 = new RedBlackBST();
      }

      // do unit testing of this class
      public static void main(String[] args)

      // length of shortest ancestral path between v and w; -1 if no such path
      public int length(int v, int w) {


      }

      private
      // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
      public int ancestor(int v, int w)
      {
          Queue<Integer> q1 = new Queue<Integer>();
          marked_1[v] = true;
          distTo_1[v] = 0;
          q1.enqueue(v);
          Queue<Integer> q2 = new Queue<Integer>();
          marked_2[w] = true;
          distTo_2[w] = 0;
          q2.enqueue(w);
          while (true){
              int vertex_1 = q1.dequeue();
              vertices_1.put(vertex_1, vertex_1);
              if (vertices_2.contains(vertex_1))
              int vertex_2 = q2.dequeue();
              vertices_2.put(v, v);
              for (int w : graph.adj(v)) {
                  if (!marked[w]) {
                      edgeTo[w] = v;
                      distTo[w] = distTo[v] + 1;
                      marked[w] = true;
                      q.enqueue(w);
                  }
              }
          }
      }
      private boolean contains(int v, Queue<Integer>){

          for (int w : q2.iterator() )
          {
              if (w == v) return true;
          }
          return  false;
      }
      // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
      public int length(Iterable<Integer> v, Iterable<Integer> w)
      {

      }
      // a common ancestor that participates in shortest ancestral path; -1 if no such path
      public int ancestor(Iterable<Integer> v, Iterable<Integer> w)*/

    private static final int INFINITY = Integer.MAX_VALUE;
    /*     private boolean[] marked;  // marked[v] = is there an s->v path?
         private boolean[] reverse;  // reserve[v] = is v in the reserve graph?
         private int[] edgeTo;      // edgeTo[v] = last edge on shortest s->v path
         private int[] distTo;      // distTo[v] = length of shortest s->v path*/
    private Digraph g; // mirror graph of input digraph G
    private Digraph rg; // reversed digraph of G.
    //      private int ancestorInt;
    private int parent;
    private int parentIter;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        g = G;
        rg = g.reverse();
    }



    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return bfs(v, w);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return parent; // after calling the length, the parent would be modified.
    }

    // BFS from single source
    private int bfs(int s, int t) {
        boolean[] marked = new boolean[g.V()]; // every time use bfs, initialize it again.
        boolean[] reverse = new boolean[g.V()];
        int[] distTo = new int[g.V()];
        int[] edgeTo = new int[g.V()];
        for (int v = 0; v < g.V(); v++)
            distTo[v] = INFINITY;
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s); // all the above are about initialization.
        while (!q.isEmpty()) {
            int v = q.dequeue();
            if (v == t) {
                int distance = distTo[v];
                do {
                    v = edgeTo[v];
                }
                while(reverse[v]); // find the turning point, i.e. the ancestor.
                parent = v;
                return distance;
            }
            if (reverse[v] == false) {
                for (int w : g.adj(v)) { // these are the nodes in the normal digraph.
                    if (!marked[w]) {
                        edgeTo[w] = v;
                        distTo[w] = distTo[v] + 1;
                        marked[w] = true;
                        q.enqueue(w);
                    }
                }
                for (int w : rg.adj(v)) {// these are the nodes in the reverse digraph.
                    if (!marked[w]) {
                        edgeTo[w] = v;
                        distTo[w] = distTo[v] + 1;
                        marked[w] = true;
                        reverse[w] = true;
                        q.enqueue(w);
                    }
                }
            } else {// these are the nodes in the reverse digraph, they can only find nodes in the reverse graph.
                for (int w : rg.adj(v)) {
                    if (!marked[w]) {
                        edgeTo[w] = v;
                        distTo[w] = distTo[v] + 1;
                        marked[w] = true;
                        reverse[w] = true;
                        q.enqueue(w);
                    }
                }
            }
        }
        parent = -1;
        return -1;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
          int parentIter = -1; // record the vertex of the shortest path.
            int shortestDistance = INFINITY; // record the length of the shortest path.
//            int[] shortestEdgeTo = new int[g.V()]; // record the path of the shortest path.
        boolean[] marked = new boolean[g.V()]; // same variables used in the single source
        boolean[] reverse = new boolean[g.V()];
        int[] distTo = new int[g.V()];
        int[] edgeTo = new int[g.V()];
        for (int i = 0; i < g.V(); i++)
            distTo[i] = INFINITY;
        for (Integer m:
             v) {
            for (Integer n:
                 w) {
                int leng = length(m, n); // according to the test client, the length always comes at first. so do the corrections accordingly.
                int vertex = ancestor(m, n);
                if (leng >= 0 && leng < shortestDistance) {
                    shortestDistance = leng;
                    parentIter = vertex;
                }

            }
        }
        if (shortestDistance == INFINITY) {shortestDistance = -1;}
        return shortestDistance;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return parentIter;
  }
    // do unit testing of this class
    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
