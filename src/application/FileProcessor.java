/**************************************************
 * 
 * 
 * This class provides file reading and writing functionality, 
 * Reads the dataset text file and returns its contents as a 2D String array.
 * 
 * Author: Mary Byrne
 * 
 * 3-12-2025
 * 
 * 
 ************************************************************************************/

package application;

import java.io.*;
import java.util.ArrayList;

public class FileProcessor {

	public static String[][] loadDataset(String filename) {

		//temp array list to hold row data
	    ArrayList<String[]> rows = new ArrayList<>();
	    
	    try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
	        String line;
	        boolean isFirstLine = true;

	        //read in file line by line
	        while ((line = fileReader.readLine()) != null) {

	        	//remove whitespace
	            line = line.trim();
	            
	            //skip blank lines
	            if (line.isEmpty()) {
	                continue;
	            }

	            //skip column headers (first line of dataset)
	            if (isFirstLine) {
	                isFirstLine = false; 
	                continue;
	            }

	            //split line into columns
	            String[] columns = line.split("\\s+");

	            //make sure only rows that have 7 columns are added
	            if (columns.length == 7) {
	                rows.add(columns);
	                
	            } else {
	            	
	            	//error checking
	            	//ignore rows with more or less than 7 columns
	                
	            }
	        }

	    } catch (Exception e) {
	    	//print any errors 
	        e.printStackTrace();
	        
	    }
	    
	    //convert temp ArrayList to 2D array for JTable
	    return rows.toArray(new String[0][]);
	}

}
