//-----------------------------------------------------
// Title: Puzzle Solver class
// Author: Kayra Polat - Baturalp KIZILTAN
// ID: 1000306178 - 4456996054
// Section: 1
// Assignment: 5
// Description: This class contains the required logic to be able solve
// implement the 6th functionality (puzzle solving) of the assignment.
//-----------------------------------------------------

import java.io.File;
import java.util.Scanner;

public class PuzzleSolver {
	private final String[][] puzzle;
	private int currentRow, currentCol, rowLength, colLength;
	private final int[] directionX = { // Possible directions along the horizontal axis
			 0,  1,  1, 1, 
			-1, -1, -1, 0 
		};
    private final int[] directionY = { // Possible directions along the vertical axis
    		 1, -1, 0,  1, 
    		-1,  0, 1, -1 
    	};
	
	public PuzzleSolver(String filename) throws Exception {
		/**
		 * Summary: Constructor method of the Puzzle Solver class.
		 * Gets a filename as an argument then opens the puzzle text file.
		 * Initializes a 256*256 string array, since the total number of 
		 * characters is unknown at the initial state. Then reads each row and
		 * column respectively to register into array. Maintains lengths of row and column.
		 * Precondition: filename --> String
		 * Postcondition: Built the puzzle array using the input file. Stored the total length
		 * value of row & column. If the file couldn't be opened properly, or total input char
		 * count are greater than the expected, then throws exception. 
		 * */
		Scanner file = new Scanner(new File(filename));
		this.puzzle = new String[256][256];
		
		int i = 0;
		while (file.hasNextLine()) {
			String line = file.nextLine();
			String[] chars = line.split("[ \\t]");
			int j = 0;
			for (String c : chars) {
				this.puzzle[i][j] = c;
				++j;
			}
			colLength = j;
			++i;
		}
		rowLength = i;
	}
	
	public boolean Contains(String key) {
		/**
		 * Summary: Public method to check each string key (pattern) whether 
		 * they are included or not in the puzzle file using private method "hasPatternOf".
		 * Basically, visits each character by traversing every row and column index. And at 
		 * every step executes internal method "hasPatternOf" if current char matches with the first
		 * char of key the pattern.
		 * Precondition: key (pattern) --> String
		 * Postcondition: if the pattern is matched, returns true. Otherwise, always returns false.
		 * */
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				currentRow = i;
				currentCol = j;
				
				String firstChar = String.valueOf(key.charAt(0));
				if (puzzle[i][j].equals(firstChar)) {
					if (hasPatternOf(key)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	private boolean hasPatternOf(String key) {
		/**
		 * Summary: Traverses all directions one by one on each character of the key. 
		 * At each visit, checks for boundaries/borders of puzzle array and whether the current char
		 * equals to the expected character in the pattern. If current char in the pattern (key) does
		 * not equal to the char at current puzzle index, tries going to another direction. Repeats 
		 * until nothing left to ensure conditions/directions.
		 * Precondition: key (pattern) --> String
		 * Postcondition: returns whether the pattern is found (true) or not (false) as boolean
		 * */
		for (int i = 0; i < directionX.length; i++) {
            int rowIndex = currentRow + directionX[i];
            int columnIndex = currentCol + directionY[i];
 
            int currentCharIndex;
            for (currentCharIndex = 1; currentCharIndex < key.length(); ++currentCharIndex) {
            	String currentChar = String.valueOf(key.charAt(currentCharIndex));
            	
                if ((rowIndex >= rowLength || rowIndex < 0) || 
                		(columnIndex >= colLength || columnIndex < 0))
                    break;
 
                if (! puzzle[rowIndex][columnIndex].equals(currentChar))
                    break;
 
                rowIndex += directionX[i];
                columnIndex += directionY[i];
            }
 
            if (currentCharIndex == key.length())
                return true;
        }
		return false;
	}
}