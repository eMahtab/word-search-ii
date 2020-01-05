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
