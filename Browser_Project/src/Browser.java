import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
		
		
		//cited from http://stackoverflow.com/questions/4252204/how-can-i-tell-if-a-jeditorpane-textpane-document-or-page-has-finished-loading
		this.addPropertyChangeListener("page", //For the cursor
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						setCursor(Cursor.getDefaultCursor());
						//w.getAddressBar().setText(getPage().toString());
					}
				}
				);
		
		this.addHyperlinkListener(
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
		if(validateURL(url)) {
			w.getAddressBar().setText(url);
			w.writeHistory(url);
		} else {
			JOptionPane.showMessageDialog(w, "Invalid URL! \n Must start with \"http://\" or \"https://\"", "Error!", JOptionPane.ERROR_MESSAGE);

		}
		
	}
	
	public void refreshPage() {
		this.getDocument().putProperty(Document.StreamDescriptionProperty, null);
		loadURL(w.getAddressBar().getText());
	}
	
	public boolean validateURL(String url) {
		if(url.startsWith("http://") || url.startsWith("https://")) {
			try {
				this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				setPage(url);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
				
	}
	
}
