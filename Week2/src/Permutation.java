import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 */

/**
 * @author qwe95
 *
 */
public class Permutation {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> randomizedqueue  = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			randomizedqueue.enqueue(item);
        }
		for ( int i = 0; i < k; i++)
			StdOut.println(randomizedqueue.dequeue());
	}

}
