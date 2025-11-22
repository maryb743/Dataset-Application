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

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DatasetExplorerGUI extends JFrame implements ActionListener{
		
			JFrame frame = new JFrame("Weather Forecast Dataset");
			JPanel tablePanel = new JPanel();
			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JPanel inputPanel = new JPanel();
			JLabel label1, label2, label3,label4,label5,label6,label7;
			JButton addButton, updateButton, deleteButton;
			JTextField stationID, date, temp, humidity, windKPH, precipMM, Condition;
			
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

	        	//setting 
	        	tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
	        	inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

	            frame.add(tablePanel);

	            //create the default table model
	            DefaultTableModel model = new DefaultTableModel(tableData, columns);
	            JTable datasetTable = new JTable(model);

	            //add, update and delete row buttons
	            addButton = new JButton("Add");
	            updateButton = new JButton("Update");
	            deleteButton = new JButton("Delete");
	            
	            //labels
	            label1 = new JLabel("Station ID");
	            label2 = new JLabel("Date");
	            label3 = new JLabel("Temp (Celcius)");
	            label4 = new JLabel("Humidity %");
	            label5 = new JLabel("Wind (KPH)");
	            label6 = new JLabel("Precipitation (MM)");
	            label7 = new JLabel("Condition");
	            
	            //text fields for entering data
	            stationID = new JTextField();
	            date = new JTextField();
	            temp = new JTextField();
	            humidity = new JTextField();
	            windKPH = new JTextField();
	            precipMM = new JTextField();
	            Condition = new JTextField();
	            
	            //action listener for add row button
	            addButton.addActionListener(e -> {
	            	
	                System.out.println("Row added");
	                model.addRow(new String[columns.length]);
	                
	            });

	            inputPanel.add(createInputRow("Station ID", stationID));
	            inputPanel.add(createInputRow("Date", date));
	            inputPanel.add(createInputRow("Temp (Celsius)", temp));
	            inputPanel.add(createInputRow("Humidity %", humidity));
	            inputPanel.add(createInputRow("Wind (KPH)", windKPH));
	            inputPanel.add(createInputRow("Precipitation (MM)", precipMM));
	            inputPanel.add(createInputRow("Condition", Condition));
	            
	            //button panel
	            buttonPanel.add(addButton);
	            buttonPanel.add(updateButton);
	            buttonPanel.add(deleteButton);
	           
	            
	            //add scroll bar
	            tablePanel.add(new JScrollPane(datasetTable));
	            
	            tablePanel.add(inputPanel);
	            tablePanel.add(buttonPanel);
	            
	            
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
	
	public JPanel createInputRow(String labelText, JTextField field) {
		
	    JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JLabel label = new JLabel(labelText);
	    field.setColumns(10);
	    row.add(label);
	    row.add(field);
	    return row;
	}

    
}
