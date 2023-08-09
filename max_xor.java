package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class main {

	public static class node{
		
		Integer number = null;
		final String bit;
		List<node> adj_list = new ArrayList<>();
		
		node(String bit){
			
			this.bit = bit;
			
			for(int i = 0;i < 2;i++){
				adj_list.add(null);
			}
		}
		
		public node get_next(String bit){
			
			int idx = Integer.parseInt(bit);
			
			return adj_list.get(idx);
			
		}
		
		public node add(String bit){
			
			int idx = Integer.parseInt(bit);
			
			node tmp = new node(bit);
			
			if(this.get_next(bit) == null) {
				adj_list.set(idx, tmp);
				return tmp;
			}
			return adj_list.get(idx);
			
		}
		
		
		
		
	}
	
	public static void main(String[] args) {
		
		List<Integer> a = new ArrayList<>(Arrays.asList(6,8));
		List<Integer> b = new ArrayList<>(Arrays.asList(7,8,2));
		
		System.out.println(max_xor(a,b));
		/*node root = set_tree(b);
		System.out.println(max_xor(6,root));*/
	
	}
	
	private static String set_bit(int qnt,String num){
		//16 bits for now
		
		StringBuilder str = new StringBuilder();
		
		int len = qnt - num.length();
		
		for(int i = 0;i < len;i++){
			str.append(0);
		}
		str.append(num);
		return str.toString();
		
	}
	
	private static node set_tree(List<Integer> arr){
		
		node root = new node(null);
		
		
		for(int i = 0;i < arr.size();i++) {
			node itr = root;
			
			String num = set_bit(8,Integer.toBinaryString(arr.get(i)));
			
			//System.out.println(num);
			
			int len = num.length();
			
			for(int j = 0;j < len;j++){
				
				node tmp = itr.add(num.substring(j,j+1));
				
				if(j+1 == len){
					tmp.number = arr.get(i);
				}
				itr = tmp;
			}
			
			
		}
		
		return root;
	}
	
	private static int max_xor(int x,node root){
		
		
		String cmp = set_bit(8,Integer.toBinaryString(x));
		node itr = root;
		int num = -1;
		
		//System.out.println(cmp);
		
		for(int i = 0;i < cmp.length();i++){
			
			int idx = Integer.parseInt(cmp.substring(i,i+1));
			
			node path1 = itr.adj_list.get(0);
			node path2 = itr.adj_list.get(1);
			
			//System.out.println(itr.bit);
			
			if(path1 == null){
				itr = path2;
				if(itr != null && itr.number != null) {
					num = itr.number;
				}
			}
			else if(path2 == null){
				itr = path1;
				if(itr != null && itr.number != null) {
					num = itr.number;
				}
			}
			else{
				if(idx == 0) {
					if(itr != null && itr.number != null) {
						num = itr.number;
					}
					itr = path2;
				}
				else {
					if(itr != null && itr.number != null) {
						num = itr.number;
					}
					itr = path1;
				}
				
			}
			
		}
		num = itr.number;
		
		
		return num;
	}
	
	private static List<Integer> max_xor(List<Integer> a,List<Integer> b){
		
		node root = set_tree(b);
		
		int cmp = -1;
		int max = -1;
		for(int i = 0;i < a.size();i++){
			
			int curr = max_xor(a.get(i),root);
			if(curr > max) {
				max = curr;
				cmp = a.get(i);
			}
			
		}
		
		return new ArrayList<>(Arrays.asList(cmp,max));
	}
	
}
