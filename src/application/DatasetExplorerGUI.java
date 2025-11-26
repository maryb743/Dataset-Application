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

import java.awt.BorderLayout;
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
			JPanel textPanel, headerPanel = new JPanel();
			JLabel label1, label2, label3,label4,label5,label6,label7,displaySun, titleLabel, statsLabel;
			JButton addButton,deleteButton, showStatsButton;
			ImageIcon sunImage;
			JTextField stationID, date, temp, humidity, windKPH, precipMM, Condition;
			JDialog statsDialog = new JDialog();
			Color headerBlue = new Color (173, 216, 230);
			Color bodyBlue = new Color (70, 130, 180);
			
			//array to store column titles
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
	        	
                //create panel to hold statistics
                textPanel = new JPanel(new BorderLayout());
                textPanel.setBackground(bodyBlue);

                //panel for statistics header and icon 
                headerPanel = new JPanel(new BorderLayout());
                headerPanel.setBackground(headerBlue);
                
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

	                //set input as currently empty
	                boolean inputEmpty = true;
	               
	                for (String s : newRow) {
	                	
	                	//if no text is entered set the row as containing data
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
	           
	            
	            //actionlistener for showstats button
	            showStatsButton.addActionListener(f -> {

	            	//display user feedback to console
	                System.out.println("Now displaying Weather Forecast Statistics");

	                //get stats from stats methods in DataStatistics
	                double averageTemp = DataStatistics.calcAverageTemp(tableData);
	                double averageHumid = DataStatistics.calcAverageHumid(tableData);
	                int totalRows = DataStatistics.calcTotalRows(tableData);
	                String totalConditions = DataStatistics.calcTotalConditions(tableData);

	                //add title to header panel
	                titleLabel = new JLabel("<html><h3>Weather Forecast Statistics</h3></html>");
	                headerPanel.add(titleLabel, BorderLayout.WEST);

	                //add forecast icon
	                if (totalConditions.contains("Sunny")) {
	                    try {
	                    	//get og icon size
	                        ImageIcon original = new ImageIcon(getClass().getResource("forecast.png"));
	                        //create scaled version of image
	                        Image scaled = original.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	                        //replace old icon with scaled one
	                        JLabel iconLabel = new JLabel(new ImageIcon(scaled));
	                        //add forecast icon to right of header panel
	                        headerPanel.add(iconLabel, BorderLayout.EAST);
	                        
	                    } catch (Exception e) {
	                    	//if image path is incorrect print error
	                        System.out.println("Image not found");
	                        
	                    }
	                }

	                //stats to be displayed using html
	                statsLabel = new JLabel(
	                		
	                    "<html>" + 
	                        "Average Temperature: " + averageTemp + " °C<br>" +
	                        "Average Humidity: " + averageHumid + " %<br>" +
	                        totalConditions + "<br>" +
	                        "Total forecasts: " + totalRows +
	                    "</html>"
	                        
	                );
	                //add stats to 
	                textPanel.add(statsLabel, BorderLayout.WEST);
	                
	                //add header panel to top of main panel
	                textPanel.add(headerPanel, BorderLayout.NORTH);

	                //show window popup
	                statsDialog = new JDialog(frame, "Weather Forecast Statistics", true);
	                statsDialog.add(textPanel);
	                statsDialog.setSize(400, 200);
	                statsDialog.setLocationRelativeTo(frame);
	                statsDialog.setVisible(true);
	                
	            });

	            //add dataset table to main frame
	            frame.add(tablePanel);
	            
	            //adding labels and text boxes to panel using createInputRow method
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
	        
	    	//panel to display labels and text fields for the user to use to add data
	    	public JPanel createInputRow(String labelText, JTextField field) {
	    		
	    	    JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    	    JLabel label = new JLabel(labelText);
	    	    field.setColumns(10);
	    	    row.add(label);
	    	    row.add(field);
	    	    return row;
	    	    
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
