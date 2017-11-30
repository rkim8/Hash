/* Programmer: Richard Kim
 * Date: February 19th, 2014
 * Lecture: 2215
 * Purpose: The purpose of this program is to read a txt file that contains a list of numbers that are
 *          separated by commas. The program will then read each number accordingly and determine which 
 *          numbers are higher/lower than others. Once the list of numbers have been sorted, it will be 
 *          output as an array in a separate output text file.
 */


//Here we import every necessary package
import java.io.*;
import java.util.*;


public class HeapSort{

public static int[] array = new int[50];
public static int size;
public static int sizeOfHeap; 
public static int length = 0;

//This is used in order to read the name of the input.txt file
public static void readTextFile(String filengthame) {
FileReader file = null;

try {
	  String line = null;
	  String ar[] = new String[50];
	  file = new FileReader(filengthame);
	  BufferedReader reader = new BufferedReader(file);
	 
	 while ((line = reader.readLine()) != null )
	 	{
		 	ar = line.split(",");
	 	}

     length = ar.length;

	 for(size = 0; size < length; size++)
	   {
	       array[size] = Integer.parseInt(ar[size]);
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

	public static int rSide(int p)
		{
		   //returns right index of a zero based array
		   return 2 + p * 2;
		}
  
	public static int lSide(int p)
	   {
	      //returns left index of a zero index based array
	      return 1 + p * 2;
	   }

   public static void BUILD_MAX_HEAP(int A[])
   {		
	   //Here we create the size of the Heap
	      sizeOfHeap = length; 
	     
	      for(int i = length/2; i >= 0; i--)//since n/2, n/2+1 ... are leaves we can start from n/2 step downwards
		   {
		      //Below is where we bring up Max Heapify on each root node, beginning with n/2
		      MAX_HEAPIFY(A, i);
		   }
   }
   
   //Here we create our binary tree that will sort each index/heaps
   public static void MAX_HEAPIFY(int A[],int i)
   {
	  //Below we are creating the indexs of each side of the tree node, as well as a 
	  int rightHeap = rSide(i);//The right heap is created here
      int leftHeap = lSide(i);//The left heap is created here
      int biggestHeap = -1;//Here we are creating the biggest object to be used later; this is because the index index cannot be below 0.

      if(leftHeap < sizeOfHeap && A[leftHeap] > A[i])
      {
        biggestHeap = leftHeap;
      }
         else //if max heap property is not violated copy the root's index in biggestHeap
		 {	
		   biggestHeap = i;
		 }
       
   if(rightHeap < sizeOfHeap && A[rightHeap]>A[biggestHeap])
   {
      biggestHeap = rightHeap;
   }
     
	   if(biggestHeap != i)
	   {
	      int placeHolder = A[i];
	      A[i] = A[biggestHeap];
	      A[biggestHeap] = placeHolder;
	     
	      MAX_HEAPIFY(A, biggestHeap);
	   }
   }
   
   public static void HEAP_SORT(int biggestAmount[])
   {
      BUILD_MAX_HEAP(biggestAmount);

      for(int i = length - 1; i >= 0; i--)
      {
   
            int placeHolder = biggestAmount[0];
            biggestAmount[0] = biggestAmount[i];
            biggestAmount[i] = placeHolder;
    
            sizeOfHeap = sizeOfHeap - 1;
       
            MAX_HEAPIFY(biggestAmount,0);
      }
   }
   
   public static void main(String[] args) throws IOException {
  
   //The code below will request the user to input the name of the text file he or she would like
   //the program to read and sort.
   Scanner in = new Scanner(System.in);
   System.out.print("Please input the name of the txt file you would like to sort (input.txt): "); 
   String txtFilengthame;
   txtFilengthame = in.nextLine();
   readTextFile(txtFilengthame);
   
  for(int i = 0; i < length; i++)
  {
	  HEAP_SORT(array);
  }
  
   PrintWriter outputFile = new PrintWriter("output.txt");
  
  //A loop is created in order to separate each number by a comma
  for(int i = 0; i < length; i++)
  {
	 outputFile.print(array[i] + ",");
  }
   
  	//The output file will finally close and the prompt notifies
    //the user that the output file has been created.
   outputFile.close();
   System.out.println("Your numbers have been sorted and has been placed inside output.txt!\nPlease check the designated folder for the file.");

   }
}