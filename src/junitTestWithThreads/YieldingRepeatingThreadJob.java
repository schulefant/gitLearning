package junitTestWithThreads;

public class YieldingRepeatingThreadJob extends RepeatingThreadJob {

	@Override
	public void run() {
		for(int i=1; i <=15; i++){
			System.out.println(super.getJobName() +" RUNNING Yielding ROUND " +i);
			Thread.yield();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public YieldingRepeatingThreadJob(String jobName) {
		super(jobName);
		// TODO Auto-generated constructor stub
	}

}
