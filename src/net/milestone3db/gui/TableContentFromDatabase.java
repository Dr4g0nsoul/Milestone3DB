package net.milestone3db.gui;

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class TableContentFromDatabase extends JPanel
{
    public TableContentFromDatabase(String tablename)
    {
    	setLayout(new BorderLayout());
    	setPreferredSize(new Dimension(1200, 500));
    	setBackground(Color.red);
    	
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to Database, run query, get result set
        String url = "jdbc:postgresql://localhost:5432/steam";
        String userid = "postgres";
        String password = "masterkey";
        String sql = "SELECT * FROM "+tablename;

        try (Connection connection = DriverManager.getConnection( url, userid, password );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            //  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }
            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);
                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                }
                data.add( row );
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();
        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));

        //  Create table with database data    
        JTable table = new JTable(dataVector, columnNamesVector)
        {
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }
                return Object.class;
            }
        };    
        add( new JScrollPane( table ), BorderLayout.CENTER );
    }
}
