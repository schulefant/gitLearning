package accountManagment;

public class Main {

	public static void main(String[] args) {
		
		AccountManagment management = new AccountManagment();
		
		Thread he  = new Thread (management, "Max");
		Thread she = new Thread (management, "Erika"); 
		
		he.start();
		she.start();
		
	}

}
