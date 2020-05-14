package junitTestWithThreads;
public class ThreadTestMain {

	public static final int jobAnzahl = 2;
	public static final int workerAnzahl = 2;

	public static void main(String[] args) {
		ThreadJob[] jobs = new ThreadJob[jobAnzahl];
		jobs[0] = new YieldingRepeatingThreadJob("First Counter yielding");
		jobs[1] = new RepeatingThreadJob("Counting Job 2 - not yielding");

		Thread[] worker = new Thread[workerAnzahl];

		worker[0] = new Thread(jobs[1]);
		worker[1] = new Thread(jobs[0]);

		for (Thread tj : worker)
			tj.start();
		for (int i = 0; i < 10; i++){
			for (Thread tj : worker)
				System.out.println("Thread State:" + tj.getName()+" "+tj.getState());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
