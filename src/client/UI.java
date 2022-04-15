package client;

import org.json.simple.JSONObject;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UI extends Thread {

	private JFrame frmDictionary;
	private JTextField title;
	private JTextField word;
	private JTextField meaning;
	private Client client;

	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI(client);
					window.frmDictionary.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public UI(Client client) {
		this.client = client;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDictionary = new JFrame();
		frmDictionary.setResizable(false);
		frmDictionary.setTitle("Dictionary");
		frmDictionary.setBounds(100, 100, 450, 300);
		frmDictionary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDictionary.getContentPane().setLayout(null);
		
		title = new JTextField();
		title.setBorder(null);
		title.setBackground(new Color(240,240,240));
		title.setFont(new Font("Arial Black", Font.PLAIN, 15));
		title.setText("This is a dictionary");
		title.setEditable(false);
		title.setBounds(130, -1, 171, 36);
		frmDictionary.getContentPane().add(title);
		title.setColumns(10);
		
		word = new JTextField();
		word.setBounds(10, 80, 120, 36);
		frmDictionary.getContentPane().add(word);
		word.setColumns(10);
		
		meaning = new JTextField();
		meaning.setBounds(10, 126, 120, 85);
		frmDictionary.getContentPane().add(meaning);
		meaning.setColumns(10);

		JTextArea display = new JTextArea();
		display.setEditable(false);
		display.setBounds(153, 80, 271, 131);
		frmDictionary.getContentPane().add(display);





		JButton query = new JButton("QUERY");
		query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JSONObject msg = new JSONObject();
				msg.put("action", "Query");
				msg.put("word", word.getText());

				JSONObject reply = client.send(msg);
				if (reply.get("error") != null) {
					display.setText((String) reply.get("error"));
				} else {
					display.setText((String) reply.get("meaning"));
				}




			}
		});
		query.setBounds(10, 45, 93, 23);
		frmDictionary.getContentPane().add(query);



		JButton add = new JButton("ADD");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JSONObject msg = new JSONObject();
				msg.put("action", "Add");
				msg.put("word", word.getText());
				msg.put("meaning", meaning.getText());

				JSONObject reply = client.send(msg);
				if (reply.get("error") != null) {
					display.setText((String) reply.get("error"));
				} else {
					display.setText((String) reply.get("msg"));
				}
			}
		});
		add.setBounds(113, 45, 93, 23);
		frmDictionary.getContentPane().add(add);



		JButton remove = new JButton("REMOVE");
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JSONObject msg = new JSONObject();
				msg.put("action", "Remove");
				msg.put("word", word.getText());

				JSONObject reply = client.send(msg);
				if (reply.get("error") != null) {
					display.setText((String) reply.get("error"));
				} else {
					display.setText((String) reply.get("msg"));
				}
			}
		});
		remove.setBounds(228, 45, 93, 23);
		frmDictionary.getContentPane().add(remove);
		
		JButton update = new JButton("UPDATE");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JSONObject msg = new JSONObject();
				msg.put("action", "Update");
				msg.put("word", word.getText());
				msg.put("meaning", meaning.getText());

				JSONObject reply = client.send(msg);
				if (reply.get("error") != null) {
					display.setText((String) reply.get("error"));
				} else {
					display.setText((String) reply.get("msg"));
				}
			}
		});
		update.setBounds(331, 45, 93, 23);
		frmDictionary.getContentPane().add(update);
		

	}
}
