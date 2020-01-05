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


😮 Lets try : Trie 😮


