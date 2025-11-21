/**************************************************
 * 
 * 
 * This class is to create and display a GUI containing a dataset
 * 
 * Author: Mary Byrne
 * 
 * 2025
 * 
 * 
 * 
 * 
 ************************************************************************************/

package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DatasetExplorerGUI extends JFrame implements ActionListener{
	
	public DatasetExplorerGUI(){
		
		//creating the window and panel to hold table
		JFrame frame = new JFrame("Dataset table");
		JPanel panel1 = new JPanel();
		
		//add vertical layout
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		//add name to table
		JLabel staticTableName = new JLabel("Weather Forcast Table");
		
		//adding panel with label to frame 
		frame.add(panel1);
		panel1.add(staticTableName);
		
		//column titles
		String[] columns= {
				"Station_ID","Date","Temp_C",
				"Humidity_%","Wind_kph","Precip_mm","Condition"
				};
		
		//loading data from dataset into 2d array
		String[][] tableData = FileProcessor.loadDataset("dataset.txt");

		//create table using columns and data
        JTable datasetTable = new JTable(tableData, columns);
        
        //make table scrollable
        panel1.add(new JScrollPane(datasetTable));

        //close program when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //resize window to fit componants
        frame.pack();
        
        //make window visable
        frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DatasetExplorerGUI());
    }
    
}
