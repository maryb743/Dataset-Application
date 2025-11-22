/**************************************************
 * 
 * 
 * This class is to create and display a GUI containing a dataset and functional buttons
 * 
 * Author: Mary Byrne
 * 
 * 2025
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
			JButton addButton,deleteButton;
			JTextField stationID, date, temp, humidity, windKPH, precipMM, Condition;
			
			//column array
			String[] columns= {
					"Station_ID","Date","Temp_C",
					"Humidity_%","Wind_kph","Precip_mm","Condition"
					};
			
			//loading data from dataset into 2d array
			String[][] tableData = FileProcessor.loadDataset("dataset.txt");
			
	        public DatasetExplorerGUI() {

	        	//setting 
	        	tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
	        	inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

	            frame.add(tablePanel);

	            //create the default table model
	            DefaultTableModel model = new DefaultTableModel(tableData, columns);
	            JTable datasetTable = new JTable(model);
//	            datasetTable.setBackground(Color.darkGray);
//	            datasetTable.setForeground(Color.pink);

	            //add, update and delete row buttons
	            addButton = new JButton("Add");
	            deleteButton = new JButton("Delete");

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

	                //read values from text fields
	                String[] newRow = {
	                    stationID.getText().trim(),
	                    date.getText().trim(),
	                    temp.getText().trim(),
	                    humidity.getText().trim(),
	                    windKPH.getText().trim(),
	                    precipMM.getText().trim(),
	                    Condition.getText().trim()
	                };

	                
	                boolean inputEmpty = true;
	               //if no text is entered set the row as empty
	                for (String s : newRow) {
	                	
	                    if (!s.isEmpty()) {
	                    	
	                    	inputEmpty = false;
	                        break;
	                        
	                    }
	                    
	                }

	               
	                if (inputEmpty) {
	                	//if empty add blank row
	                    model.addRow(new Object[columns.length]);  
	                    
	                } else {
	                	//else add data to table
	                    model.addRow(newRow);                       
	                }

	                //reset textboxes to blank after data is added
	                stationID.setText("");
	                date.setText("");
	                temp.setText("");
	                humidity.setText("");
	                windKPH.setText("");
	                precipMM.setText("");
	                Condition.setText("");

	            });

	            //adding labels and text boxes to panel
	            inputPanel.add(createInputRow("Station ID", stationID));
	            inputPanel.add(createInputRow("Date", date));
	            inputPanel.add(createInputRow("Temp (Celsius)", temp));
	            inputPanel.add(createInputRow("Humidity %", humidity));
	            inputPanel.add(createInputRow("Wind (KPH)", windKPH));
	            inputPanel.add(createInputRow("Precipitation (MM)", precipMM));
	            inputPanel.add(createInputRow("Condition", Condition));
	            
	            //button panel
	            buttonPanel.add(addButton);
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
