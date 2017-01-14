package net.milestone3db.gui;

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

import net.milestone3db.jdbc.CustomTableModel;
import net.milestone3db.jdbc.JDBCConnector;

@SuppressWarnings("serial")
public class TableContentFromDatabase extends JPanel
{
    public TableContentFromDatabase(String tablename)
    {
    	setLayout(new BorderLayout());
    	setPreferredSize(new Dimension(1200, 700));
    	setBackground(Color.red);
    		
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to Database, run query, get result
        String sql = "SELECT * FROM "+tablename;

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try 
        {
        	connection = JDBCConnector.getInstance();
        	stmt = connection.createStatement();
        	rs = stmt.executeQuery( sql );
        	
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
        finally{
            //try{rs.close();}catch(Exception e){}
            //try{stmt.close();}catch(Exception e){}
            //try{connection.close();}catch(Exception e){}
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
        
        JTable table = new JTable(new CustomTableModel(dataVector, columnNamesVector)
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
        });  
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);

        add( new JScrollPane( table ), BorderLayout.CENTER );
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                  JTable target = (JTable) e.getSource();
                  int row = target.getSelectedRow();
                  StringBuilder header = new StringBuilder();
                  StringBuilder data = new StringBuilder();
                  String lineSeparator = System.getProperty("line.separator");
                  header.append(columnNamesVector.toString() + lineSeparator);
                  data.append(dataVector.elementAt(row).toString() + lineSeparator);
                  TextFrame textFrame = new TextFrame(header.toString(), data.toString(), target.getColumnCount());
                  textFrame.setVisible(true);
               }
            }
         }); 
    }
    class TextFrame extends JFrame
    {
       public TextFrame(String header, String data, int length) {
          super("EditFrame");
          
          JPanel mainPanel = new JPanel();
          mainPanel.setLayout(new GridLayout(length, 2));
          
          header = header.replaceAll("\\[", "").replaceAll("\\]","").replaceAll(" ","");
          String headerString[];
          headerString = header.split(",");
          
          data = data.replaceAll("\\[", "").replaceAll("\\]","");
          String dataString[];
          dataString = data.split(",");
          
          JLabel[] labels=new JLabel[length];
          JTextField[] fields=new JTextField[length];
	      for (int i=0;i<length;i++){
	          labels[i]=new JLabel(headerString[i]);
	          fields[i]=new JTextField(dataString[i]);
	      }
	      
	      for (int i=0;i<length;i++){
	          mainPanel.add(labels[i]);
	          mainPanel.add(fields[i]);
	      }
          
          getContentPane().add(mainPanel);
          addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent we) {
                dispose();
             }
          });
          setLocation(50, 50);
          setSize(500, 70*length);
       }
    }
}
