import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class HistoryWindow extends JFrame {
	
	private JList<String> historyList;
	private DefaultListModel<String> model;
	//private String[] historyArray;
	private Browser b;
	private Window w;
	//Dimension minimumSize;
	
	//TODO add labels for date, time, and url
	public HistoryWindow(Browser browser, Window window) {
		this.w = window;
		this.b = browser;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		model = new DefaultListModel<String>();
		
		//historyArray = new String[w.getHistory().size()];
				
		for (int i = 0; i < w.getHistory().size(); i++) {
			model.addElement(w.getHistory().get(i));
		}
		
		
		
		historyList = new JList<String>(model);
		historyList.setLayoutOrientation(JList.VERTICAL);
		historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroller = new JScrollPane(historyList);
		this.getContentPane().add(scroller);
		
		
		historyList.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		           String selectedValue = historyList.getSelectedValue();
		           String[] parts = selectedValue.split("\t");
		           b.loadURL(parts[1]);
		           refresh();
		        }
		    }
		}); 
		
		this.setTitle("History");
		this.setSize(250, 300);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	public void refresh() {
		DefaultListModel<String> newModel = new DefaultListModel<String>();
		
		for (int i = 0; i < w.getHistory().size(); i++) {
			newModel.addElement(w.getHistory().get(i));
		}
		historyList.setModel(newModel);
	}
}
