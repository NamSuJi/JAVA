package bubblesort;

import java.util.Arrays;
import java.util.Scanner;

public class CharBubbleSorter extends BubbleSorter {
	private String[] array = null;
	
	public int sort(String[] theArray) {
		array = theArray;
		length = array.length;
		return doSort();
	}
	
	protected void swap(int index) {
		String temp = array[index];
		array[index] = array[index+1];
		array[index+1] = temp;
		
		System.out.println(Arrays.toString(array));
	}
	
	protected boolean outOfOrder(int index) {
		boolean result = false;
		if((array[index].compareTo(array[index+1]))>0) result = true;
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] arr = str.split("");
		
		(new CharBubbleSorter()).sort(arr);
	}

}
