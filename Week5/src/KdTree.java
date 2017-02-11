import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

/**
 * Created by qwe95 on 2/11/2017.
 */
public class KdTree {
    /*    private class TwoDTree {
        public TwoDTreeTree() {
        }

        public void insert(Point2D) {
        }
    }*/

    /*
     * This follows the same implementation on 3.2 page4. The attributes are
     * transparent in the class body.
     */
    private class Node {
        private Node left, right;
        private Point2D point;
        private boolean direction;
        private Node(point, direction) {
            this.point = point;
            this.direction = direction;
        }
        public int compare(Point2D p) {
            if (direction == VERTICAL) return Double.compare(point.x(), p.x());
            else return Double.compare(point.y(), p.y());
        }
    }

    private final  static  boolean HORIZON = false;
    private  final  static boolean VERTICAL = true;
    private Node root;
    private int N;

    // construct an empty set of points
    public         KdTree() {
    
    }
    public           boolean isEmpty() {
        return root == null;
    }
    // number of points in the set
    public               int size() {
        return N;
    }

    // add the point to the set (if it is not already in the set)
    /*
     * If the KdTree is empty, initialize with the given point here.
     * Else, check if the point is in the KdTree, this is unnecessary for
     * the adding process can solve the problem of repeatedly adding.
     *
     *  this return value doesn't has meaning, just to give the same
     *  value to the root. root would never change.
     *
     *  the initial boolean is only useful for initialization, otherwise
     *  it's not important at all, since the only thing that matters is
     *  the direction of its parent.
     */
    public              void insert(Point2D p)        {
        root = put(root, p, VERTICAL);
    }
    /*
     * A reursive method to solve the problem.
     */
    private Node put(Node x, Point2D p, boolean direction) {
        if (x == null) { N++; return new Node(p, direction);}
        int cmp = x.compare(p);
        if (cmp < 0)
            x.left = put(x.left, p, !x.direction);
        else if (cmp > 0)
            x.right = put(x.right, p, !x.direction);
        else if (cmp == 0)
            if (x.point.isEqual(p)) return x;
        return x;
    }

    // does the set contain point p?
    public           boolean contains(Point2D p)  {
        Node x = root;
        while (x != null)
        {
            int cmp = x.compareTo(p);
            if (cmp < 0) x = x.right;
            else if (cmp > 0) x = x.left;
            else if (cmp == 0)
                if (x.point.equals(p)) return true;
        }
        return false;
    }
    public              void draw()                         // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle
    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args)                  // unit testing of the methods (optional)
}
