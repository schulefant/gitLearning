package chatServerSammelThread;

import java.io.*;
import java.net.*;

public class ChatClient implements Runnable{
	
	String name;
	BufferedReader reader;
	PrintWriter writer;
	String serverIP = "10.151.33.71";

	public ChatClient(String name) {
		this.name=name;
	}

	public static void main(String[] args) {
		
		for ( int i=0; i < 3; i++){
			ChatClient cc = new ChatClient(" "+i+" ");
			cc.go();
		}

	}

	public void go() {
		
		this.setUpNetworking();
		Thread t = new Thread( this );
		t.start();
		System.out.println("Receiving messages possible.");
		
		while(true){
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//			Scanner input = new Scanner(System.in);
			System.out.println("You want to say something? ");
			String bla="";
			try {
				bla = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.println(bla);
			writer.flush();
		}
		
		
	}
	public void setUpNetworking(){
		try {
			Socket chatSocket = new Socket(serverIP, 3333);
			reader = new BufferedReader(new InputStreamReader(
					chatSocket.getInputStream()));
			writer = new PrintWriter( chatSocket.getOutputStream());
			System.out.println("Connected to Server.");
			chatSocket.close();
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
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
