# 📃 A-program-with-Trie-Data-Structure
  - A program written in Java using Trie Data Structure. How the program works and what it does are explained below.

## Problem Statement and Code Design
- Primary goal was to be able to create the R-way trie structure by correctly reading the words in the file we read. While reading the words, we made the value of each word by indexing the file according to the reading order. 
- At the same time, to run the findTopK(int k) method, we needed to keep track of how many times each word in our file was repeated. That's why we created the count variable to be kept on each node of our Trie. This count data kept in the node keeps us informed of how many times each word has been overwritten (ie the number of repetitions of each word in the file). Then, with the switch-case, we enabled the desired operations to be performed according to the inputs received from the user. The methods in the TrieHelper.java class are the methods that allow the user to perform the operations as desired.
![image](https://user-images.githubusercontent.com/75734949/161154919-37d48f5c-19eb-42ed-8c77-d1c861f3a94a.png)
