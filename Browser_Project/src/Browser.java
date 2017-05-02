import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

public class Browser extends JEditorPane {
	
	//public Browser() {
	//	
	//}
	
	private Window w;

	public Browser(Window x) {
		this.w = x;
		
		loadURL(w.getHomeURL());
		setEditable(false);
		
		addHyperlinkListener(
				new HyperlinkListener() {
					public void hyperlinkUpdate(HyperlinkEvent click) {
						if (click.getEventType()==HyperlinkEvent.EventType.ACTIVATED) {
							loadURL(click.getURL().toString());
						}
					}
				}
		);
		
	}
	
	
	public void loadURL(String url) {
		try {
			this.setPage(url);

		} catch (IOException e) {
			e.printStackTrace();
		}
		w.addressBar.setText(url);
		w.printHistory(url);
		
	}
	
	public void refreshPage() {
		loadURL(w.addressBar.getText());
	}
	
}
