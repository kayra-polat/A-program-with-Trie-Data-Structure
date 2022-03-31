//-----------------------------------------------------
// Title: TrieHelper.java
// Author: Kayra Polat - Baturalp Kýzýltan
// IDs: 1000306178 - 4456996054
// Section: 1
// Assignment: 5
// Description: This is a Helper Trie class to run the given algorithms. Driver class is use this class directly. The methods in it meet all the requirements.
//-----------------------------------------------------

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TrieHelper {

	// Global Variables
	private TrieST<Integer> trieST = new TrieST<Integer>(); // Original trie data structure
	private TrieST<Integer> reversedTrieST = new TrieST<Integer>(); // Reversed trie data structure
	private ArrayList<String> buffer = new ArrayList<String>(); // A helper arraylist for used this for all methods.

	public TrieHelper(TrieST<Integer> trieST, TrieST<Integer> reversedTrieST) {
		// --------------------------------------------------------------
		// Summary: Constructor method.
		// Precondition: trieST a TrieST<Integer> and reversedTrieST is Trie<Integer>.
		// Postcondition: Tries are created.
		// --------------------------------------------------------------

		this.setTrieST(trieST);
		this.setReversedTrieST(reversedTrieST);
	}

	public static String reverseString(String str) {
		// --------------------------------------------------------------
		// Summary: Take a string and reversed it an returned it.
		// Precondition: str is String
		// Postcondition: Given string is reversed and returned.
		// --------------------------------------------------------------

		char ch[] = str.toCharArray();
		String rev = "";
		for (int i = ch.length - 1; i >= 0; i--) {
			rev += ch[i];
		}
		return rev;
	}

	private void print() {
		// --------------------------------------------------------------
		// Summary: void printing method.
		// Precondition: none
		// Postcondition: print output with using buffer array-list, lexicographically.
		// --------------------------------------------------------------

		Collections.sort(buffer);
		for (int i = 0; i < buffer.size(); i++) {

			if (i == buffer.size() - 1) {
				System.out.print(buffer.get(i));
				continue;
			}

			System.out.print(buffer.get(i) + ", ");

		}

		buffer.clear();
	}

	public boolean Search(String arg) {
		// --------------------------------------------------------------
		// Summary: Boolean search method to search given key in Trie.
		// Precondition: arg is a String
		// Postcondition: If the given string found in trie then return true, otherwise
		// false.
		// --------------------------------------------------------------

		if (arg == null)
			throw new IllegalArgumentException("argument to Search() is null");
		if (trieST.get(arg) != null) {
			return true;
		}
		return false;
	}

	public void autoComplete(String prefix) {
		// --------------------------------------------------------------
		// Summary: Find all strings start with given prefix in trie and print them with
		// print() method.
		// Precondition: prefix is a String
		// Postcondition: Found strings with given prefix, add them in to buffer
		// array-list and print using print() method.
		// --------------------------------------------------------------

		Queue<String> results = (Queue<String>) trieST.keysWithPrefix(prefix);

		if (results.isEmpty()) {
			System.out.println("No word");
			return;
		}

		for (String string : results) {
			buffer.add(string);
		}

		print();
	}

	public void reverseAutoComplete(String suffix) {
		// --------------------------------------------------------------
		// Summary: Find all strings end with given suffix in trie and print them with
		// print() method.
		// Precondition: suffix is a String
		// Postcondition: Found strings with given suffix, add them in to buffer
		// array-list and print using print() method.
		// --------------------------------------------------------------

		Queue<String> results = (Queue<String>) reversedTrieST.keysWithPrefix(suffix);
		if (results.isEmpty()) {
			System.out.print("No word");
			return;
		}

		for (String string : results) {
			buffer.add(reverseString(string));
		}

		print();
	}

	public void FullAutoComplete(String prefix, String suffix) {
		// --------------------------------------------------------------
		// Summary: Find all strings start with given prefix in trie and end with given
		// suffix and print them with print() method.
		// Precondition: prefix is a String and suffix is a String
		// Postcondition: Found strings with given prefix and given suffix, add them in
		// to buffer array-list and print using print() method. In basic logic, we take
		// the two tries we have and compare them among them. If we encounter a word
		// that is the same in both, we add that word to the buffer arraylist.
		// --------------------------------------------------------------

		Iterable<String> results_pre = trieST.keysWithPrefix(prefix);
		Iterable<String> results_suf = reversedTrieST.keysWithPrefix(suffix);
		ArrayList<String> suffixArrayList = new ArrayList<String>();

		// Since the words stand in the opposite way in the reusults_suf Iterable, we
		// make these words original.
		for (String string : results_suf) {
			suffixArrayList.add(reverseString(string));
		}
		
		// Comparison process
		for (String string : results_pre) {
			for (String string2 : suffixArrayList) {
				if (string.equals(string2)) {
					buffer.add(string);
				}
			}
		}

		if (buffer.isEmpty()) {
			System.out.println("No word");
		}

		print();
	}

	public void findTopK(int k) {
		// --------------------------------------------------------------
		// Summary: Find top k words(given by user) that have most occurences. In Trie,
		// I created a primitive data structure called count in the Node class. It is
		// explained in detail in the driver class. count counts how many times a word
		// occurs in the file being read
		// Precondition: k is an integer
		// Postcondition: key_with_counts is a data structure that holds the keys in the
		// trie and their counts. After we put all the keys and their counts in the hash
		// map, we sort this data in descending order according to the coutns. In this
		// way, the most repeated words are arranged in order from beginning to end.
		// --------------------------------------------------------------

		Map<String, Integer> key_with_counts = new HashMap<>();
		LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

		for (String string : trieST.keys()) {
			key_with_counts.put(string, trieST.getCount(string));
		}
		// Call sorting_by_descending function to sort key-value pairs and put them into
		// new hashmap -> reversedSortedMap
		reverseSortedMap = sorting_by_descending(key_with_counts);

		for (Map.Entry<String, Integer> entry : reverseSortedMap.entrySet()) {
			if (k == 0)
				break;
			String key = entry.getKey();
			buffer.add(key);
			k--;
		}

		print();
	}

	private LinkedHashMap<String, Integer> sorting_by_descending(Map<String, Integer> map) {
		// --------------------------------------------------------------
		// Summary: Sort the given map by values with descending order.
		// Precondition: map is Map<String, Integer>
		// Postcondition: Created LinkedHashMap. Sort the given map to putting
		// key-values into reversedSortedMap and return it.
		// --------------------------------------------------------------

		LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
		map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

		return reverseSortedMap;
	}

	public void SolvePuzzle(String filepath) throws Exception {
		// --------------------------------------------------------------
		// Summary: Solve the given puzzle(another .txt file). Read input from the given
		// filepath and print all possible words in our trie. First create an object in
		// PuzzleSolver class.
		// Precondition: filepath is a String which represents the filename of puzzle.
		// Postcondition: Returns true if any word in the trie is found in the given
		// puzzle file, otherwise false.
		// --------------------------------------------------------------

		PuzzleSolver puzzleSolver = new PuzzleSolver(filepath);

		for (String key : trieST.keys()) {
			if (puzzleSolver.Contains(key))
				buffer.add(key);
		}

		if (buffer.isEmpty())
			System.out.println("No word found");
		else
			print();
	}

	public TrieST<Integer> getTrieST() {
		return trieST;
	}

	public void setTrieST(TrieST<Integer> trieST) {
		this.trieST = trieST;
	}

	public TrieST<Integer> getReversedTrieST() {
		return reversedTrieST;
	}

	public void setReversedTrieST(TrieST<Integer> reversedTrieST) {
		this.reversedTrieST = reversedTrieST;
	}

}