import java.util.Random;
import java.io.*;
public class J22_QuicksortZurBearbeitung {

	/**
	 * @param args
	 */
//	public static void main(String[] args) throws IOException{
//
//		int anzahl;
//		int zahlenFeld[];
//		BufferedReader eingabeTool = new BufferedReader(new InputStreamReader( System.in));
//		System.out.print("Wie viele Zahlen erzeugen?");
//		anzahl = Integer.parseInt(eingabeTool.readLine());
//		zahlenFeld= new int[anzahl];
//		System.out.println("QuickSort");
//		feldFuellen(zahlenFeld);
//		ausgabeFeld(zahlenFeld);
//		quicksort(zahlenFeld, 0, zahlenFeld.length-1);
//		ausgabeFeld(zahlenFeld);
//
//	}
//	public static void feldFuellen(int[]zahlenFeld){
//		Random zahlenMaschine = new Random();
//		//Feld mit Zufallszahlen fuellen
//		for(int i = 0; i < zahlenFeld.length; i++)
//			zahlenFeld[i] = zahlenMaschine.nextInt(1000);
//	}
//
//	public static void ausgabeFeld(int[]zahlenFeld){
//		for(int i = 0; i < zahlenFeld.length; i++)
//			System.out.print(zahlenFeld[i] + " ");
//		System.out.println();
//	}
	public static void quicksort( int [] zahlenFeld, int links, int rechts){
		
		//3-Median Strategie
		int mitte = (rechts + links)/2;
		if( zahlenFeld[links] < zahlenFeld[mitte] && zahlenFeld[mitte] < zahlenFeld[rechts]){
			swap(zahlenFeld, rechts, mitte);
		}else if( zahlenFeld[mitte] < zahlenFeld[links] && zahlenFeld[links] < zahlenFeld[rechts]){
			swap(zahlenFeld, rechts, links);
		}
		
		int pivot = zahlenFeld[rechts]; // Nur damit der Code lesbarer wird
		//Durchläufe
		int i = links, j = rechts-1;
		while( i <= j ){
			//Suche linke Tauschposition
			while( i<rechts && zahlenFeld[i] <= pivot)
				i++;
			//Suche rechte Tauschposition
			while( j >= links && zahlenFeld[j] >= pivot)
				j--;
			//Wenn Durchlauf noch nicht beendet
			if( i < j ){
				swap( zahlenFeld, i, j);
			}
		}
		//Setze Pivotelement in die Mitte (endgültige Position)
		swap(zahlenFeld, rechts, i);
		
		// Rekursive Aufrufe:
		
		int newRechts = i-1, newLinks = i+1;
		
//		System.out.println("Aufruf linke Teilfolge: "+"links: "+ links + " newRechts: " + newRechts);
		
		if( links < newRechts){
			quicksort ( zahlenFeld, links, newRechts);
		}
//		System.out.println("Aufruf rechte Teilfolge: "+"newLinks: " +newLinks+ " rechts: " + rechts);
		if( newLinks < rechts){
			quicksort( zahlenFeld, newLinks, rechts);
		}
//		ausgabeFeld(zahlenFeld);
	}
	
	public static void swap(int [] zahlenFeld, int i, int j){
		int temp = zahlenFeld[i];
		zahlenFeld[i] = zahlenFeld[j];
		zahlenFeld[j] = temp;
	}
}
