package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class main_misc {

	
	public static class node{
		
		String val;
		List<node> adj_list = new ArrayList<>();
		
		node(String val){
			for(int i = 0;i < 26;i++){
				adj_list.add(null);
			}
			this.val = val;
		}
		
		public node get_next(String next){
			
			int idx = (int)next.toCharArray()[0] - 97;
			return adj_list.get(idx);
		}
		
		public node add(String letter){
			
			int idx = (int)letter.toCharArray()[0] - 97;
			node tmp = new node(letter);
			if(adj_list.get(idx) == null){
				adj_list.set(idx, tmp);
				return tmp;
			}
			return adj_list.get(idx);
		}
		
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(count_substrings("abab"));
	
	}
	private static int count_substrings(String str){
		
		int count = 0;
		
		int len = str.length();
		
		node root = new node(null);
		
		for(int i = 0;i < len;i++){
			
			node itr = root;
			
			for(int j = i;j < len;j++){
				
				String s = str.substring(j,j+1);
				node tmp = itr.get_next(s);
				if(tmp != null){
					itr = tmp;
					continue;
				}
				itr = itr.add(s);
				count++;
				
			}
			
		}
		return count;
	}
	
	
}
