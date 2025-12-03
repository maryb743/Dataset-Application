/**************************************************
* 
* 
*  This class is to calculate and display summary statistics about the data in the file, such as number
*  of rows, averages and totals as relevant to the dataset.
*  
*  Author: Mary Byrne
*  
*  3-12-2025
*  
*  
************************************************************************************/

package application;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DataStatistics {
	
	//statistics variables
	private double averageTemp;
	private double averageHumid;
	private String totalConditions;
	private int totalRows;
	
	//method to calc average temperatures 
	public static double calcAverageTemp(String[][] data) {
		
	    double sum = 0.0;
	    int count = 0;
	    
	    //temperature column index
	    int tempCol = 2;

	    //loop through rows 
	    for (String[] row : data) {

	    	//check column (containing temps) exists and isn't empty
	        if (row.length > tempCol) {
	        	
	            try {
	            	//convert temps from txt file from string to double
	                double temp = Double.parseDouble(row[tempCol]);
	                //add to total
	                sum += temp;
	                //increase count
	                count++;
	                
	            } catch (NumberFormatException e) {
	                //ignore invalid values
	            }
	        }
	    }

	    //calc average
	    double avg = sum / count;

	    //round to 1 decimal point
	    BigDecimal result = new BigDecimal(avg);
	    result = result.setScale(1, RoundingMode.HALF_UP);

	    //return final value (rounded to 1 decimal point)
	    return result.doubleValue();
	}

	//method to calc average humidity percentage 
	public static double calcAverageHumid(String[][] data) {

	    double sum = 0.0;
	    int count = 0;
	    
	    //humditiy column index
	    int humidityCol = 3;

	    //loop through rows
	    for (String[] row : data) {

	    	//check column (containing humidity %) exists and isn't empty
	        if (row.length > humidityCol) {

	            try {
	            	//convert humidity % from txt file from string to double
	                double humid = Double.parseDouble(row[humidityCol]);
	                sum += humid;
	                count++;

	            } catch (NumberFormatException e) {
	                //ignore invalid values
	            }
	        }
	    }
	    
	    //calc average humidity %
	    double avg = sum / count;

	    //round to 1 decimal place
	    BigDecimal result = new BigDecimal(avg);
	    //set result as rounded number
	    result = result.setScale(1, RoundingMode.HALF_UP);

	    //return final value (rounded to 1 decimal point)
	    return result.doubleValue();
	}

	//method to calc total of each weather condition
	public static String calcTotalConditions(String[][] data) {

	    int sunnyCount = 0;
	    int cloudyCount = 0;
	    int rainCount = 0;
	    int snowCount = 0;
	    
	    //condition column index
	    int conditionCol = 6;

	    //loop through rows
	    for (String[] row : data) {
	    	
	    	//check the Conditions column exists/error checking
	        if (row.length > conditionCol) {

	        	//remove any leading/trailing spaces
	            String condition = row[conditionCol].trim();

	            //switch statement to increment counts
	            switch (condition) {
	                case "Sunny":
	                    sunnyCount++;
	                    break;
	                case "Cloudy":
	                    cloudyCount++;
	                    break;
	                case "Rain":
	                    rainCount++;
	                    break;
	                case "Snow":
	                    snowCount++;
	                    break;
	            }
	        }
	    }

	    //return amounts of each condition
	    return "Sunny days: " + sunnyCount + "\n" +
	           "Cloudy days: " + cloudyCount + "\n" +
	           "Rainy days: " + rainCount + "\n" +
	           "Snowy days: " + snowCount;
	}
	
	//method to calc total of each weather condition
	public static int calcTotalRows(String[][] data) {

	    int rowCount = -1;

	    //loop through rows
	    for (String[] row : data) {
	    	
	    	//check row isn't null
	        if (row != null) {

	        	rowCount++;
	        	
	        }
	    }

	    //return total row number
	    return rowCount;
	}
	
	//method to caluclate and store everything 
	public void calcStats(String[][] data) {

	    this.averageTemp = calcAverageTemp(data);
	    
	    this.averageHumid = calcAverageHumid(data);
	    
	    this.totalRows = calcTotalRows(data);
	    
	    this.totalConditions = calcTotalConditions(data);
	    
	}

	//getters & setters
	//averageTemp getter
	public double getAverageTemp() {
		return averageTemp;
	}

	//averageTemp setter
	public void setAverageTemp(double averageTemp) {
		this.averageTemp = averageTemp;
	}

	//averageHumid getter
	public double getAverageHumid() {
		return averageHumid;
	}

	//averageHumid setter
	public void setAverageHumid(double averageHumid) {
		this.averageHumid = averageHumid;
	}

	//totalConditions getter
	public String getTotalConditions() {
		return totalConditions;
	}
	
	//totalCondition setter
	public void setTotalConditions(String totalConditions) {
		this.totalConditions = totalConditions;
	}

	//totalRows getter
	public int getTotalRows() {
		return totalRows;
	}

	//totalRows setter
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	

}