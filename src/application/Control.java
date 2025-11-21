/**************************************************
* 
* 
*  Control class for Dataset Explorer App
*  
*  Author: Mary Byrne
*  
*  2025
*  
*  
************************************************************************************/
package application;

public class Control 
{
	  public static void main(String[] args)  //throws FileNotFoundException
	  {
		   
		  FileProcessor fp = new FileProcessor("dataset.txt");
		  fp.connectToFile();
		  fp.readFileContents();
		  
	 	 
	  }
	  
}
