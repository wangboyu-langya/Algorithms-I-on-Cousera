import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {

	/**
	 * @param args
	 * 鍏堝畬鎴愪竴鑸儏鍐碉紝鍐嶈�冭檻鐗规畩鎯呭喌
	 * 瑕佸啓test鏂囦欢
	 */

	
	private Node first,last; 
	private int N;
	
	private class Node {
		Item item;
		Node next;
		Node previous;
	}

	public Deque() {
		first = null;
		last = first;
	}

	public boolean isEmpty() {
		return first == last && first == null;
	}
	
	public int size() {
		return N;
	}

	public void addFirst(Item item) {
		if (item == null) throw new java.lang.NullPointerException();
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst; 
		first.previous = null;
		if (oldfirst != null) oldfirst.previous = first;  
		else last = first;              
		N++;
	}

	/**
	 * 瑙ｅ喅last闇�瑕侀澶栦竴涓寚閽堬紝鎸囧悜鍊掓暟绗簩涓猲ode
	 * 杩欎釜鎬濊矾鏄敊璇殑锛屽洜涓哄鍒犻櫎鍑犳灏辨病鐢ㄤ簡
	 * 鏍规嵁棰樼洰鐨勮姹傛潵鐪嬶紝48n锛宭inked list闇�瑕� 40n锛� 棰濆鐨�8涓槸鏈夋剰鐨勫缁欎簡涓�涓寚閽堢殑浣欓 
	 */

    public void addLast(Item item) {
    	if (item == null) throw new java.lang.NullPointerException();
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		last.previous = oldlast;
		if (oldlast != null) oldlast.next = last;
		else first = last;       
		N++;
	}

	public Item removeFirst() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		Item item = first.item;
		first = first.next;
		if (first != null) first.previous = null;
		else last = first;
		N--;
		return item;
	}

	public Item removeLast() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		Item item = last.item;
		last = last.previous;
		if (last != null) last.next = null;
		else first = last;
		N--;
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() { return current != null; }
		public void remove()     { throw new java.lang.UnsupportedOperationException();}
		public Item next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
