import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class Window extends JFrame {	

	private Browser html;
	public JTextField addressBar;
	private JButton btnGo, btnBack, btnForward, btnHome, btnRefresh;
	private final Dimension MINIMUM_SIZE = new Dimension(800, 600);
	private ArrayList<String> history;
	private static int currentPageIndex;
	private static int originalPageIndex;
	
	public Window() {
		history = new ArrayList<String>();

		this.setTitle("Glenn is gay");
		String homeURL = "http://www.java.com/";

		history.add(homeURL);


		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);

		btnBack = new JButton("<");
		btnForward = new JButton(">");
		btnHome = new JButton("Home");
		btnRefresh = new JButton("Refresh");
		addressBar = new JTextField(homeURL, 30);
		btnGo = new JButton("GO");

		btnForward.setEnabled(false);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(btnBack);
		panel.add(btnForward);
		panel.add(btnHome);
		panel.add(btnRefresh);
		panel.add(addressBar);
		panel.add(btnGo);


		html = new Browser(homeURL);
		
		html.setEditable(false);

		html.addHyperlinkListener(
				new HyperlinkListener() {
					public void hyperlinkUpdate(HyperlinkEvent click) {
						if (click.getEventType()==HyperlinkEvent.EventType.ACTIVATED) {
							loadURL(click.getURL().toString());
						}
					}
				}
				);


		btnGo.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						loadURL(addressBar.getText());
					}
				}
				);
		
		btnRefresh.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						html.refreshPage();
					}
				}
				);

		addressBar.addActionListener(	
				new ActionListener() {
					public void actionPerformed(ActionEvent enter) {
						loadURL(addressBar.getText());
					}
				}	
				);     
		
		btnHome.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						//HistoryWindow h = new HistoryWindow(history);
						writeHistory();
					}
				}
				);
		
		btnBack.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						backPage();
					}
				}
				);

		JScrollPane sp = new JScrollPane(html);
		//    frame.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(panel, BorderLayout.NORTH);
		this.getContentPane().add(sp, BorderLayout.CENTER);

		this.setMinimumSize(MINIMUM_SIZE);
		this.setResizable(true);
		this.setVisible(true);

	}
	
	public void loadURL(String url) {
		html.loadURL(url);
		addressBar.setText(url);
		addToHistory(url);
	}
	
	public void backPage() {
		loadURL(history.get(history.size()-2));
	}
	
	public void forwardPage() {
		if (!btnForward.isEnabled()) {
			btnForward.setEnabled(true);
		}
	}
	
	public void addToHistory(String url) {
		int size = history.size();
		boolean exists = false;
		for (int i = 0; i < size; i++) {
			if (history.get(i).equals(url)) {
				exists = true;
			}
		}
		if (!exists) {
			history.add(url);
		}

	}
	
	public static void addToFavourites() {
		System.out.println("add");
	}
	
	public static void clearFavourites() {
		System.out.println("clear");
	}
	
	public void writeHistory() {
		//PrintWriter f = null;
		try {
			PrintWriter f = new PrintWriter("src/history.txt");
			f.println(history.get(0));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		/*
		for(int i = 0; i < history.size(); i++) {
			f.println(history.get(i));
		} */
		
		
	}

}