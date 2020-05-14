package chatServerSammelThread;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.*;

public class ChatServer {

	private BlockingQueue<String> messages = new LinkedBlockingQueue<String>();
	private ClientReadHandler readHandler = new ClientReadHandler();
	private ClientWriteHandler writeHandler = new ClientWriteHandler();
	private ServerSocket ss;// ss will be closed, when ChatServer is stopped

	public static void main(String[] args) {

		ChatServer cs = new ChatServer();
		cs.go();
	}

	public void go() {

		try {
			ss = new ServerSocket(3333);
			InetAddress serverIP = InetAddress.getLocalHost();
			System.out.println("ServerIP ist " + serverIP);
			
			Thread readThread = new Thread(readHandler);
			readThread.start();
			System.out.println("readThread started");
			Thread writeThread = new Thread(writeHandler);
			writeThread.start();
			System.out.println("writeThread started");

			while (true) {
				System.out.println("Waiting for Contact");
				Socket newChatClient = ss.accept();
				writeHandler.addClient(newChatClient);
				readHandler.addClient(newChatClient);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class ClientReadHandler implements Runnable {
		List<BufferedReader> readers = new ArrayList<BufferedReader>();

		public void addClient(Socket s) {
			try {

				readers.add(new BufferedReader(new InputStreamReader(s
						.getInputStream())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void run() {
			String news = "";
			while (true) {
				for (BufferedReader reader : readers) {
					try {
						if ((news = reader.readLine()) != null) {
							System.out.println("Message received");
							messages.put(news);
						}// if
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}// try
				}// for
			}// while(true
		}// run()
	}// class ClientReadHandler

	class ClientWriteHandler implements Runnable {
		ArrayList<PrintWriter> myClients = new ArrayList<PrintWriter>();

		public void addClient(Socket s) {
			try {
				myClients.add(new PrintWriter(s.getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void mailAll(String news) {

			for (PrintWriter pw : myClients) {
				pw.write(news);
				pw.flush();
			}
		}

		public void run() {
			while (true) {
				try {
					String news = messages.take();
					mailAll(news);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// try

			}// while (true)
		}// public void run()
	}

}
