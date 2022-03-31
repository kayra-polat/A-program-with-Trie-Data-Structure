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


<a href="url"><img src="https://user-images.githubusercontent.com/75734949/161155686-74ae79da-fda9-4228-9b7c-0872c849148d.png" align="left" height="150" width="350" ></a>

<a href="url"><img src="https://user-images.githubusercontent.com/75734949/161156078-6f3756fd-b2be-40f6-b869-be5fdfc491fb.png" align="middle" height="50" width="350" ></a>
<br/>
- While adding each word to trie, we should not ignore a very critical point. For the findTopk function to work correctly, it was necessary to know how many times each word was repeated while reading the file. To achieve this, we kept a 'count' integer data structure inside the Node class in the TrieST class. Basically, count keeps track of how many times a word has been overwritten. This allows us to reach the number of each word in the file we have. This if case uses the Search method to check if the current word has been added to trie before. If it is added, it takes the count of the current word. It adds (overwrites) the trie again by increasing the received count by one.

<a href="url"><img src="https://user-images.githubusercontent.com/75734949/161156209-3ddde87e-cbdd-4e70-83c6-ff64bac7f1a5.png" align="left" height="100" width="250" ></a>











