package accountManagment;

public class Account {
	private double balance;
	
	Account (double total){
		balance = total;
	}
	
	/* Getter */
	public double getBalance(){
		return balance;
	}
	
	/* Setter */
	// Set is not possible: Use withdraw() /deposit() instead
	
	public void /*b) synchronized*/withdraw (double amount){
		if (balance >= amount){
			System.out.println(Thread.currentThread().getName() + " m�chte Geld abheben.");
			try {Thread.sleep(500);} catch (Exception e){}
			balance -= amount;
			System.out.println("Betrag von " + amount + " � erfolgreich abgehoben.");
			printTotal();
		} else {
			System.out.println("Das Konto weist nicht gen�gend Deckung auf!");
		}
	}
	
	public void deposit (double amount){
		balance += amount;
		System.out.println("Betrag von " + amount + " � erfolgreich eingezahlt.");
		printTotal();
	}
	
	public void printTotal(){
		System.out.println("Der Kontostand betr�gt " + balance + " �.");
	}
}
