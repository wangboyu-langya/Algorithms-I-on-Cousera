import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

/**
 * 
 */

/**
 * @author huxianglong
 *
 * This package is implemented to realized a queue that  
 * the item removed is chosen uniformly at random from items in the data structure. 
 * 
 * Linked lists cannot be use to implement this method, as it's hard to generate a 
 * random number and extract it from the list in constant amortized time. So resized 
 * arrays have to be used.
 * 
 * The difficulty is in that eliminate a random element from the queue.
 * Because the order of this queue is not important, I could just move
 * the last element to the deleted position. So that the time would be 
 * constant. There is no need to use multiple arrays to track the index.
 */
public class RandomizedQueue<Item> implements Iterable<Item> 
{
	private Item[] s;    // the resized array storing the queue
	private int N;       // size of the queue.
	
   @SuppressWarnings("unchecked")
   public RandomizedQueue()                 
   {  s = (Item[]) new Object[1];  }
   
   public boolean isEmpty()                 
   {  return N == 0;  }
   
   public int size()                        
   {  return N;  }
   
   public void enqueue(Item item)          
   {
		if ( item == null) throw new java.lang.NullPointerException();
		if ( N > 0 && N == s.length) resize(2*s.length);
		s[N++] = item;
   }
   
   @SuppressWarnings("unchecked")
   private void resize(int capacity)
   {
	   Item[] copy; 
	   copy = (Item[]) new Object[capacity];
	   for ( int i = 0; i < N; i++)
		   copy[i] = s[i];
	   s = copy;
   }
   
   public Item dequeue()                   
   {
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   int index = StdRandom.uniform(N);
	   Item item = s[index];
	   s[index] = s[--N];
	   s[N] = null;
	   if ( N > 0 && N == s.length/4) resize(s.length/2);
	   return item;
   }
   
   public Item sample()                     
   {
   	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   int index = StdRandom.uniform(N);
	   return s[index];
   }
   
   public Iterator<Item> iterator()         
   {  return new RandomizedQueueIterator(); }
   
   private class RandomizedQueueIterator implements Iterator<Item>
   {
	   int[] index = new int[N];
	   public RandomizedQueueIterator(){
		   for ( int i = 0; i < N; i++)
			   index[i] = i;
		   StdRandom.shuffle(index);            //  This is a random index array to set up an iterator.
	   }
	   int i = N;
	   public boolean hasNext() {  return i > 0;  }
	   public void remove()     {  throw new java.lang.UnsupportedOperationException();  }
	   public Item next() 
	   {  
		   if (hasNext()) return s[index[--i]]; 
		   else {  throw new java.util.NoSuchElementException();  }
	   }
   } 
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}