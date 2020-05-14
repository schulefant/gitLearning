import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


public class QuicksortVergleich {

	public QuicksortVergleich() {
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		int anzahl;
		final int zahlenFeldFuerThreaded[];
		final int zahlenFeldFuerUnThreaded[];
		BufferedReader eingabeTool = new BufferedReader(new InputStreamReader( System.in));
		System.out.print("Wie viele Zahlen erzeugen? ");
		anzahl = Integer.parseInt(eingabeTool.readLine());
		zahlenFeldFuerThreaded= new int[anzahl];
		zahlenFeldFuerUnThreaded=new int[anzahl];
		feldFuellen(zahlenFeldFuerThreaded);
		feldFuellen(zahlenFeldFuerUnThreaded);

		System.out.println("QuickSortVergleich");
//		ausgabeFeld(zahlenFeldFuerThreaded);
//		ausgabeFeld(zahlenFeldFuerUnThreaded);
		final long timestampThreaded= System.currentTimeMillis();
		Thread qThreaded = new Thread(){
			public void run(){
				J22_QuicksortMitThreads.quicksort(zahlenFeldFuerThreaded, 0, zahlenFeldFuerThreaded.length-1);
			}
		};
		qThreaded.start();
		final long timestampUnthreaded= System.currentTimeMillis();;
		J22_QuicksortZurBearbeitung.quicksort(zahlenFeldFuerUnThreaded, 0, zahlenFeldFuerUnThreaded.length-1);
		System.out.println("UNTHREADED fertig:");
		System.out.println("Zeit in Millis: " +(System.currentTimeMillis()-timestampUnthreaded));
		ausgabeFeld(zahlenFeldFuerUnThreaded);
		try {
			qThreaded.join();
			System.out.println("THREADED fertig:");
			System.out.println("Zeit in Millis: " +(System.currentTimeMillis()-timestampThreaded));
			ausgabeFeld(zahlenFeldFuerThreaded);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public static void feldFuellen(int[] zahlenFeld) {
		Random zahlenMaschine = new Random();
		// Feld mit Zufallszahlen fuellen
		for (int i = 0; i < zahlenFeld.length; i++)
			zahlenFeld[i] = zahlenMaschine.nextInt(1000);
	}

	public static void ausgabeFeld(int[] zahlenFeld) {
//		System.out.println("Active Threads: " + Thread.activeCount());
//		System.out.println("Started Threads for Quicksort: " + num_threads);
		for (int i = 0; i < zahlenFeld.length; i++)
			System.out.print(zahlenFeld[i] + " ");
		System.out.println();
	}

}
