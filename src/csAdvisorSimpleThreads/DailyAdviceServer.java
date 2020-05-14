package csAdvisorSimpleThreads;

import java.io.*;
import java.net.*;

public class DailyAdviceServer {

	private InetAddress serverIP ;
	private final int NUM_ADVICES = 3;
	private String[] adviceList = { "Iss langsam.", "Zeit für einen Haarschnitt.",
			"Nur ein Wort: UNANGEMESSEN!",
			"Sei ehrlich - wenn auch nur heute ;-)",
			"Sag Deinem Chef, was Du wirklich denkst.",
			"Geh das nächste mal zu einem anderen Friseur." };
	private PrintWriter antwortWriter;
	private ServerSocket serverSocket;

	public static void main(String[] args) {

		DailyAdviceServer server = new DailyAdviceServer();
		server.go();
	}

	private String getAdvice() {
		int nr = (int)( Math.random() * adviceList.length);
		return adviceList[nr];
	}

	private void go() {

		try {
			serverSocket = new ServerSocket(4242);
			serverIP = InetAddress.getLocalHost();
			System.out.println("ServerIP ist " + serverIP);
			
			while(true){
				System.out.println("ServerSocket wartet auf Anfragen.");
				Socket antwortSocket = serverSocket.accept();
				System.out.println("Anfrage von IP " + antwortSocket.getInetAddress()+" Port "+ antwortSocket.getPort() +" angenommen.");
				
				antwortWriter = new PrintWriter( antwortSocket.getOutputStream()) ;

				Thread t = new Thread( new Runnable(){
					@Override
					public void run() {
						for(int i=0; i < NUM_ADVICES; i++){
							//println statt print sorgt dafür, dass nicht alles auf einmal gesendet wird.
							antwortWriter.println( getAdvice());
							antwortWriter.flush();
						}
					}
				});
				t.start();

					/* close() sorgt dafür, dass der Stream null zurückgibt, wenn 
				 * readLine() am anderen Ende aufgerufen wird.
				 * antwortWriter.flush() schickt die Texte, aber schließt nicht, 
				 * also wird auch kein null zurückgegeben
				 */
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			antwortWriter.close();
		}

	}

}
