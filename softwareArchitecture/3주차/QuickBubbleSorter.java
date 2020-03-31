package bubblesort;

import java.util.Arrays;
import java.util.Scanner;

public class QuickBubbleSorter extends BubbleSorter {
private int[] array = null;
	
	public int sort(int[] theArray) {
		array = theArray;
		length = array.length;
		return doSort();
	}
	
	protected void swap(int index) {
		int temp = array[index];
		array[index] = array[index+1];
		array[index+1] = temp;
		
		System.out.println(Arrays.toString(array));
	}
	
	protected boolean outOfOrder(int index) {
		return (array[index]>array[index+1]);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] array2 = new int[n];
		for(int i=0;i<n;i++)
			array2[i] = sc.nextInt();
		
		(new QuickBubbleSorter()).sort(array2);
	}
}
