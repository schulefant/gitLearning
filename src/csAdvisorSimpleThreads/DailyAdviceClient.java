package csAdvisorSimpleThreads;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class DailyAdviceClient {

	BufferedReader adviceReader;
	String serverIP = "10.151.131.199";

	public static void main(String[] args) {

		DailyAdviceClient clientApp = new DailyAdviceClient();

		clientApp.go();
	}

	private void go() {
		 serverIP= JOptionPane.showInputDialog(this,
		 "IP des Advice Servers:");

		try {

			Socket requestingAdviceSocket = new Socket(serverIP, 4242);
			adviceReader = new BufferedReader(new InputStreamReader(
					requestingAdviceSocket.getInputStream()));

			String advice ;
			while( (advice = this.adviceReader.readLine() ) != null)
				System.out.println(advice);

			this.adviceReader.close();
			requestingAdviceSocket.close();

		} catch (UnknownHostException e) {
			System.out.println("Überprüfe IP und Port.");
			e.printStackTrace();
		}catch(ConnectException e){
			System.out.println("Server nicht gestartet. Try again later!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
