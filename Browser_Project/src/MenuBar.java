import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	
	public MenuBar() {

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
					}
				}
				);
		
		print.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						//PRINT PAGE
					}
				}
				);
		
		save.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						//SAVE PAGE
					}
				}
				);
		
		
		JMenu history = new JMenu("History");
		
		JMenu navigation = new JMenu("Navigation");
		JMenuItem back = new JMenuItem("Back");
		JMenuItem forward = new JMenuItem("Forward");
		JMenuItem home = new JMenuItem("Home");
		JMenuItem refresh = new JMenuItem("Refresh");
		navigation.add(back);
		navigation.add(forward);
		navigation.add(home);
		navigation.add(refresh);
		
		back.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						//GO BACK
					}
				}
				);
		
		forward.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						//GO FORWARD
					}
				}
				);
		
		home.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						//GO TO HOME
					}
				}
				);
		
		refresh.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						//REFRESH PAGE
					}
				}
				);
		

		JMenu favourites = new JMenu("Favourites");
		JMenuItem addToFavourites = new JMenuItem("Add current site to favourites");
		JMenuItem clearFavourites = new JMenuItem("Clear favourites");
		favourites.add(addToFavourites);
		favourites.add(clearFavourites);
		
		addToFavourites.addActionListener(
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

