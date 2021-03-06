package chatServerSimpleThreads;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class ChatClient implements Runnable{
	
	private String name;
	private BufferedReader reader;
	private PrintWriter writer;
	//Damit es keinen memory leak gibt, da er nicht geschlossen wird?:
	private Socket chatSocket;
	private String serverIP = "";
	private ChatGUI view;

	public ChatClient(String ip, String name) {
		this.name=name;
	}

	public static void main(String[] args) {
		String ip = JOptionPane.showInputDialog("Server IP?");
		String name = JOptionPane.showInputDialog("Dein Chat Name:");
		
			ChatClient cc = new ChatClient(ip, name);
			cc.go();
	}

	public void go() {
		
		this.setUpNetworking();
		Thread t = new Thread( this );
		t.start();
		System.out.println("Receiving messages possible.");
		view = new ChatGUI(this.name);
		
		//Hier wird jetzt das passende Callback erstellt!
		view.setCallback( new NewsCallback(){

			@Override
			public void callNewsUpdate() {
				writer.println(name +": "+ view.getMessage());
				writer.flush();
				
			}
			
		});

//CONSOLE CHATWRITER		
//		while(true){
//			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
////			Scanner input = new Scanner(System.in);
//			System.out.println("You want to say something? ");
//			String bla="";
//			try {
//				bla = in.readLine();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			writer.println(bla);
//			writer.flush();
//		}
		
		
	}
	public void setUpNetworking(){
		try {
			chatSocket = new Socket(serverIP, 3333);
			reader = new BufferedReader(new InputStreamReader(
					chatSocket.getInputStream()));
			writer = new PrintWriter( chatSocket.getOutputStream());
			System.out.println("Connected to Server.");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void run(){
		String news;
		try {
			while((news = reader.readLine())!=null ){
				System.out.println("New Message: "+news);
				this.view.showNews(news);
			}
		} catch(SocketException e){
			view.showNews("Sorry, der Server hat sich verabschiedet.");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				chatSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
