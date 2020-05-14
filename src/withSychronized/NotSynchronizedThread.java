package withSychronized;

public class NotSynchronizedThread extends Thread{
	
	private Point p;
	int x;
	int y;
	
	public NotSynchronizedThread(Point p, int x, int y) {
		this.p = p;
		this.x = x;
		this.y = y;
	}

	public void run() {
		p.setX(x);
		p.setY(y);
		
		System.out.println(getName() + " x: " + p.getX() + " y: " + p.getY()); 
	 }
}
