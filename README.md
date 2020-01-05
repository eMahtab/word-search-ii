# Word Search II
## https://leetcode.com/problems/word-search-ii

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

```
Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
```

Note:
1. All inputs are consist of lowercase letters a-z.
2. The values of words are distinct.

## ⭐️⭐️ Prerequisite ⭐️⭐️
Before you try to solve this problem, you should definitely do the below 2 problems first
1. https://leetcode.com/problems/word-search - ([My Notes](https://github.com/eMahtab/word-search "My Notes"))
2. https://leetcode.com/problems/implement-trie-prefix-tree - ([My Notes](https://github.com/eMahtab/implement-trie "My Notes"))

## The Problem
This problem is very similar to the https://leetcode.com/problems/word-search problem, but with two changes. 
1. Now we are given an array of words (not a single word) to check which words exists in the board.
2. Finally we have to return the list of words which are found in the board. In the word search problem we were just returning boolean to represent if a word exists in the board or not.

**❗️ : Note that all the strings in the `words` array are distinct**


### Naive Solution  - Not so good 😓

We alreday know how to solve the word search problem, we can put a condition to add the word in the output list, if a word is found in the board. We will iterate over the given words array, so that all the words which are found in the board, will be added to output list. Although this approach will work, but it will be very time consuming, the reason is, we are initiating the entire search process again for every word in the `words` array. 

e.g. Suppose we are given an array of 6 words `["random", "minimal", "there", "oat", "the", "oath"]`, suppose we alreday found that the word `the` exists in the board. Now rather than starting an entirely new search for word `there`, can't we try to find `there` while we actually found `the` in the board, this way we can save a lot of time. And the time we save, will become more significant when the board is very large, and also when the given words are very long.

e.g. For example if we are given words `['centrifugal' , .... , 'centrifugalize', ....]`, if we found that the word `centrifugal` exists in the board, we can keep going and search for the word `centrifugalize` without losing our search progress. This way we will save a lot of time as we alreday found match for first 11 characters which is `centrifugal`, we will just have to find the match for next 3 characters `ize`.


## 😮 Lets try : Trie 😮

Trie is a perfect fit for solving this problem efficiently.

We will build a trie with the given words array, so lets define our `TrieNode` class 

```java
static class TrieNode {
		TrieNode[] children;
		String word;
		public TrieNode() {
			children = new TrieNode[26];
			word = null;
		}
}
```
**The `word` property reflects whether the `TrieNode` marks the end of a word or not. If word property is null, it means it doesn't mark the end of a word, otherwise if its not null it means, it marks the end of a word, and in that case we will set the `word` property to that particular word for which it marks the end.**


### Build Trie :
We will have a `buildTrie()` method which will build the Trie with all the given words.

```java
private static void buildTrie(TrieNode root, String[] words) {
		for(String s : words) {
			TrieNode node = root;
			for(char c : s.toCharArray()) {
				int index = c - 'a';
				if(node.children[index] == null) {
					node.children[index] = new TrieNode();
				}
				node = node.children[index];
			}
			node.word = s;
		}
}
```

From the `findWords()` we will call the `buildTrie()`

```java
public static List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        if(board == null || board[0].length == 0)
        	return res;
        
        TrieNode root = new TrieNode();
        buildTrie(root, words); // <---  calling buildTrie()
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
        	char c = board[i][j];
        	int index = c - 'a';
        	if(root.children[index] != null) {
        	   dfs(board, i, j, root, res); // <-- we are starting the search when first letter is matched
        	}
            }
        }
        
        return res;
 }
```

## DFS

```java
private static void dfs(char[][] board, int i, int j, TrieNode node, List<String> res) {
	if(node.word != null) {
	   res.add(node.word);
	   node.word = null; // <-- Once we find a match for a word in the board, we don't stop the search.
	}
		
	if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#') {
	   return;
	}
		
	char ch = board[i][j];
	int index = board[i][j] - 'a';
	if(node.children[index] == null) {
	   return;
	}
		
	board[i][j] = '#'; // <-- Important, we don't want to visit an already visited cell
	dfs(board, i, j + 1, node.children[index], res);
	dfs(board, i, j - 1, node.children[index], res);
	dfs(board, i - 1, j, node.children[index], res);
	dfs(board, i + 1, j, node.children[index], res);
		
	board[i][j] = ch; // <-- Once all 4 recursive calls return, set the original value
}
```

## Now lets test it 

```java
public static void main(String[] args) {
	char[][] board = {
			   {'o','a','a','n'},
 			   {'e','t','a','e'},
			   {'i','h','k','r'},
			   {'i','f','l','v'}
		     };
	String[] words = { "oath", "oat", "pea", "pear", "eat", "rain" };
	System.out.println(findWords(board, words));
}
```
#### Trie of words : ["oath", "oat", "pea", "pear", "eat", "rain"]

![Trie of words](trie.PNG?raw=true "Trie of words")


The output is `[oat, oath, eat]` which is correct.

## References
1. https://www.youtube.com/watch?v=5Ha1nJ5rjrE
2. https://github.com/eMahtab/word-search
