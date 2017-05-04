import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class FavouritesWindow extends JFrame {
	
	private JList<String> favouritesList;
	private DefaultListModel<String> model;
	private Browser b;
	private Window w;
	private MenuBar mb;
	private FavouritesWindow fw;
	
	public FavouritesWindow(Window window, Browser browser, MenuBar menu) {
		this.w = window;
		this.b = browser;
		this.mb = menu;
		fw = this;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		model = new DefaultListModel<String>();

		for (int i = 0; i < w.getFavourites().size(); i++) {
			model.addElement(w.getFavourites().get(i));
		}
		
		favouritesList = new JList<String>(model);
		favouritesList.setLayoutOrientation(JList.VERTICAL);
		favouritesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroller = new JScrollPane(favouritesList);
		this.getContentPane().add(scroller);
		
		favouritesList.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent evt) {
		        if(SwingUtilities.isRightMouseButton(evt)) {
		            JList tempList = (JList)evt.getSource();
		            int index = tempList.locationToIndex(evt.getPoint());
		        	RightClickMenu rcm = new RightClickMenu(fw, w, b, mb, index);
		        	rcm.show(evt.getComponent(), evt.getX(), evt.getY());
		        }
		    }
		}); 

		this.setTitle("Favourites");
		this.setSize(250, 300);
		this.setResizable(false);
		this.setVisible(true);

	}
	
	public void refresh() {
		DefaultListModel<String> newModel = new DefaultListModel<String>();
		
		for (int i = 0; i < w.getFavourites().size(); i++) {
			newModel.addElement(w.getFavourites().get(i));
		}
		favouritesList.setModel(newModel);
	}

	
	
}
