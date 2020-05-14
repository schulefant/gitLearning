package withSychronized;

public class Point {
	
	private int x;
	private int y;

	synchronized public void setX(int x) {
		this.x = x;
	}

	synchronized public long getX() {
		return x;
	}

	synchronized public void setY(int y) {
		this.y = y;
	}

	synchronized public long getY() {
		return y;
	}
}
