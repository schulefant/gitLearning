package accountManagment;

public class AccountManagment implements Runnable {

	private Account account = new Account(100);
	
	@Override
	public void run() {
		for (int i = 0; i <= 10; i++){
			account.withdraw(10);
			if (account.getBalance() < 0){
				System.out.println("Konto wurde überzogen!");
			}
		}
	}

}
