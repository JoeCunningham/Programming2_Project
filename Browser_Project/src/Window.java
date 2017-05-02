import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.*;

public class Window extends JFrame {	

	private Browser html;
	private JTextField addressBar;
	private JButton btnGo, btnBack, btnForward, btnHome, btnRefresh;
	private final Dimension MINIMUM_SIZE = new Dimension(800, 600);
	//private ArrayList<String> history;
	//private static int currentPageIndex;
	//private static int originalPageIndex;
	private String homeURL;
	private ArrayList<String> favourites;
	
	private BufferedWriter bw = null;
	private BufferedReader br = null;
	private File historyFile;
	private File homepageFile;
	private File favouritesFile;
	private static final String HISTORY_FILENAME = "/Users/joe/Desktop/history.txt";
	private static final String HOMEPAGE_FILENAME = "/Users/joe/Desktop/homepage.txt";
	private static final String FAVOURITES_FILENAME = "/Users/joe/Desktop/favourites.txt";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy   hh:mm");

	public Window() {
		
		//history = new ArrayList<String>();
		
		historyFile = new File(HISTORY_FILENAME);
		homepageFile = new File(HOMEPAGE_FILENAME);
		favouritesFile = new File(FAVOURITES_FILENAME);


		this.setTitle("browser");
		
		writeHistory(getHomeURL());

		btnBack = new JButton("<");
		btnForward = new JButton(">");
		btnHome = new JButton("Home");
		btnRefresh = new JButton("Refresh");
		addressBar = new JTextField(getHomeURL(), 30);
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

		btnGo.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						html.loadURL(addressBar.getText());
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
						html.loadURL(addressBar.getText());
					}
				}	
				);     
		
		btnHome.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						//HistoryWindow h = new HistoryWindow(history);
						//writeHistory();
						loadHomePage();
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
		
		html = new Browser(this);

		MenuBar menuBar = new MenuBar(this, html); 
		this.setJMenuBar(menuBar);

		JScrollPane sp = new JScrollPane(html);
		//    frame.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(panel, BorderLayout.NORTH);
		this.getContentPane().add(sp, BorderLayout.CENTER);

		this.setMinimumSize(MINIMUM_SIZE);
		this.setResizable(true);
		this.setVisible(true);

	}
		
	public void backPage() {
		//loadURL(history.get(history.size()-2));
	}
	
	public void forwardPage() {
		if (!btnForward.isEnabled()) {
			btnForward.setEnabled(true);
		}
	}
	
	//TODO: validate url then call loadpage method on browser class
	
	
	//TODO implement linked list
	/*public void addToHistory(String url) {
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

	} */
	
	public static void addToFavourites() {
		System.out.println("add"); //TODO
	}
	
	public static void clearFavourites() {
		System.out.println("clear"); //TODO
	}
	
	public void writeHistory(String url) {
		try {
			Date d = new Date();
			String data = DATE_FORMAT.format(d) + "\t" + url; //TODO: edit history window to show this from file

			if (!historyFile.exists()) {
				historyFile.createNewFile();
			}

			bw = new BufferedWriter(new FileWriter(historyFile.getAbsoluteFile(), true));

			bw.write(data);
			bw.newLine();
			
			bw.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clearHistory() {
		historyFile.delete();
		try {
			historyFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	  public void writeHistory() {
		//PrintWriter f = null;
		try {
			PrintWriter f = new PrintWriter("src/history.txt");
			f.println(history.get(0));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		for(int i = 0; i < history.size(); i++) {
			f.println(history.get(i));
		} 
		
		
	} */
	
	public void loadHomePage() {
		html.loadURL(getHomeURL());
	}
	
	
	//TODO TODO ask demonstrator about this
	//public void refreshPage() { //This is so the method can be called from MenuBar class without Browser object
	//	html.refreshPage();
	//}

	public String getHomeURL() {
		if (homepageFile.exists()) {
			try {
				br = new BufferedReader(new FileReader(homepageFile.getAbsoluteFile()));
				homeURL = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				homeURL = "http://java.com";
			}
		} else {
			homeURL = "http://java.com";
		}

		return homeURL;
	}

	public void setHomeURL(String homeURL) {
		this.homeURL = homeURL;
		try {
			if (!homepageFile.exists()) {
				homepageFile.createNewFile();
			}

			bw = new BufferedWriter(new FileWriter(homepageFile.getAbsoluteFile(), false));

			bw.write(homeURL);
		
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<String> getFavourites() {
		favourites = new ArrayList<String>();

		if (favouritesFile.exists()) {
			try {
				br = new BufferedReader(new FileReader(homepageFile.getAbsoluteFile()));
				String temp;
				while ((temp = br.readLine()) != null) {
					favourites.add(temp);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//FIXME
		
		
		
		return favourites;
	}
	
	public JTextField getAddressBar() {
		return addressBar;
	}
	
	//TODO:???IO class which return reader/writer for correct file
}
