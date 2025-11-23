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

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
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
			JPanel statsPanel = new JPanel();
			JPanel rowPanel = new JPanel();
			JLabel label1, label2, label3,label4,label5,label6,label7,displaySun, displayCloud, displayRain, displaySnow;
			JButton addButton,deleteButton, showStatsButton;
			ImageIcon sunImage, cloudImage,rainImage,snowImage;
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
	        	statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));

	            frame.add(tablePanel);

	            //create the default table model
	            DefaultTableModel model = new DefaultTableModel(tableData, columns);
	            JTable datasetTable = new JTable(model);

	            //add, update and delete row buttons
	            addButton = new JButton("Add");
	            deleteButton = new JButton("Delete");
	            showStatsButton = new JButton("Show Forecast Statistics");

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
	                    System.out.println("Blank row added to dataset");
	                    
	                } else {
	                	//else add data to table
	                    model.addRow(newRow); 
	                    System.out.println("New data added to dataset");
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
	            
	            showStatsButton.addActionListener(f -> {

	            	//display user feedback to console
	                System.out.println("Now displaying Weather Forecast Statistics");

	                //get stats from stats methods in DataStatistics
	                double averageTemp = DataStatistics.calcAverageTemp(tableData);
	                double averageHumid = DataStatistics.calcAverageHumid(tableData);
	                String totalConditions = DataStatistics.calcTotalConditions(tableData);

	                //statslabel to store html of forecast stats
	                JLabel statsLabel = new JLabel(
	                		
	                    "<html>" +
	                    "<h3>Weather Forecast Statistics</h3>" +
	                    "Average Temperature: " + averageTemp + " °C<br>" +
	                    "Average Humidity: " + averageHumid + " %<br>" +
	                    totalConditions +
	                    "</html>"
	                    
	                );

	                //create display panel
	                rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	                
	                //update background colour
	                rowPanel.setBackground(Color.WHITE);
	                
	                //add stats to panel
	                rowPanel.add(statsLabel);

	                try {
	                	//get image in og size
	                    ImageIcon original = new ImageIcon(getClass().getResource("sunny.png"));

	                    //resize image
	                    Image scaled = original.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	                    ImageIcon resizedIcon = new ImageIcon(scaled);

	                    //update size
	                    displaySun = new JLabel(resizedIcon);
	                    rowPanel.add(displaySun);

	                    //if condition is sunny display image of sun
	                    if (totalConditions.contains("Sunny")) {
	                        rowPanel.add(displaySun);
	                    }

	                    //if image path incorrect display error
	                } catch (Exception e) {
	                    System.out.println("Image not found");
	                }


	                //show window popup
	                JDialog statsDialog = new JDialog(frame, "Weather Forecast Statistics", true);
	                statsDialog.add(rowPanel);
	                statsDialog.setSize(350, 300);
	                statsDialog.setLocationRelativeTo(frame);
	                statsDialog.setVisible(true);
	                
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
	            buttonPanel.add(showStatsButton);
	           
	            
	            //add scroll bar
	            tablePanel.add(new JScrollPane(datasetTable));
	            
	            tablePanel.add(inputPanel);
	            tablePanel.add(buttonPanel);
	            
	            //old stats displaying to console
//	            //displaying the weather forecast stats
//	            System.out.println("Weather Forecast Statistics:");
//	            //displaying the average temp 
//	            double averageTemp = DataStatistics.calcAverageTemp(tableData);
//	            System.out.println("The average temperature is " + averageTemp);
//	            //displaying average humidity percentage
//	            double averageHumid = DataStatistics.calcAverageHumid(tableData);
//	            System.out.println("The average humidity percentage is " + averageHumid);
//	            //displaying the total conditions
//	            String totalConditions = DataStatistics.calcTotalConditions(tableData);
//	            System.out.println(totalConditions);

	            //ensure frame closes when x is pressed
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
