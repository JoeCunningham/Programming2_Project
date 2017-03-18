import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class HistoryWindow extends JFrame {
	
	String[] historyArray;
	Dimension minimumSize;
	
	public HistoryWindow(ArrayList<String> history) {
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		historyArray = new String[history.size()];
		
		for (int i = 0; i < history.size(); i++) {
			historyArray[i] = history.get(i);
		}
		
		JList historyList = new JList(historyArray);
		historyList.setLayoutOrientation(JList.VERTICAL);
		historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroller = new JScrollPane(historyList);
		this.getContentPane().add(scroller);
		
		this.setTitle("History");
		this.setSize(250, 300);
		this.setVisible(true);
		
	}
	
}
