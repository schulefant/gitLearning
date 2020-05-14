package junitTestWithThreads;

public class RepeatingThreadJob extends ThreadJob {

	@Override
	public void run() {
		
		for(int i=1; i <=15; i++){
			super.run();
			System.out.println("RUNNING ROUND " +i);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public RepeatingThreadJob(String jobName) {
		super(jobName);
		// TODO Auto-generated constructor stub
	}

}
