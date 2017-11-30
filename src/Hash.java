import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Hash
{
    private static int[] currArray;
    private static int n;
    private static int left;
    private static int right;
    private static int largest;

    
    public static void buildheap(int[] currArray){
        n = currArray.length - 1;
        for(int i = n/2; i >= 0; i--){
            shiftDown(currArray,i);
        }
    }
    
    public static void shiftDown(int[] currArray, int currIndex){ 
        left = 2 * currIndex;
        right = 2 * currIndex + 1;
        if(left <= n && currArray[left] > currArray[currIndex]){
            largest = left;
        }
        else{
            largest = currIndex;
        }
        
        if(right <= n && currArray[right] > currArray[largest]){
            largest=right;
        }
        if(largest != currIndex){
            exchange(currIndex,largest);
            shiftDown(currArray, largest);
        }
    }
    
    public static void exchange(int i, int j){
        int t=currArray[i];
        currArray[i]=currArray[j];
        currArray[j]=t; 
        }
    
    public static void removeMaxElement(int []currArray0){
        currArray=currArray0;
        buildheap(currArray);
        
        for(int i = n; i > 0; i--){
            exchange(0, i);
            n = n - 1;
            shiftDown(currArray, 0);
        }
    }
    
  
    public static void main(String[] args) throws IOException {    
    	
    	Scanner scanner = new Scanner(new File("input.txt"));
    	int [] tall = new int [100];
    	int i = 0;
    	while(scanner.hasNextInt()){
    	   tall[i++] = scanner.nextInt();
    	}
    	
    	System.out.println();
       
		  
        }
    }



