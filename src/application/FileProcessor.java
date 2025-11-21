/**************************************************
 * 
 * 
 * This class provides file reading and writing functionality
 * 
 * Author: Mary Byrne
 * 
 * 2025
 * 
 * 
 ************************************************************************************/

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileProcessor 
{
	

	private String fileName; 
	private File fileExample;  
	private Scanner myScanner;
	private PrintWriter pwInput;
	
	// Constructor
	FileProcessor (String fileName)
	{
		
		this.fileName = fileName;
		
	}
	
	// get a connection to the file
	void connectToFile()
	{
		fileExample = new File(fileName);
	}


	// method to read and print out all the lines of the file 
	void readFileContents()
    {
       try
       {
    	   	myScanner = new Scanner(fileExample); 
       
	       	while (myScanner.hasNextLine()) {
	       	  System.out.println(myScanner.nextLine());	
	       	 	
	       	}
       }    	
	  catch(FileNotFoundException e) 
       {
		  System.out.println("run time error " + e.getMessage());	
	   
       }       
    }
    	
	@SuppressWarnings("finally")
	
	// Read the file, returning a string array of lines
    String[] readFile()
    {
    
    		String[] values = new String[20];
    	
	    try
		{
		    	int i = 0;
		    	myScanner = new Scanner(fileExample); 
				 while (myScanner.hasNextLine())
				    {
				      
					 values[i] = myScanner.nextLine();
				      i++;
				    }
				 myScanner.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("run time error " + e.getMessage());
		}
	    finally
	    {
	        return values;
	    }
    }
    
    
	// get hold of a Print writer object
    void getFileWriter()
    {
    	try
    	{
    		pwInput = new PrintWriter(fileExample);
    	}
  		catch (FileNotFoundException e)
  		{
  			System.out.println("run time error " + e.getMessage());
  		}
    	
    }	

	// write a string to the file
    void writeLineToFile(String line)
    {
       System.out.println(line);
  		pwInput.println(line);    	
    }	

    // close the scanner. Good to have this as a separate method as "closing" is different to readin
    void closeReadFile()
    {
		 myScanner.close();
    }

    // close the PrintWriter. Good to have this as a separate method as "closing" is different to writing. 
    void closeWriteFile()
    {
		 pwInput.close();
    }

}
