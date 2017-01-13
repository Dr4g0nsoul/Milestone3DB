package net.milestone3db.jdbc;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * A custom TableModel. It prevents that fields are editable.
 * @author Philipp Rassele
 */
public class CustomTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	public CustomTableModel() {
	}

	public CustomTableModel(Vector<Vector<?>> rowdata, Vector<String> colNames) {
		super(rowdata, colNames);
	}

	@Override
	public Class<?> getColumnClass(int c) {
		Class<?> ret = Object.class;
		if (getRowCount() > 0)
			ret = getValueAt(0, c).getClass();
		return ret;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}


