/* Programmer: Richard Kim
 * Date: February 19th, 2014
 * Lecture: 2215
 * Purpose: The purpose of this program is to read a txt file that contains a list of numbers that are
 *          separated by commas. The program will then read each number accordingly and determine which 
 *          numbers are higher/lower than others. Once the list of numbers have been sorted, it will be 
 *          output as an array in a separate output text file.
 */


import java.io.*;
import java.util.*;
import javax.swing.*;
//import java.io.PrintWriter;


public class Hash1{

public static int[] array = new int[20];
public static int i, sizeOfHeap, len = 0;

public static void readTextFile(String fileName) {
FileReader file = null;

try {
	   
	  String line = null;
	  String ar[] = new String[50];
	  file = new FileReader(fileName);
	  BufferedReader reader = new BufferedReader(file);
	 
	 while ((line = reader.readLine()) != null )
	 	{
		 	ar = line.split(",");
	 	}

     len = ar.length;

	 for(i=0;i<len;i++)
	   {
	       array[i] = Integer.parseInt(ar[i]);
	   }
    } 
	
catch (Exception exception) 
	 {
	   throw new RuntimeException(exception);
	 } 
		finally 
		{
		   if (file != null) 
		   {
		      try 
		      {
		         file.close();
		      }
		      
		     catch (IOException exception) {} 
	
		   }
	    }
}

	public static int rightSide(int p)
		{
		   //returns right index of a zero based array
		   return 2+p*2;
		}
  
	public static int leftSide(int p)
	   {
	      //returns left index of a zero index based array
	      return 1+p*2;
	   }

   public static void BUILD_MAX_HEAP(int A[])
   {
	      sizeOfHeap = len;//heap size initialised
	     
	      for(int i=len/2; i>=0;i--)//since n/2, n/2+1 ... are leaves we can start from n/2 step downwards
		   {
		      //call MAX_HEAPIFY on each root node starting from n/2
		      MAX_HEAPIFY(A, i);
		   }
   }
   
   public static void MAX_HEAPIFY(int A[],int i)
   {
      int leftIndex = leftSide(i);//the left element's index which is 2*i+1 (for zero based indexed array)
      int rightIndex = rightSide(i);//right index = 2*i+2;
      int largestElementIndex = -1;//index can't be negative so initialise largest index , it will be used later

      if(leftIndex < sizeOfHeap && A[leftIndex] > A[i])
      {
        largestElementIndex = leftIndex;
      }
         else //if max heap property is not violated copy the root's index in largestElementIndex
		 {	
		   largestElementIndex=i;
		 }
       
   if(rightIndex<sizeOfHeap && A[rightIndex]>A[largestElementIndex])
   {
      largestElementIndex = rightIndex;
   }
     
	   if(largestElementIndex!=i){
	      int temp = A[i];
	      A[i]=A[largestElementIndex];
	      A[largestElementIndex]=temp;
	     
	      MAX_HEAPIFY(A, largestElementIndex);
	   }
   }
   public static void HEAP_SORT(int A[])
   {
  
      BUILD_MAX_HEAP(A);

      for(int i = len - 1;i >= 0; i--){
   
            int temp = A[0];
            A[0]=A[i];
            A[i]=temp;
    
            sizeOfHeap = sizeOfHeap-1;
       
            MAX_HEAPIFY(A,0);
      }
   }
   
   public static void main(String[] args) throws IOException {
   Scanner in = new Scanner(System.in);
   System.out.print("Please input the txt file name that you want to sort (input.txt): "); 
   String input ;
   input = in.nextLine();
   readTextFile(input);
   
  for(int i = 0; i < len; i++)
  {
	  HEAP_SORT(array);
  }
   PrintWriter outputFile = new PrintWriter("output.txt");
   for(int i=0; i <len; i++)
   outputFile.print(array[i] + ",");
   
   outputFile.close();
   System.out.println("Output.txt has been created!\nPlease check the designated folder for the file.");

   }
}