package junitTestWithThreads;

public abstract class ThreadJob implements Runnable{

	private String jobName;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread Job: " +jobName);
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public ThreadJob(String jobName) {
		super();
		this.jobName = jobName;
	}

	
}
