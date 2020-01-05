package net.mahtabalam;

import java.util.ArrayList;
import java.util.List;

public class App {

	public static void main(String[] args) {
		char[][] board = {
				{'o','a','a','n'},
 				{'e','t','a','e'},
				{'i','h','k','r'},
				{'i','f','l','v'}
		};
		String[] words = { "oath","oat" , "pea","pear", "eat","rain" };
		System.out.println(findWords(board, words));

	}
	
	static class TrieNode {
		TrieNode[] children;
		String word;
		public TrieNode() {
			children = new TrieNode[26];
			word = null;
		}
	}
	
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        if(board == null || board[0].length == 0)
        	return res;
        
        TrieNode root = new TrieNode();
        buildTrie(root, words);
        
        for(int i = 0; i < board.length; i++) {
        	for(int j = 0; j < board[0].length; j++) {
        		char c = board[i][j];
        		int index = c - 'a';
        		if(root.children[index] != null) {
        			dfs(board, i, j, root, res);
        		}
        	}
        }
        
        return res;
    }

	private static void dfs(char[][] board, int i, int j, TrieNode node, List<String> res) {
		if(node.word != null) {
			res.add(node.word);
			node.word = null;
		}
		
		if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#') {
			return;
		}
		
		char ch = board[i][j];
		int index = board[i][j] - 'a';
		if(node.children[index] == null) {
			return;
		}
		
		board[i][j] = '#';
		dfs(board, i, j + 1, node.children[index], res);
		dfs(board, i, j - 1, node.children[index], res);
		dfs(board, i - 1, j, node.children[index], res);
		dfs(board, i + 1, j, node.children[index], res);
		
		board[i][j] = ch;
	}

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
	
}
