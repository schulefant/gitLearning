package SimpleThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InterruptSnippet {

	public static void main(String[] args) throws IOException {
		Thread t = new Thread(() -> {
			int counter = 0;
			while (!Thread.currentThread().isInterrupted()) {
				counter++;
				System.out.println(counter);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		t.start();
		System.out.println("Geben Sie Stop ein:");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while(!"stop".equals(input.readLine()))
			;
		t.interrupt();
		System.out.println("ENDE");
	}
}
