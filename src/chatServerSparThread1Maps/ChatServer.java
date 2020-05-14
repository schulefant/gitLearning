package chatServerSparThread1Maps;

import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class ChatServer {

	Map<String, PrintWriter> myClients = new HashMap<String, PrintWriter>();
	// ss will be closed, when ChatServer is stopped
	private ServerSocket ss;

	public static void main(String[] args) {

		ChatServer cs = new ChatServer();
		cs.go();

	}

	public void go() {

		try {
			ss = new ServerSocket(3333);
			System.out.println("IP des Servers: " + InetAddress.getLocalHost());
			while (true) {
				Socket newChatClient = ss.accept();
				System.out.println("New Client at IP "
						+ newChatClient.getInetAddress() + " and Port "
						+ newChatClient.getPort());
				String name = new BufferedReader(new InputStreamReader(
						newChatClient.getInputStream())).readLine();
				myClients.put(name,
						new PrintWriter(newChatClient.getOutputStream()));

				/*
				 * Für jeden neuen Client wird ein neuer Thread angelegt. So
				 * kann der Server sofort neue Anfragen annehmen.
				 */
				Thread chatThread = new Thread(new ClientHandler(newChatClient));
				chatThread.start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void mailAll(String news) {

		for (Map.Entry<String,PrintWriter > entry : myClients.entrySet()) {
			PrintWriter pw = entry.getValue();
			pw.println(news);
			pw.flush();
		}

//		for (PrintWriter pw : myClients) {
//			pw.println(news);
//			pw.flush();
//		}
	}

	class ClientHandler implements Runnable {
		BufferedReader reader;

		public ClientHandler(Socket s) {
			try {

				reader = new BufferedReader(new InputStreamReader(
						s.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void run() {
			String news;
			try {
				while ((news = reader.readLine()) != null) {

					mailAll(news);

				}
			} catch (SocketException e) {
				System.out.println("Ein Client hat sich verabschiedet");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
