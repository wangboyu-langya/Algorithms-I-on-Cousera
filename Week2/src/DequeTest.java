import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Test;

public class DequeTest {

	private Deque<String> deque;
	
	@Before
	public void setUp() {
		deque = new Deque<String>();
	}
	
	
	
	// Test the isEmpty().
	@Test
	public void isEmptyTest_1() {
		assertTrue(deque.isEmpty());
	}
	
	@Test
	public void isEmptyTest_2() {
		deque.addFirst("a");
		assertFalse(deque.isEmpty());
	}
	
	
	
	//Test size() and addFirst().
	@Test
	public void sizeTest_1() {
		assertEquals(0,deque.size());
	}
	
	@Test
	public void sizeTest_2() {
		deque.addFirst("a");
		assertEquals(1,deque.size());
	}
	
	@Test
	public void sizeTest_3() {
		deque.addFirst("a");
		deque.addFirst("b");
		assertEquals(2,deque.size());
	}
	
	
	
	//Test addlast().
	@Test
	public void sizeTest_4() {
		assertEquals(0,deque.size());
	}
	
	@Test
	public void sizeTest_5() {
		deque.addLast("a");
		assertEquals(1,deque.size());
	}
	
	@Test
	public void sizeTest_6() {
		deque.addLast("a");
		deque.addLast("b");
		assertEquals(2,deque.size());
	}
	
	
	//Test remove.
	@Test
	public void removeTest_1() {
		deque.addFirst("a");
		deque.addFirst("b");
		deque.addLast("c");
		deque.addLast("d");
		assertEquals("b", deque.removeFirst());
		assertEquals("a", deque.removeFirst());
		assertEquals("c", deque.removeFirst());
		assertEquals("d", deque.removeFirst());
		assertTrue(deque.isEmpty());
	}
	
	

}
