//-----------------------------------------------------
// Title: Driver.java
// Author: Kayra Polat - Baturalp Kiziltan
// Description: This class is run the whole program
//-----------------------------------------------------

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Driver {
	public static char[] replaceString(char[] str) {
		// --------------------------------------------------------------
		// Summary: A special static function to take a string to char array an replace
		// 'I' to 'i'. Because program perceive letter 'I' to 'ý'. Have to change this I
		// to 'i'.
		// Precondition: str is a char array
		// Postcondition: Replaced the 'I' 's to 'i'
		// --------------------------------------------------------------

		int length = str.length;
		for (int i = 0; i < length; i++) {
			if (str[i] == 'I') {
				str[i] = 'i';
			}
		}
		return str;
	}

	public static void main(String[] args) {
		// --------------------------------------------------------------
		// Summary: Main method to run the whole program.
		// Precondition: args is String array
		// Postcondition: The program first reads the words in the file and adds them to
		// trie. Then, with the switch-case structure, inputs are taken from the user
		// and the desired functions are run according to the inputs.
		// --------------------------------------------------------------

		Scanner input = new Scanner(System.in);
		String word = null;
		String word2 = null;
		String file_name = input.next().trim();
		int function_num = input.nextInt();

		// If fullautocomplete method(function 4) is called, 4 data will be taken from
		// the user
		if (function_num == 4) {
			word = input.next().trim();
			word2 = input.next().trim();
		} else
			word = input.next().trim();

		try {
			Scanner sc = new Scanner(new File(file_name));
			TrieST<Integer> trieST = new TrieST<Integer>(); // Create an empty trie.
			TrieST<Integer> reversedTrieST = new TrieST<Integer>(); // Create an empty reversedtrie.
			TrieHelper trieHelper = new TrieHelper(trieST, reversedTrieST); // Create trieHelper obejct to to its
																			// functions.

			// Reading the given file, add these words in trie one by one
			int index = 0;
			while (sc.hasNextLine()) {
				String[] line = sc.nextLine().split("\\s*(,|;|:|!|\\.|\\t|\\s)\\s*"); // Take one line and split it with the
																				// given regexes.(Depends on the
																				// situation, multiple regex must used)
				for (int i = 0; i < line.length; i++) {

					// If the current word started with 'I', call replaceString function to replace
					// it to 'i'.
					if (line[i].charAt(0) == 73) {
						char[] str = line[i].toCharArray();
						line[i] = String.valueOf(replaceString(str)); // Valueof() method returns to string
																		// representation to char array
					}

					// Turn current words to lower case
					String temp = line[i].toLowerCase();

					/*
					 * For the findTopk function to work correctly, it was necessary to know how
					 * many times each word was repeated while reading the file. To achieve this, we
					 * kept a 'count' integer data structure inside the Node class in the TrieST
					 * class. Basically count keeps track of how many times a word has been
					 * overwritten. This actually allows us to reach the number of each word in the
					 * file we have. This if case uses the Search method to check if the current
					 * word has been added to trie before. If it is added, it takes the count of the
					 * current word. It adds (overwrites) the trie again by increasing the received
					 * count by one.
					 */
					if (trieHelper.Search(temp) == true) {

						int repeated = trieST.getCount(temp);

						trieST.put(temp, index, ++repeated);
						index++;
					} else {
						trieST.put(temp, index, 1); // when a word is encountered for the first time, count 1 becomes
													// directly
						index++;
					}

				}

			}

			/*
			 * Our reversedTrie is a trie where all the words in the original trie are kept
			 * reversed. All the same operations we did when adding the original trie are
			 * done here as well.
			 */
			for (String string : trieST.keys()) {
				int val = trieST.get(string);
				String tempString = TrieHelper.reverseString(string);

				if (trieHelper.Search(tempString) == true) {
					int rep_reverse = reversedTrieST.getCount(tempString);
					reversedTrieST.put(tempString, val, ++rep_reverse);
				} else
					reversedTrieST.put(tempString, val, 1);
			}

			// A switch-case structure that functions according to the user's inputs. While
			// reducing any word or letter received from the user, on the other hand, when
			// we call Locale.ENGLISH, it translates each received character according to
			// the English language. For example, when the user enters the letter 'I',
			// normally when we take the lower case to this letter, this letter makes 'ý'
			// because our program language is in Turkish infrastructure. However, when we
			// make the language English in this way, we can convert the letter 'I' to the
			// letter 'i'.
			switch (function_num) {
			case 1:

				// Case 1 : If the taken words is found in trie return true and print true.
				if (trieHelper.Search(word.toLowerCase(Locale.ENGLISH)))
					System.out.println("True");
				else
					System.out.println("False");
				break;

			case 2:

				// Case 2 : Take the user input and send into autoComplete() method.
				// (autoComplete method is in TrieHelper class, look that for detailed
				// explanation)
				trieHelper.autoComplete(word.toLowerCase(Locale.ENGLISH));

				break;

			case 3:

				// Case 3 : Take the user input and send into reversedAutoComplete() method.
				// (reversedAutoComplete method is in TrieHelper class, look that for detailed
				// explanation) But first take reversed version of taken input because we must
				// look this given word to reversedtrie. To find a suffix, we reversed the tried
				// and tested words. We reversed the suffix we received from the user. Let's
				// make a correct prefix call inside reversedTrieST. Basically we did a
				// reversedTrieST prefix search, but this kind of implementation made it look
				// like we were searching for suffix in the original trie.
				String reversed_word = TrieHelper.reverseString(word);
				trieHelper.reverseAutoComplete(reversed_word.toLowerCase(Locale.ENGLISH));

				break;

			case 4:

				// Case 4 : Take the user input and send into FullAutoComplete() method.
				// (reversedAutoComplete method is in TrieHelper class, look that for detailed
				// explanation.
				String reversed_word2 = TrieHelper.reverseString(word2);
				trieHelper.FullAutoComplete(word.toLowerCase(Locale.ENGLISH),
						reversed_word2.toLowerCase(Locale.ENGLISH));

				break;

			case 5:

				// Case 5 : Take the user input and send into findTopK() method.
				// (findTopK method is in TrieHelper class, look that for detailed
				// explanation.
				trieHelper.findTopK(Integer.parseInt(word));

				break;

			case 6:

				// Case 5 : Take the user input and send into SolvePuzzle() method.
				// (SolvePuzzle method is in TrieHelper class, look that for detailed
				// explanation.
				trieHelper.SolvePuzzle(word);

				break;
			default:
				break;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
