//-----------------------------------------------------
// Title: Queue.java
// Authors: Kayra POLAT - Baturalp KIZILTAN
// Description: This class defines a generic type of an item in queue.
//-----------------------------------------------------

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
	private Node<Item> first; // beginning of queue
	private Node<Item> last; // end of queue
	private int n; // number of elements on queue

	// helper linked list class
	private static class Node<Item> {
		// -------------------------------------------------
		// Summary: This is a helper linked list class of our queue
		// Precondition: item is an item and next is a Node
		// Postcondition: Our nodes defined like below
		// -------------------------------------------------

		private Item item;
		private Node<Item> next;
	}

	public Queue() {
		// -------------------------------------------------
		// Summary: Initializes an empty queue
		// Precondition: none
		// Postcondition: Initalizing first node as null and number of item is 0.
		// -------------------------------------------------

		first = null;
		last = null;
		n = 0;
	}

	public boolean isEmpty() {
		// --------------------------------------------------------
		// Summary: Check queue is empty or not
		// Precondition: none
		// Postcondition: First node is null then return true
		// --------------------------------------------------------

		return first == null;
	}

	public int size() {
		// --------------------------------------------------------
		// Summary: Find the size of queue
		// Precondition: none
		// Postcondition: Return number of elements into the queue
		// --------------------------------------------------------

		return n;
	}

	public Item peek() {
		// --------------------------------------------------------------
		// Summary: Returns the item recently added.
		// Precondition: none
		// Postcondition: First item returned in our queue
		// --------------------------------------------------------------

		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		return first.item;
	}

	public void enqueue(Item item) {
		// --------------------------------------------------------------
		// Summary: When this method call new Item is added into the queue
		// Precondition: item is a Item and it is generic
		// Postcondition: The new item is added to the queue
		// --------------------------------------------------------------

		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		n++;
	}

	public Item dequeue() {
		// --------------------------------------------------------------
		// Summary: When this method call the last recently added Item removed
		// Precondition: none
		// Postcondition: Last recently item removed to the queue
		// --------------------------------------------------------------

		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		n--;
		if (isEmpty())
			last = null; // to avoid loitering
		return item;
	}

	public Iterator<Item> iterator() {
		return new LinkedIterator(first);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class LinkedIterator implements Iterator<Item> {
		private Node<Item> current;

		public LinkedIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}
