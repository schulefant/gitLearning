package threadedQuicksort;

import java.util.Random;
import java.io.*;

public class QuicksortThreaded {

	private static Object lock = new Object();

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		int amount;
		int numArray[];
		BufferedReader inputTool = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Wie viele Zahlen erzeugen?");
		amount = Integer.parseInt(inputTool.readLine());
		numArray = new int[amount];

		System.out.println("QuickSort");
		fillArray(numArray);
		// printArray(numArray);
		quicksort(numArray, 0, numArray.length - 1);
		System.out.println("\nENDE");
		printArray(numArray);

	}

	public static void fillArray(int[] numArray) {
		Random numberMachine = new Random();
		// Feld mit Zufallszahlen fuellen
		for (int i = 0; i < numArray.length; i++)
			numArray[i] = numberMachine.nextInt(90) + 10;
	}

	public static void printArray(int[] numberArray) {
		for (int i = 0; i < numberArray.length; i++)
			System.out.print(numberArray[i] + " ");
		System.out.println();
	}

	public static synchronized void printArray(int[] numberArray, int from, int to) {
		if (to >= from) {
			System.out.println();
			// System.out.println("From " + from + " to "+to);
			for (int i = 0; i < from; i++)
				System.out.print("   ");
			for (int i = from; i <= to; i++)
				System.out.print(numberArray[i] + " ");
		}
	}

	public static void quicksort(int[] numberArray, int left, int right) {

//		printArray(numberArray, left, right);
		// 3-Median Strategie
		int middle = (right + left) / 2;
		if (numberArray[left] < numberArray[middle] && numberArray[middle] < numberArray[right]) {
			swap(numberArray, right, middle);
		} else if (numberArray[middle] < numberArray[left] && numberArray[left] < numberArray[right]) {
			swap(numberArray, right, left);
		}
		printArray(numberArray, left, right);

		int pivot = numberArray[right]; // Nur damit der Code lesbarer wird
		// Durchläufe
		int i = left, j = right - 1;
		while (i <= j) {
			// Suche linke Tauschposition
			while (i < right && numberArray[i] <= pivot)
				i++;
			// Suche rechte Tauschposition
			while (j >= left && numberArray[j] >= pivot)
				j--;
			// Wenn Durchlauf noch nicht beendet
			if (i < j) {
				swap(numberArray, i, j);
			}
		}
		// Setze Pivotelement in die Mitte (endgültige Position)
		swap(numberArray, right, i);

		// Rekursive Aufrufe:

		int newRight = i - 1, newLeft = i + 1;

		// System.out.print("\nLINKS: " + "links: " + left + " newRechts: " +
		// newRight);
//		printArray(numberArray, left, newRight);
		Thread t = new Thread(() -> {
			if (left < newRight) {
				quicksort(numberArray, left, newRight);
			}
		});
		t.start();
		// System.out.print("\nRECHTS: " + "newLinks: " + newLeft + " rechts: "
		// + right);
//		printArray(numberArray, newLeft, right);
		if (newLeft < right) {
			quicksort(numberArray, newLeft, right);
		}
		try {
			t.join();
			// System.out.println("Left Thread has joined");
		} catch (InterruptedException e) {
			System.out.println("INTERRUPTED");
			e.printStackTrace();
		}

	}

	public static void swap(int[] zahlenArray, int i, int j) {
		int temp = zahlenArray[i];
		zahlenArray[i] = zahlenArray[j];
		zahlenArray[j] = temp;
	}
}
