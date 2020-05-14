package chatServerSammelThreadMaps;

import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class ChatServer {

	Map<String, PrintWriter> myClients = new HashMap<String, PrintWriter>();
	ClientHandler chatHandler = new ClientHandler();
	// ss will be closed, when ChatServer is stopped
	private ServerSocket ss;

	public static void main(String[] args) {

		ChatServer cs = new ChatServer();
		cs.go();

	}

	public void go() {
		Thread chatThread = new Thread(chatHandler);
		chatThread.start();

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

				chatHandler.addClient(name, newChatClient);

				/*
				 * Für jeden neuen Client wird ein neuer Thread angelegt. So
				 * kann der Server sofort neue Anfragen annehmen.
				 */
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void mailAll(String news) {

		for (Map.Entry<String, PrintWriter> entry : myClients.entrySet()) {
			PrintWriter pw = entry.getValue();
			pw.println(news);
			pw.flush();
		}
	}

	class ClientHandler implements Runnable {
		// Die normale ArrayList produziert beim hinzufügen eine
		// ConcurrentModificationException!
		// löst das ConcurrentModificationException Problem auch nicht
		Map<String, BufferedReader> readers = new HashMap<String, BufferedReader>();

		public void addClient(String name, Socket s) {
			try {

				readers.put(
						name,
						new BufferedReader(new InputStreamReader(s
								.getInputStream())));
				System.out.println("New Reader added.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void run() {
			String news;
			try {
				while (true) {
					// synchronized (readers) {
					for (Map.Entry<String, BufferedReader> entry : readers
							.entrySet()) {
						BufferedReader br = entry.getValue();
						if (br.ready()) {
							news = br.readLine();
							mailAll(news);
						}
					}
				}

				// }// synchronized
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
