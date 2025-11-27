/**************************************************
 * 
 * 
 * This class is to create and display a GUI containing a dataset and functional buttons.
 * 
 * Author: Mary Byrne
 * 
 * 3-12-2025
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
public class DatasetExplorerGUI extends JFrame implements ActionListener {


    private JPanel tablePanel = new JPanel();
    private JTable datasetTable;
    private DefaultTableModel model;
    private JTable searchResult = new JTable();
    private JPanel searchPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel inputPanel = new JPanel();
    private JPanel statsPanel = new JPanel();
    private JPanel statsTextPanel = new JPanel();
    private JPanel statsHeaderPanel = new JPanel();
    private JLabel titleLabel, statsLabel;
    private JButton addButton, deleteButton, showStatsButton;
    private JButton searchButton;
    private JTextField stationID, date, temp, humidity, windKPH, precipMM, Condition;
    private JTextField searchBar = new JTextField(20);
    private JScrollPane searchScroll = new JScrollPane();
    private JDialog statsDialog = new JDialog();
    private JDialog searchDialog = new JDialog();
    private Color headerBlue = new Color(173, 216, 230);
    private Color bodyBlue = new Color(70, 130, 180);

    //array to store column titles
    private String[] columns = {
        "Station_ID", "Date", "Temp_C",
        "Humidity_%", "Wind_kph", "Precip_mm", "Condition"
    };

    //loading data from dataset in FileProcessor into 2d array
    private String[][] tableData = FileProcessor.loadDataset("dataset.txt");

    //GUI constructor
    public DatasetExplorerGUI() {

    	//layout setup for panels
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        statsTextPanel.setLayout(new BorderLayout());
        statsHeaderPanel.setLayout(new BorderLayout());
        
        //setting background colours
        statsTextPanel.setBackground(bodyBlue);
        statsHeaderPanel.setBackground(headerBlue);

        //creating main dataset table and table model
        model = new DefaultTableModel(tableData, columns);
        datasetTable = new JTable(model);

        //buttons
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        showStatsButton = new JButton("Show Forecast Statistics");
        searchButton = new JButton("Search");

        //text boxes for adding new rows
        stationID = new JTextField();
        date = new JTextField();
        temp = new JTextField();
        humidity = new JTextField();
        windKPH = new JTextField();
        precipMM = new JTextField();
        Condition = new JTextField();

        //giving all buttons actionListeners
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        showStatsButton.addActionListener(this);
        searchButton.addActionListener(this);

        //add main table into frame
        add(tablePanel);

        //input rows for adding data
        inputPanel.add(createInputRow("Station ID", stationID));
        inputPanel.add(createInputRow("Date", date));
        inputPanel.add(createInputRow("Temp (Celsius)", temp));
        inputPanel.add(createInputRow("Humidity %", humidity));
        inputPanel.add(createInputRow("Wind (KPH)", windKPH));
        inputPanel.add(createInputRow("Precipitation (MM)", precipMM));
        inputPanel.add(createInputRow("Condition", Condition));
        
        //add buttons to button panel
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showStatsButton);

        //add search componants to search panel
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);

        //add all componants to main panel
        tablePanel.add(new JScrollPane(datasetTable));
        tablePanel.add(inputPanel);
        tablePanel.add(buttonPanel);
        tablePanel.add(searchPanel);

        //ensure window closes when x is pressed, componants fit and is visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
    }

    //method to make the labelled input fields for adding data
    public JPanel createInputRow(String labelText, JTextField field) {
    	
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        field.setColumns(10);
        row.add(label);
        row.add(field);
        return row;
        
    }

    //main method to run as java app
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DatasetExplorerGUI());
    }

    //event handling
    @Override
    public void actionPerformed(ActionEvent e) {

    	//object to identify which button gets pressed
        Object src = e.getSource();

        if (src == addButton) {
        	//if add is pressed add row to dataset
            addRow();
        
        } 
        
        else if (src == deleteButton) {
        	//if delete is pressed delete row from dataset
            deleteRow();   
        }
        
        else if (src == showStatsButton) {
        	//if show stats is pressed show forecast statistics
            showStats();
           
        } 
        
        else if (src == searchButton) {
            System.out.println("Searching for item");
          //is search button is pressed search for input then display results panel 
            search();
            
        }
        
        else if(src == searchBar) {
        	//if searchBar pressed 
        	System.out.println("Search bar in use");
        	
        }
        
        
    }

    //method to add a row to dataset
    private void addRow() {

    	//get input from text boxes
        String[] newRow = {
            stationID.getText().trim(),
            date.getText().trim(),
            temp.getText().trim(),
            humidity.getText().trim(),
            windKPH.getText().trim(),
            precipMM.getText().trim(),
            Condition.getText().trim()
        };

        //check if text boxes are empty
        boolean empty = true;
        
        for (String textBox : newRow) {
        	//if not empty set empty to false
            if (!textBox.isEmpty()) {
            	empty = false;
            	
                break;
                
            }
            
        }

        //if text boxes are empty add blank row
        if (empty) {
        	
            model.addRow(new Object[columns.length]);
            System.out.println("Blank row added to dataset");
            
        } 
        //if not empty add data to dataset
        else {
            model.addRow(newRow);
            System.out.println("New data added to dataset");
        }

        //update/clear text boxes after row is added
        stationID.setText("");
        date.setText("");
        temp.setText("");
        humidity.setText("");
        windKPH.setText("");
        precipMM.setText("");
        Condition.setText("");
        
    }

  //method to delete a row to dataset
    private void deleteRow() {
    	
    	//sets row to selected row / 1 (-1 if not selected)
        int row = datasetTable.getSelectedRow();
        
        //if row is greated than 0 (row selected) remove row
        if (row >= 0) {
        	
            model.removeRow(row);
            System.out.println("Row deleted");
            
        } 
        
        //else print user feedback
        else 
        {
            System.out.println("No row selected to delete.");
        }
        
    }

  //method to show forecast statistics panel
    private void showStats() {
    	

        System.out.println("Now displaying Weather Forecast Statistics");

        //create stats object
        DataStatistics stats = new DataStatistics();

        //calc + store stats using setters
        stats.calcStats(tableData);

        //get values from getters
        double averageTemp = stats.getAverageTemp();
        double averageHumid = stats.getAverageHumid();
        int totalRows = stats.getTotalRows();
        String totalConditions = stats.getTotalConditions();

        //use html to display title
        titleLabel = new JLabel("<html><h3>Weather Forecast Statistics</h3></html>");
        
        //add title and layout setup to header
        statsHeaderPanel.add(titleLabel, BorderLayout.WEST);

        //if condiitons not empty show forecast image
        if (totalConditions != null) {
        	
            try {
            	
            	//get og image
                ImageIcon original = new ImageIcon(getClass().getResource("forecast.png"));
                //scale down to 50 x 50 pixels
                Image scaled = original.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                //get resized image
                JLabel iconLabel = new JLabel(new ImageIcon(scaled));
                //add tto header panel
                statsHeaderPanel.add(iconLabel, BorderLayout.EAST);
                
             //error checking
            } catch (Exception ex) {
            	
            	//print user feeback
                System.out.println("Image not found");
                
            }
            
        }

        //using html to format statistics
        statsLabel = new JLabel(
            "<html>" +
        
            "Average Temperature: " + averageTemp + " °C<br>" +
            
            "Average Humidity: " + averageHumid + " %<br>" +
            
            totalConditions + "<br>" +
            
            "Total forecasts: " + totalRows +
            "</html>"
        );

        //add text to text panel 
        statsTextPanel.add(statsLabel, BorderLayout.WEST);
        //add the header above text
        statsTextPanel.add(statsHeaderPanel, BorderLayout.NORTH);

        //add text panel to pop up window
        statsDialog.add(statsTextPanel);
        //set layout of popup window
        statsDialog.setSize(400, 200);
        statsDialog.setLocationRelativeTo(tablePanel);
        //set as visable
        statsDialog.setVisible(true);
        
        
        
        
    }

  //method to searcg dataset for user input entered
    private void search() {

    	//get user input from search box
        String userInput = searchBar.getText();
        
        //new model to hold results
        DefaultTableModel newModel = new DefaultTableModel(columns, 0);

        //loop through dataset
        for (String[] row : tableData) {
        	//set data match to false
            boolean inputMatches = false;

            //check each piece of data if it matches the user input
            for (String data : row) {
            	
                if (data != null && data.contains(userInput)) {
                	//input matches the data set to true
                	inputMatches = true;
                	
                    break;
                    
                }
                
            }

            //if match to input is found add row to results table
            if (inputMatches) {
            	
                newModel.addRow(row);
                
            }
        }

        //display resuts table in new model
        searchResult.setModel(newModel);
        searchDialog = new JDialog(this, "Search Results found: ");
        //add scroll wheel to results panel
        searchScroll = new JScrollPane(searchResult);
        //set results dialog layout
        searchDialog.setSize(400, 300);
        searchDialog.add(searchScroll);
        searchDialog.setLocationRelativeTo(this);
        searchDialog.setVisible(true);
        
        
    }
}

