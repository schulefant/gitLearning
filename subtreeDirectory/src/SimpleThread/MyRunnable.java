package SimpleThread;

public class MyRunnable implements Runnable {

	private int runde, grenze;
	
	public MyRunnable(int runde, int grenze) {
		this.runde=runde;
		this.grenze = grenze;
	}
	public void run(){
		while( runde <=grenze ){
			System.out.println(
					Thread.currentThread().getName()
					+" Runde: "+runde++);
		}
	}
}
