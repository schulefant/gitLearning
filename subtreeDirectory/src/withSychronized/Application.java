package withSychronized;

public class Application {
	
	public static void main(String[] args) throws InterruptedException {

		Point p = new Point();
		
		NotSynchronizedThread t1 = new NotSynchronizedThread(p, 1, 2);
		NotSynchronizedThread t2 = new NotSynchronizedThread(p, 2, 1);
		
		t1.start();
		t2.start();
	}
}
