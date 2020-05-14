package junitTestWithThreads;

public class SimpleThreadJob extends ThreadJob {

	public SimpleThreadJob(String jobName) {
		super(jobName);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		super.run();
		System.out.println("RUNNING");
	}

}
