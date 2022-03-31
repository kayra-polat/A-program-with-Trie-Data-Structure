# 📃 A-program-with-Trie-Data-Structure
  - A program written in Java using Trie Data Structure. How the program works and what it does are explained below.

## Problem Statement and Code Design
- Primary goal was to be able to create the R-way trie structure by correctly reading the words in the file we read. While reading the words, we made the value of each word by indexing the file according to the reading order. 
- At the same time, to run the findTopK(int k) method, we needed to keep track of how many times each word in our file was repeated. That's why we created the count variable to be kept on each node of our Trie. This count data kept in the node keeps us informed of how many times each word has been overwritten (ie the number of repetitions of each word in the file). Then, with the switch-case, we enabled the desired operations to be performed according to the inputs received from the user. The methods in the TrieHelper.java class are the methods that allow the user to perform the operations as desired.
![image](https://user-images.githubusercontent.com/75734949/161154919-37d48f5c-19eb-42ed-8c77-d1c861f3a94a.png)

## Whole Implementation and Code Design
- Program first waits for user inputs. The trick here is that the fullAutoComplete function needs 4 inputs to work 
  ```bash
  <file name> <function number> <prefix> <suffix>
  ```
  Except for this case, it is sufficient to enter 3 data by the user to run all functions. 
  ```bash
  <filename> <function number> <word>
  ```
- After receiving the user input, we started reading the file. We take one line and split it with the given regexes. We convert each word into lowercase letters and add them to our trie. Also, in this part, we have a special if case for the letter 'I'. The program calls the replaceString() function every time it sees the letter 'I'. This function converts 'I' to 'i'. In this way, we prevent the letter 'I' from being reduced to 'i' before the reduction operation. Before creating the words, we create our empty trie and empty reversedtrie and send these tries to the constructor method of the TrieHelper class.
  ![](https://user-images.githubusercontent.com/75734949/161155520-90303cf3-0077-4eae-81f1-97a55810ba39.png | width = 100)
