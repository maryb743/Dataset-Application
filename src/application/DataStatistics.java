/**************************************************
* 
* 
*  This class is to calculate and display summary statistics about the data in the file, such as number
*  of rows, averages and totals as relevant to the dataset.
*  
*  //average temp and humidity
*  //total rainy,windy etc days
*  
*  Author: Mary Byrne
*  
*  2025
*  
*  
************************************************************************************/

package application;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DataStatistics {
	
	//method to calc average temperatures 
	public static double calcAverageTemp(String[][] data) {
		
	    double sum = 0.0;
	    int count = 0;
	    
	    //temperature column index
	    int tempCol = 2;

	    //loop through rows 
	    for (String[] row : data) {

	    	//check column (containing temps) exists and isn't empty
	        if (row.length > tempCol && row[tempCol] != null && !row[tempCol].isEmpty()) {
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

	    //error checking if count = 0 don't complete equation
	    if (count == 0) return 0;

	    //calc average
	    double avg = sum / count;

	    //round to 1 decimal point
	    BigDecimal rounded = new BigDecimal(avg);
	    rounded = rounded.setScale(1, RoundingMode.HALF_UP);

	    //return final value (rounded to 1 decimal point)
	    return rounded.doubleValue();
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
	        if (row.length > humidityCol && row[humidityCol] != null && !row[humidityCol].isEmpty()) {

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

	    //error checking if count = 0 don't complete equation
	    if (count == 0) return 0;

	    //calc average humidity %
	    double avg = sum / count;

	    // Round to 1 decimal place
	    BigDecimal result = new BigDecimal(avg);
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
	    	
	    	//check the Conditions column exists
	        if (row.length > conditionCol && row[conditionCol] != null) {

	        	//remove any leading/trailing spaces
	            String cond = row[conditionCol].trim();

	            //switch statement to increment counts
	            switch (cond) {
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
	           "Rain days: " + rainCount + "\n" +
	           "Snow days: " + snowCount;
	}


}