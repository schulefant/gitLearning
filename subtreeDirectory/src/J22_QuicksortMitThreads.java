public class J22_QuicksortMitThreads {

	static final int MAX_THREADS = Runtime.getRuntime().availableProcessors() ;
	static int num_threads = 1;


	public static void quicksort(final int[] zahlenFeld, final int links,
			final int rechts) {

		// 3-Median Strategie
		int mitte = (rechts + links) / 2;
		if (zahlenFeld[links] < zahlenFeld[mitte]
				&& zahlenFeld[mitte] < zahlenFeld[rechts]) {
			swap(zahlenFeld, rechts, mitte);
		} else if (zahlenFeld[mitte] < zahlenFeld[links]
				&& zahlenFeld[links] < zahlenFeld[rechts]) {
			swap(zahlenFeld, rechts, links);
		}

		int pivot = zahlenFeld[rechts]; // Nur damit der Code lesbarer wird
		// Durchläufe
		int i = links, j = rechts - 1;
		while (i <= j) {
			// Suche linke Tauschposition
			while (i < rechts && zahlenFeld[i] <= pivot)
				i++;
			// Suche rechte Tauschposition
			while (j >= links && zahlenFeld[j] >= pivot)
				j--;
			// Wenn Durchlauf noch nicht beendet
			if (i < j) {
				swap(zahlenFeld, i, j);
			}
		}
		// Setze Pivotelement in die Mitte (endgültige Position)
		swap(zahlenFeld, rechts, i);

		// Rekursive Aufrufe:

		final int newRechts = i - 1, newLinks = i + 1;

//		System.out.println("Aufruf linke Teilfolge in einem neuen Thread: "
//				+ "links: " + links + " newRechts: " + newRechts);

		Thread linkerThread = null;
		if (links < newRechts) {
			if (MAX_THREADS > num_threads) {
				num_threads++;
				// Hier entsteht der neue Thread
				linkerThread = new Thread() {
					public void run() {
						quicksort(zahlenFeld, links, newRechts);

					}
				};
				linkerThread.start();
			} else
				quicksort(zahlenFeld, links, newRechts);
		}
//		System.out.println("Aufruf rechte Teilfolge: " + "newLinks: "
//				+ newLinks + " rechts: " + rechts);
		if (newLinks < rechts) {
			quicksort(zahlenFeld, newLinks, rechts);
		}
		// Vor der Ausgabe sollte der linke Thread auch fertig sein
		if (linkerThread != null)
			try {
				linkerThread.join();
				num_threads--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		ausgabeFeld(zahlenFeld);
//		System.out.println("Active Threads: " + Thread.activeCount());
//		System.out.println("Started Threads for Quicksort: " + num_threads);
	}

	public static void swap(int[] zahlenFeld, int i, int j) {
		int temp = zahlenFeld[i];
		zahlenFeld[i] = zahlenFeld[j];
		zahlenFeld[j] = temp;
	}
}
