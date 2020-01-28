package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class list_duplication {
	public static void main(String args[]) throws Exception {
		List<String> expected = new ArrayList<String>();
		List<String> actual = new ArrayList<String>();
		
		
		expected.add("a");
		expected.add("b");
		expected.add("c");
		expected.add("e");
		expected.add("f");
		expected.add("a");
		
		actual.add("c");
		actual.add("b");
		actual.add("a");

		
		
//		for (int i=0; i < expected.size(); i++) {
//			actual.remove
//		}
		
		System.out.println("expected : " + expected);
		System.out.println("actual : " + actual);
		
		expected = actual.parallelStream().distinct().collect(Collectors.toList());
//		expected.removeAll(new HashSet(actual));
		
		
		System.out.println("expected : " + expected);
		System.out.println("actual : " + actual);
		
		
		
	}
	
}
