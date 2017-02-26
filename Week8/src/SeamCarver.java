import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Picture;

import java.awt.*;

/**
 * Created by qwe95 on 2/24/2017.
 * <p>
 * The general idea is simple:
 * 1.calculate all the weights of the pixel. And store the information in an two-dimension array. This could be done when copy
 * the picture. create a private method to calculate the gradient.
 * 2.use greedy algorithm to find the shortest path. use a priority queue to store all the information. And start
 * from the shortest one.
 * <p>
 * <p>
 * Problems at the moment:
 * 1. Although the requirement is that the performance requirement is at most linear to all the pixels in the
 * figure. I want to find an efficient way to implement it. After discussion with Kang, I convinced myself that
 * there is no better way.
 * 2. The algorithm used to find the shortest path is the same as the Dijkstra's algorithm or the topological sort.
 * However, I don't quite understand the mechanism of topological sort.
 * I only have a general idea how the topological order works, but since there is no explicit digraph here, I
 * think I would rather not use the topological sort algorithm. Instead, I should improve the Dijkstra's algorithm.
 * The only difference is that the edge is no longer important, I should only using the nodes, and the other things
 * are approximately the same.
 * To be more specific, setting an array of distance, setting an array of node, always deque the node with the
 * shortest gradient, and add its neighbors, i.e the next three pixels in the next row.
 * When adding to the queue, the edge and distance should be changed.
 * 3. If using the algorithm above, the difficulty should be the transformation between the row, col number and a single
 * number.
 */
public class SeamCarver {
    private Picture current;
    private int height;
    private int width;
    private double[][] gradients;

    public SeamCarver(Picture picture)                // create a seam carver object based on the given picture
    {
        height = picture.height();
        width = picture.width();
        current = new Picture(width, height);
        gradients = new double[width][height];
        for (int col = 0; col < width; col++) { // here I follow the same convention as the picture, cause the column indicates the x coordinate, so it is put in the first.
            for (int row = 0; row < height; row++) {
                Color color = picture.get(col, row);
                double gradient = toGradient(picture, col, row);
                picture.set(col, row, color);
                gradients[col][row] = gradient;
            }
        }
    }

    // calculate the gradient of the given pixel in the picture.
    private double toGradient(Picture picture, int col, int row) {
        double gradient;
        double squareDeltaX, squareDeltaY;
        int rX, gX, bX, rY, gY, bY;
        rX = picture.get(col - 1, row).getRed() - picture.get(col + 1, row).getRed();
        gX = picture.get(col - 1, row).getGreen() - picture.get(col + 1, row).getGreen();
        bX = picture.get(col - 1, row).getBlue() - picture.get(col + 1, row).getBlue();
        rY = picture.get(col, row + 1).getRed() - picture.get(col, row - 1).getRed();
        gY = picture.get(col, row + 1).getGreen() - picture.get(col, row - 1).getGreen();
        bY = picture.get(col, row + 1).getBlue() - picture.get(col, row - 1).getBlue();
        squareDeltaX = rX * rX + gX * gX + bX * bX;
        squareDeltaY = rY * rY + gY * gY + bY * bY;
        gradient = Math.sqrt(squareDeltaX + squareDeltaY);
        return gradient;
    }

    public int width()                            // width of current picture
    {  return width;  }

    public int height()                           // height of current picture
    {  return height;  }

    public double energy(int x, int y)               // energy of pixel at column x and row y
    {  return gradients[x][y];  }

    public int[] findHorizontalSeam()               // sequence of indices for horizontal seam
    {
          MinPQ<Double> pq = new MinPQ();
          int[] nodeTo = new int[width * height];  // record the last node to it
          double[] distanceTo = new double[width * height];  // record the distance to the node
        for (int v = 0; v < ; v++) {
            distanceTo[v] = Double.POSITIVE_INFINITY;
        }
        distanceTo[0] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (int n : adj(v)) {
                relax(n);
            }
        }


    }

    private int[] adj(int v) {
    }

    public int[] findVerticalSeam()                 // sequence of indices for vertical seam

    public void removeHorizontalSeam(int[] seam)   // remove horizontal seam from current picture

    public void removeVerticalSeam(int[] seam)     // remove vertical seam from current picture
}

