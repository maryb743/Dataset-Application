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
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DatasetExplorerGUI extends JFrame implements ActionListener{
		
			JFrame frame = new JFrame("Dataset table");
			JPanel panel1 = new JPanel();
			JButton button1, button2, button3;
			JTextField t1;
			
			//column array
			String[] columns= {
					"Station_ID","Date","Temp_C",
					"Humidity_%","Wind_kph","Precip_mm","Condition"
					};
			
			//loading data from dataset into 2d array
			String[][] tableData = FileProcessor.loadDataset("dataset.txt");
			
			//create table using columns and data
	        JTable datasetTable = new JTable(tableData, columns);
			
	        public DatasetExplorerGUI() {

	            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
	            frame.add(panel1);

	            //create the default table model
	            DefaultTableModel model = new DefaultTableModel(tableData, columns);
	            JTable datasetTable = new JTable(model);

	            //add row button
	            button1 = new JButton("Add empty row");

	            //action listener for add row button
	            button1.addActionListener(e -> {
	            	
	                System.out.println("'Row added");
	                model.addRow(new String[columns.length]);
	                
	            });

	            panel1.add(button1);
	            panel1.add(new JScrollPane(datasetTable));
	            
	            //displaying the weather forecast stats
	            System.out.println("Weather Forecast Statistics:");
	            //displaying the average temp 
	            double averageTemp = DataStatistics.calcAverageTemp(tableData);
	            System.out.println("The average temperature is " + averageTemp);
	            //displaying average humidity percentage
	            double averageHumid = DataStatistics.calcAverageHumid(tableData);
	            System.out.println("The average humidity percentage is " + averageHumid);
	            //displaying the total conditions
	            String totalConditions = DataStatistics.calcTotalConditions(tableData);
	            System.out.println(totalConditions);

	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.pack();
	            frame.setVisible(true);
	        }

	
	//main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DatasetExplorerGUI());
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    
}
