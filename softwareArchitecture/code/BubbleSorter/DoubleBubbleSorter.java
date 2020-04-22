package bubblesort;

import java.util.Scanner;
import java.util.*;

public class DoubleBubbleSorter extends BubbleSorter{
	private double[] array = null;
	
	public int sort(double[] theArray) {
		array = theArray;
		length = array.length;
		return doSort();
	}
	
	protected void swap(int index) {
		double temp = array[index];
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
		double[] array2 = new double[n];
		for(int i=0;i<n;i++)
			array2[i] = sc.nextDouble();
		
		(new DoubleBubbleSorter()).sort(array2);
	}
}
