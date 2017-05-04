import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	
	private Window w;
	private Browser b;
	private MenuBar mb;
	private JMenu history, navigation, favourites;
	private JMenuItem addToFavourites, showFavourites, clearFavourites;
	
	public MenuBar(Window window, Browser browser) {
		
		this.w = window;
		this.b = browser;
		mb = this;

	/*	JMenu file = new JMenu("File");
		JMenuItem newWindow = new JMenuItem("New Window");
		JMenuItem print = new JMenuItem("Print");
		JMenuItem save = new JMenuItem("Save");

		file.add(newWindow);
		file.add(save);
		file.add(print);
		
		newWindow.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						//NEW WINDOW
						//TODO
					}
				}
				);
		
		print.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						//PRINT PAGE
						//TODO
					}
				}
				);
		
		save.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						//SAVE PAGE
						//TODO
					}
				}
				);
		*/
		
		history = new JMenu("History");
		JMenuItem showHistory = new JMenuItem("Show history window");
		JMenuItem clearHistory = new JMenuItem("Clear history");
		history.add(showHistory);
		history.add(clearHistory);
		
		showHistory.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						HistoryWindow hw = new HistoryWindow(b, w);
					}
				}
				);

		
		clearHistory.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						w.clearHistory();
					}
				}
				);

		
		navigation = new JMenu("Navigation");
		JMenuItem back = new JMenuItem("Back");
		JMenuItem forward = new JMenuItem("Forward");
		JMenuItem home = new JMenuItem("Home");
		JMenuItem refresh = new JMenuItem("Refresh");
		JMenuItem setHome = new JMenuItem("Set current site to home");
		navigation.add(back);
		navigation.add(forward);
		navigation.add(home);
		navigation.add(refresh);
		navigation.addSeparator();
		navigation.add(setHome);
		
		back.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						w.backPage();
					}
				}
				);
		
		forward.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						w.forwardPage();
					}
				}
				);
		
		home.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						w.loadHomePage();
					}
				}
				);
		
		refresh.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						b.refreshPage();
					}
				}
				);
		
		setHome.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						w.setHomeURL(w.getAddressBar().getText());
					}
				}
				);
		

		favourites = new JMenu("Favourites");
		populateFavouritesMenu();

//		this.add(file);
		this.add(navigation);
		this.add(history);
		this.add(favourites);
		
	}
	
	public void populateFavouritesMenu() {
		favourites.removeAll();
		for(int i = 0; i < w.getFavourites().size() ; i++) {
			int index = i;
			JMenuItem temp = new JMenuItem(w.getFavourites().get(i));
			
			temp.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent click) {
							b.loadURL(w.getFavourites().get(index));
						}
					}
					);			
			favourites.add(temp);
		}
		favourites.addSeparator();
		addToFavourites = new JMenuItem("Add current site to favourites");
		showFavourites = new JMenuItem("Show favourites window");
		clearFavourites = new JMenuItem("Clear favourites");
		favourites.add(addToFavourites);
		favourites.add(showFavourites);
		favourites.add(clearFavourites);

		addToFavourites.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						w.addFavourite(b.getPage().toString());
						populateFavouritesMenu();
					}
				}
				);
		
		showFavourites.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						FavouritesWindow fw = new FavouritesWindow(w, b, mb);
					}
				}
				);

		
		clearFavourites.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						w.clearFavourites();
						populateFavouritesMenu();
					}
				}
				);

	}
}

