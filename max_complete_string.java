package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class main {

	public static class node{
		
		final String val;
		List<node> adj_list = new ArrayList<>();
		boolean end_word = false;
		
		node(String val){
			for(int i = 0;i < 26;i++){
				adj_list.add(null);
			}
			this.val = val;
		}
		
		
		public node get_next(String next){
			
			int idx = (int)next.toCharArray()[0] - (int)'a';
			
			return adj_list.get(idx);
			
		}
		
		public node add(String letter){
			
			int idx = (int)letter.toCharArray()[0] - (int)'a';
			
			if(adj_list.get(idx) == null){
				node tmp = new node(letter);
				adj_list.set(idx, tmp);
				return tmp;
			}
			return adj_list.get(idx);
			
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		List<String> a = new ArrayList<>(Arrays.asList("abcd","abc","kdbabcd","ab","a","kd","kdb"));
		
		System.out.println(max_complete_string(a));
	
	}
	
	private static String max_complete_string(List<String> a){
		
		node root = new node(null);
		
		//put the strings in a in the tree
		
		for(int i = 0;i < a.size();i++){
			
			node itr = root;
			
			String word = a.get(i);
			int len = word.length();
			
			for(int j = 0;j < len;j++){
				
				String x = word.substring(j,j+1);
				node tmp = itr.add(x);
				if(j+1 == len){
					tmp.end_word = true;
				}
				itr = tmp;
				
			}
			
		}
		
		//search for the max complete string
		int max = 0;
		String max_complete = null;
		for(int i = 0;i < a.size();i++){
			
			String word = a.get(i);
			int len = word.length();
			node itr = root;
			boolean complete = true;
			//check if word is complete
			for(int j = 0;j < len;j++){
				
				String x = word.substring(j,j+1);
				
				node tmp = itr.get_next(x);
				
				if(!tmp.end_word){
					//there is a prefix that is not present in the tree
					complete = false;
					break;
				}
				itr = tmp;
			}
			if(complete && len > max){
				max = len;
				max_complete = word;
			}
			
		}
		
		
		return max_complete;
	}
	
}
