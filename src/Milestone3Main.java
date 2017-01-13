import java.sql.SQLException;
import java.util.ArrayList;



import java.util.Arrays;

import net.milestone3db.gui.InsertUpdateDialog;
import net.milestone3db.gui.MainPanel;
import net.milestone3db.jdbc.Utility;

public class Milestone3Main {

	public static void main(String[] args) {

		/*
		try {
			Utility.search("select * from game", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> test = new ArrayList<>(Arrays.asList(new String[]{"12","TestPublisher","Hoi"}));
		new InsertUpdateDialog("publisher",  test, true);
		 */
		new MainPanel();
		
	}
}
