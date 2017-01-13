import java.sql.SQLException;

import net.milestone3db.gui.DefaultPanel;
import net.milestone3db.jdbc.Utility;

public class Milestone3Main {

	public static void main(String[] args) {

		try {
			Utility.search("select * from game", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new DefaultPanel();
		
	}
}
