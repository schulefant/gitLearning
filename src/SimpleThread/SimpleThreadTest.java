package SimpleThread;

public class SimpleThreadTest {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(
				new MyRunnable(1,50), "Positiv");
		Thread t2 = new Thread(
				new MyRunnable(-50,0), "Negativ");
		
		t1.start();
		t2.start();
	}
}
