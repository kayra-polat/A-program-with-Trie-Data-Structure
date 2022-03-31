//-----------------------------------------------------
// Title: TrieST.java
// Author: Kayra POLAT - Baturalp KIZILTAN
// Description: This class represents an symbol table of key-value pairs, with string keys and generic values. It create R-way Trie API.
//-----------------------------------------------------

public class TrieST<Value> {
	public static final int R = 256; // extended ASCII

	private Node root; // root of trie
	private int n; // number of keys in trie

	// R-way trie node
	// A value, next node and count is initialized
	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
		private int count; // Counts how many times the string was overwritten.(how many times each word in
							// the file is encountered)
	}

	public TrieST() {
		// --------------------------------------------------------------
		// Summary: Initializes an empty string symbol table
		// Precondition: none
		// Postcondition: An empty TrieST has been created.
		// --------------------------------------------------------------

	}

	public Value get(String key) {
		// --------------------------------------------------------------
		// Summary: Return the value associated with the given key.
		// Precondition: key is String
		// Postcondition: Value of the given is returned. Also before that, Called
		// private get class to keep the node of the given key.
		// --------------------------------------------------------------

		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		Node x = get(root, key, 0);
		if (x == null)
			return null;
		return (Value) x.val;
	}

	private Node get(Node x, String key, int d) {
		// --------------------------------------------------------------
		// Summary: Helper recursive private method to return a Node.
		// Precondition: x is a Node Object, key is a string and d is an integer
		// Postcondition: After recursive calls, Node x i returned to original get()
		// method.
		// --------------------------------------------------------------

		if (x == null)
			return null;
		if (d == key.length())
			return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d + 1);
	}

	public void put(String key, Value val, int count) {
		// --------------------------------------------------------------
		// Summary: Inserts the key-value pair into the symbol table. If given key is
		// already in trie, overwriting the old value with given key
		// Precondition: key is String, val is a Value, count is an integer
		// Postcondition: Called private put method to put the given key.
		// --------------------------------------------------------------

		if (key == null)
			throw new IllegalArgumentException("first argument to put() is null");
		if (val == null)
			delete(key);
		else
			root = put(root, key, val, count, 0);
	}

	private Node put(Node x, String key, Value val, int count, int d) {
		// --------------------------------------------------------------
		// Summary: A recursive private class to put the given key with their value in
		// Trie.
		// Precondition: x is a Node, key is String, val is Value Object, count is an
		// integer, d is an integer
		// Postcondition: After recursive calls, the given key is inserted in Trie with
		// their value and their count.(COUNT is for Counts how many times the string
		// was overwritten)
		// --------------------------------------------------------------
		if (x == null)
			x = new Node();
		if (d == key.length()) {
			if (x.val == null)
				n++;
			x.val = val;
			x.count = count;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, count, d + 1);
		return x;
	}

	public int size() {
		// --------------------------------------------------------
		// Summary: Find the size of the symbol table key-value pairs.
		// Precondition: none
		// Postcondition: Return number of elements into the symbol table(Trie)
		// --------------------------------------------------------

		return n;
	}

	public boolean isEmpty() {
		// --------------------------------------------------------
		// Summary: Check symbol table is empty or not?
		// Precondition: none
		// Postcondition: Size is zero then return true.
		// --------------------------------------------------------

		return size() == 0;
	}

	public int getCount(String key) {
		// --------------------------------------------------------------
		// Summary: Return the count associated with the given key.(The element count in
		// Node class is for counts how many times the string was overwritten. how many
		// times each word in the file is encountered)
		// Precondition: key is String
		// Postcondition: Count of the given is returned. Also before that, Called
		// private get class to keep the node of the given key.
		// --------------------------------------------------------------

		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		Node x = get(root, key, 0);
		if (x == null)
			return 0;
		return x.count;

	}

	public Iterable<String> keys() {
		// --------------------------------------------------------
		// Summary: Returns all keys in the symbol keys as an Iterable.
		// Precondition: none
		// Postcondition: Call keysWithPrefix() method an returned it.(keyWithPrefix
		// method will explain below)
		// --------------------------------------------------------

		return keysWithPrefix("");
	}

	public Iterable<String> keysWithPrefix(String prefix) {
		// --------------------------------------------------------
		// Summary: Returns all of the keys in the set that start with given prefix
		// Precondition: prefix is a String
		// Postcondition: Returned key or keys in the trie start with given prefix.
		// --------------------------------------------------------

		Queue<String> results = new Queue<String>();
		Node x = get(root, prefix, 0);
		collect(x, new StringBuilder(prefix), results);
		return results;
	}

	private void collect(Node x, StringBuilder prefix, Queue<String> results) {
		if (x == null)
			return;
		if (x.val != null)
			results.enqueue(prefix.toString());
		for (char c = 0; c < R; c++) {
			prefix.append(c);
			collect(x.next[c], prefix, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	public void delete(String key) {
		// --------------------------------------------------------
		// Summary: Delete the key from the set if the key is present.
		// Precondition: key is a String
		// Postcondition: Removed the given key in Trie.
		// --------------------------------------------------------

		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		root = delete(root, key, 0);
	}

	private Node delete(Node x, String key, int d) {
		if (x == null)
			return null;
		if (d == key.length()) {
			if (x.val != null)
				n--;
			x.val = null;
		} else {
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c], key, d + 1);
		}

		// remove subtrie rooted at x if it is completely empty
		if (x.val != null)
			return x;
		for (int c = 0; c < R; c++)
			if (x.next[c] != null)
				return x;
		return null;
	}

}
