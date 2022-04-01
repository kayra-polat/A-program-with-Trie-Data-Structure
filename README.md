# 📃 A-program-with-Trie-Data-Structure
  - A program written in Java using Trie Data Structure. How the program works and what it does are explained below.

## Important Note
- While writing the program, I used the Algorithms 4th edition book and the book's website. You can find the codes on the site here -> [Algorithms, 4th Edition](https://algs4.cs.princeton.edu/home/)

## Problem Statement and Code Design
- Primary goal was to be able to create the R-way trie structure by correctly reading the words in the file we read. While reading the words, we made the value of each word by indexing the file according to the reading order. 
- At the same time, to run the findTopK(int k) method, we needed to keep track of how many times each word in our file was repeated. That's why we created the count variable to be kept on each node of our Trie. This count data kept in the node keeps us informed of how many times each word has been overwritten (ie the number of repetitions of each word in the file). Then, with the switch-case, we enabled the desired operations to be performed according to the inputs received from the user. The methods in the TrieHelper.java class are the methods that allow the user to perform the operations as desired.

<a href="url"><img src="https://user-images.githubusercontent.com/75734949/161154919-37d48f5c-19eb-42ed-8c77-d1c861f3a94a.png" align="middle" height="500" width="650" ></a>

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
<br/>
<br/>
<br/>
<br/>
- While adding each word to trie, we should not ignore a very critical point. For the findTopk function to work correctly, it was necessary to know how many times each word was repeated while reading the file. To achieve this, we kept a 'count' integer data structure inside the Node class in the TrieST class. Basically, count keeps track of how many times a word has been overwritten. This allows us to reach the number of each word in the file we have. This if case uses the Search method to check if the current word has been added to trie before. If it is added, it takes the count of the current word. It adds (overwrites) the trie again by increasing the received count by one.

<a href="url"><img src="https://user-images.githubusercontent.com/75734949/161156209-3ddde87e-cbdd-4e70-83c6-ff64bac7f1a5.png" align="left" height="100" width="250" ></a>
<a href="url"><img src="https://user-images.githubusercontent.com/75734949/161156949-7f04b346-a32a-4a68-8e46-454ff9c0ea27.png" align="middle" height="75" width="200" ></a>
<br/>
<br/>

- After creating our original trie structure, we created a new trie by reversing the string keys in the original trie created for the implementation of the reverseAutoComplete() function (reversedTrieST). We will explain why we do this when we explain the reverseAutoComplete function.
- At this stage of the driver class, a switch-case structure that functions according to the user's inputs. While reducing any word or letter received from the user, on the other hand, when we call Locale.ENGLISH, it translates each received character according to the English language. For example, when the user enters the letter 'I', normally when we take the lower case to this letter, this letter makes 'ı' because our program language is in Turkish infrastructure. However, when we make the language English in this way, we can convert the letter 'I' to the letter 'i'.
- If the user enters an input such as ``` <input1.txt> <1> <word>```, the **Search (String arg)** function will run. This boolean function will print "True" if it returns true and "False" if it returns false. We call this function from the TrieHelper object we created. This function navigates in the existing trieST. It returns true if the word given by the user exists in trieST, otherwise false.
  
  <a href="url"><img src="https://user-images.githubusercontent.com/75734949/161159196-a14a23ff-68b3-4920-95a0-eda96975ce40.png" align="left" height="75" width="300" ></a>
  
  <a href="url"><img src="https://user-images.githubusercontent.com/75734949/161159367-b984f9d5-7917-43d6-bc57-3b224ed36350.png" align="middle" height="100" width="350" ></a>

- If the user enters an input such as ```<input1.txt> <2> <word>```, the **autoComplete (String prefix)** function works. This function allows us to find all the words starting with the given prefix in trieST. This function takes the queue returned from the keywithprefix () method in the TrieST.java class and adds this data to the buffer array-list. Buffer array-list is a global and temporary array-list that we created in this class. All functions will use this array-list. The purpose of this is to sort and print all the words we obtain in a lexicographic way. We also have a separate print () function for the print operation.

<a href="url"><img src="https://user-images.githubusercontent.com/75734949/161336236-44f19a8e-7cd2-4d10-8892-e17e18343fdc.png" align="left" height="75" width="300" ></a>

<a href="url"><img src="https://user-images.githubusercontent.com/75734949/161336294-338d8295-fb50-4770-8129-6c6d586ce4b7.png" align="left" height="175" width="300" ></a>

<a href="url"><img src="https://user-images.githubusercontent.com/75734949/161336353-c51caf48-5158-473c-a7ea-685c2a4a4b58.png" align="left" height="175" width="350" ></a>
<br/>
- If the user enters an input such as ```<input1.txt> <3> <word>```, **the reverseAutoComplete (String suffix)** function works. First take reversed version of taken input because we must look this given word to reversedTrieST. To find a suffix, we reversed the tried and tested words. We reversed the suffix we received from the user. Let's make a correct prefix call inside reversedTrieST. Basically, we did a reversedTrieST ad prefix search, but this kind of implementation made it look like we were searching for suffix in the original trie. The **reversedAutoComplete (String suffix)** function calls * *keysWithPrefix ()* * on the generated reversedTrieST. It adds the data of the returning queue to the buffer array-list. However, since our data came in the reversed way during the insertion process, it converts them back to their original state with the reverseString function.

<a href="url"><img src="https://user-images.githubusercontent.com/75734949/161337220-abcfad84-397a-4fd8-b036-e05d7cd122c2.png" align="left" height="80" width="350" ></a>

<a href="url"><img src="https://user-images.githubusercontent.com/75734949/161337308-e7a6fa44-e81c-4cd2-9907-867e60d4ec12.png" align="middle" height="150" width="350" ></a>








