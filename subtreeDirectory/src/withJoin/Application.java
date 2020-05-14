package withJoin;

public class Application {

	public static void main(String[] args) {
		JoinerThread t = new JoinerThread(); 
		t.start(); 
		System.out.println( t.result ); 
		
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		System.out.println( t.result ); 
	}
}
