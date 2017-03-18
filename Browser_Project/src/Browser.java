import java.io.IOException;

import javax.swing.JEditorPane;

public class Browser extends JEditorPane {
	
	public Browser() {
		
	}

	public Browser(String homeURL) {
		loadURL(homeURL);
	}
	
	
	public void loadURL(String url) {
		try {
			this.setPage(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void refreshPage() {
		loadURL(this.getPage().toString());
	}
	
}
