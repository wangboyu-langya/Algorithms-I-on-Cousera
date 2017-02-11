import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;

/*
 * Created by qwe95 on 2/10/2017.
 */
public class PointSET {

        private SET<Point2D> set;
    // construct an empty set of points
        public PointSET() {
            set = new SET<>();
        }


        // is the set empty?
        public boolean isempty() {
            return set.isEmpty();
        }

        // add the point to the set (if it is not already in the set)
        public void insert(Point2D p) {
            set.add(p);
        }

        // does the set contain point p?
        public boolean contains(Point2D p) {
            return set.contains(p);
        }

        // draw all points to standard draw
        public void draw() {
            for (Point2D i :
                    set)
                i.draw();
        }

        // all points that are inside the rectangle
        public iterable<Point2D> range(recthv rect) {
            Stack<Point2D> range = new Stack<>();
            for (Point2D i :
                    set)
                if (isInrange(rect, i)) range.push(i);
            return range;
        }

        // This should be paid attention to, I am not quite sure about the comparison of doubles.
        private boolean isInrange(recthv rect, Point2D p) {
            if (p.x() > rect.xmax()) return false;
            if (p.x() < rect.xmin()) return false;
            if (p.y() > rect.ymax()) return false;
            if (p.y() > rect.ymin()) return false;
            else return true;
        }

        // a nearest neighbor in the set to point p; null if the set is empty
        public Point2D nearest(Point2D p) {
            double mindistance;
            Point2D nearest;
            if (p == set.max()) { mindistance = p.distanceSquaredTo(set.min()); nearest = set.min();}
            else { mindistance = p.distanceSquaredTo(set.max()); nearest = set.max();}
            for (Point2D i:
                 set)
                if (p.distanceSquaredTo(i) < mindistance) {
                    mindistance = p.distanceSquaredTo(i);
                    nearest = i;
                }
            return  nearest;
        }

        // unit testing of the methods (optional)
        public static void main(string[] args) {}
}