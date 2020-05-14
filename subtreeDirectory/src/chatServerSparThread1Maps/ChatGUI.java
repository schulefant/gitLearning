package chatServerSparThread1Maps;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChatGUI implements ActionListener{
	private JButton btnSend = new JButton("Send");
	private JTextArea taReceived = new JTextArea();
	private JTextField write = new JTextField();
	private NewsCallback callback;
	private String message;
	
	public ChatGUI(String name) {
		JFrame chatFenster = new JFrame(name+"'s Chat Window");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel("Deine Nachricht:"),BorderLayout.WEST);
		panel.add(write);
		chatFenster.add(panel, BorderLayout.NORTH);
		taReceived.setSize(500, 200);
		chatFenster.add(taReceived, BorderLayout.CENTER);
		chatFenster.add(btnSend, BorderLayout.SOUTH);
		this.btnSend.addActionListener(this);
		chatFenster.setSize(400, 400);
//		chatFenster.pack();
		chatFenster.setVisible(true);
	}
	public void showNews(String news){
		this.taReceived.append(news+'\n');
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		message = this.write.getText();
		this.write.setText("");
		callback.callNewsUpdate();
	}
//	public NewsCallback getCallback() {
//		return callback;
//	}
	public void setCallback(NewsCallback callback) {
		this.callback = callback;
	}
	public String getMessage(){
		return message;
	}
	
	

}
