package chatServerSparThread1Maps;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class ChatClient implements Runnable {

	private String name;
	private PrintWriter writer;
	// Damit es keinen memory leak gibt, da er nicht geschlossen wird?:

	private ServerSocket contactSocket;
	private String serverIP = "";
	private ChatGUI view;

	public ChatClient(String ip, String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		String ip = JOptionPane.showInputDialog("Server IP?");
		String name = JOptionPane.showInputDialog("Dein Chat Name:");

		ChatClient cc = new ChatClient(ip, name);
		cc.go();
	}

	public void go() {

		this.setUpNetworking();
		Thread t = new Thread(this);
		t.start();
		System.out.println("Receiving messages possible.");
		view = new ChatGUI(this.name);
		// writer.println(this.name);
		// writer.flush();
		// Hier wird jetzt das passende Callback erstellt!
		view.setCallback(new NewsCallback() {

			@Override
			public void callNewsUpdate() {
				writer.println(name + ": " + view.getMessage());
				writer.flush();

			}

		});

		// CONSOLE CHATWRITER
		// while(true){
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(System.in));
		// // Scanner input = new Scanner(System.in);
		// System.out.println("You want to say something? ");
		// String bla="";
		// try {
		// bla = in.readLine();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// writer.println(bla);
		// writer.flush();
		// }

	}

	public void setUpNetworking() {
		try {
			this.contactSocket = new ServerSocket(1313);
			Socket initSocket = new Socket(serverIP, 3333);
			writer = new PrintWriter(initSocket.getOutputStream());
			InetAddress myIP = InetAddress.getLocalHost();
			System.out.println("Connected to Server.");
			writer.println(myIP);
			writer.flush();
			System.out.println(myIP + " sent to Server.");
			initSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		String news;
		try {
			while (true) {
				Socket chatSocket = this.contactSocket.accept();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						chatSocket.getInputStream()));

				if ((news = reader.readLine()) != null) {
					System.out.println("New Message: " + news);
					this.view.showNews(news);
				}
			}
		} catch (SocketException e) {
			view.showNews("Sorry, der Server hat sich verabschiedet.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				initSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
