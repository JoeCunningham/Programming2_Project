import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	
	private Window w;
	private Browser b;
	
	public MenuBar(Window x, Browser z) {
		
		this.w = x;
		this.b = z;

		JMenu file = new JMenu("File");
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
		
		
		JMenu history = new JMenu("History");
		JMenuItem clearHistory = new JMenuItem("Clear History");
		history.add(clearHistory);
		
		clearHistory.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						w.clearHistory();
					}
				}
				);

		
		
		JMenu navigation = new JMenu("Navigation");
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
		

		JMenu favourites = new JMenu("Favourites");
		for(int i = 0; i < 11; i++) { //TODO: limit to 10 favorites
			JMenuItem temp = new JMenuItem(Integer.toString(i));
			favourites.add(temp); //TODO: read all favorites in and add to menubar
		}
		favourites.addSeparator();
		JMenuItem addToFavourites = new JMenuItem("Add current site to favourites");
		JMenuItem clearFavourites = new JMenuItem("Clear favourites");
		favourites.add(addToFavourites);
		favourites.add(clearFavourites);//TODO:??? favourites identified by name? requires popup window
		
		addToFavourites.addActionListener( //TODO: refresh favorites list when added
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						Window.addToFavourites();
					}
				}
				);
		
		clearFavourites.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						Window.clearFavourites();
					}
				}
				);

		this.add(file);
		this.add(navigation);
		this.add(history);
		this.add(favourites);
		
	}
}

