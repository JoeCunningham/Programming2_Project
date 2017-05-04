import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class RightClickMenu extends JPopupMenu {
	
	private Window w;
	private Browser b;
	private FavouritesWindow fw;
	private MenuBar mb;

	
	public RightClickMenu(FavouritesWindow fav, Window window, Browser browser, MenuBar menu, int index) {
		
		this.w = window;
		this.b = browser;
		this.fw = fav;
		this.mb = menu;

		
		JMenuItem load = new JMenuItem("Load website");
		JMenuItem remove = new JMenuItem("Remove from favourites");
		
		this.add(load);
		this.add(remove);
		
		load.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						b.loadURL(w.getFavourites().get(index), false);
					}
				}
				);

		remove.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent click) {
						w.removeFavourite(index);
						fw.refresh();
						mb.populateFavouritesMenu();
					}
				}
				);

		
	}

}
