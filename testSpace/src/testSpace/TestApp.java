package testSpace;

import java.util.ArrayList;

public class TestApp {

	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		for(int i = 0; i < 10; i++)
			array.add(i);
		
		for(int i : array) {
			if(i == 5) {
				array.remove(5);
			}
		}
		
		for(int i = 0; i < array.size(); i++) {
			if(array.get(i) == 5) {
				array.remove(array.get(i));
				i--;
			}
			System.out.println("num : " + array.get(i));
		}

	}

}
