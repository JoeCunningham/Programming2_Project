import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

import javax.swing.*;
import javax.swing.event.*;

public class Window extends JFrame {	

	private Browser html;
	private JTextField addressBar;
	private JButton btnGo, btnBack, btnForward, btnHome, btnRefresh;
	private final Dimension MINIMUM_SIZE = new Dimension(800, 600);
	private String homeURL;
	private ArrayList<String> favourites;
	private ArrayList<String> history;
	
	private Stack<String> backwardsStack;
	private Stack<String> forwardsStack;
	
	private BufferedWriter bw = null;
	private BufferedReader br = null;
	private File historyFile;
	private File homepageFile;
	private File favouritesFile;
	private File tempFile;
	private static final String HISTORY_FILENAME = "/Users/joe/Desktop/history.txt";
	private static final String HOMEPAGE_FILENAME = "/Users/joe/Desktop/homepage.txt";
	private static final String FAVOURITES_FILENAME = "/Users/joe/Desktop/favourites.txt";
	private static final String TEMP_FILENAME = "/Users/joe/Desktop/temp.txt";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy   hh:mm");
	private static final int MAX_FAVOURITES = 15;

	public Window() {
				
		historyFile = new File(HISTORY_FILENAME);
		homepageFile = new File(HOMEPAGE_FILENAME);
		favouritesFile = new File(FAVOURITES_FILENAME);

		backwardsStack = new Stack<String>();
		forwardsStack = new Stack<String>();

		this.setTitle("browser");
		
		btnBack = new JButton("<");
		btnForward = new JButton(">");
		btnHome = new JButton("Home");
		btnRefresh = new JButton("Refresh");
		addressBar = new JTextField(getHomeURL(), 30);
		btnGo = new JButton("GO");

		//TODO btnForward.setEnabled(false);

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
						html.loadURL(addressBar.getText(), false);
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
						html.loadURL(addressBar.getText(), false);
					}
				}	
				);     
		
		btnHome.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
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
		
		btnForward.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						forwardPage();
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
		forwardsStack.push(backwardsStack.pop());
		html.loadURL(backwardsStack.peek(), true);
	}
	
	public void forwardPage() {
		backwardsStack.push(forwardsStack.peek());
		html.loadURL(forwardsStack.pop(), true);
	}
	
	//TODO backwards and forwards
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
	
	public void addFavourite(String url) {
		boolean exists = false;
		for (int i = 0; i < getFavourites().size(); i++) {
			if (getFavourites().get(i).equals(url)) {
				exists = true;
			}
		}
		if (!exists) {
			try {
				if (getFavourites().size() < MAX_FAVOURITES) {
					bw = new BufferedWriter(new FileWriter(favouritesFile.getAbsoluteFile(), true));
					bw.write(url);
					bw.newLine();
					bw.close();
				} else {
					JOptionPane.showMessageDialog(this, "Too many favourites! \n Maximum: " + MAX_FAVOURITES, "Warning!", JOptionPane.WARNING_MESSAGE);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Already in your favourites!", "Warning!", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	public void clearFavourites() {
		favouritesFile.delete();
		try {
			favouritesFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getHistory() {
		history = new ArrayList<String>();
		try {
			if (historyFile.exists()) {
				br = new BufferedReader(new FileReader(historyFile.getAbsoluteFile()));
				String temp;
				while ((temp = br.readLine()) != null) {
					history.add(temp);
				}
			} else {
				historyFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return history;
	}
	
	public void writeHistory(String url) {
		try {
			Date d = new Date();
			String data = DATE_FORMAT.format(d) + "\t" + url;

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
		html.loadURL(getHomeURL(), false);
	}
	
	public String getHomeURL() {
		if (homepageFile.exists()) {
			try {
				br = new BufferedReader(new FileReader(homepageFile.getAbsoluteFile()));
				homeURL = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			setHomeURL("http://java.com");
		}

		return homeURL;
	}

	public void setHomeURL(String homeURL) {
		this.homeURL = homeURL;
		try {
			//if (!homepageFile.exists()) {
			//	homepageFile.createNewFile();
			//}

			bw = new BufferedWriter(new FileWriter(homepageFile.getAbsoluteFile(), false));

			bw.write(homeURL);
		
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
		
	public ArrayList<String> getFavourites() {
		favourites = new ArrayList<String>();
		try {
			if (favouritesFile.exists()) {
				br = new BufferedReader(new FileReader(favouritesFile.getAbsoluteFile()));
				String temp;
				while ((temp = br.readLine()) != null) {
					favourites.add(temp);
				}
			} else {
				favouritesFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return favourites;
	}
	
	public void removeFavourite(int index) {
		String urlToDelete = favourites.get(index);
		favourites.remove(index);
		try {
			tempFile = new File(TEMP_FILENAME);
			tempFile.createNewFile();
			br = new BufferedReader(new FileReader(favouritesFile.getAbsoluteFile()));
			bw = new BufferedWriter(new FileWriter(tempFile.getAbsoluteFile()));

			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				if(!currentLine.equals(urlToDelete)) {
					bw.write(currentLine);
					bw.newLine();
				}
			}
			br.close();
			bw.close();
			tempFile.renameTo(favouritesFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println("gone");
		//System.out.println(favourites.toString());

	}
	
	public JTextField getAddressBar() {
		return addressBar;
	}
	
	public Stack<String> getBackwardsStack() {
		return backwardsStack;
	}
	
	public Stack<String> getForwardsStack() {
		return forwardsStack;
	}

	
	//TODO:???IO/FileSystem/FileAccess class which return reader/writer for correct file
}
