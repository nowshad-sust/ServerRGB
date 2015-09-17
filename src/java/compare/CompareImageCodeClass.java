/*
    this is the Code class to make the Compare GUI classes
    functional.
*/
package compare;

//importing packages
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author nowshad
 */
public class CompareImageCodeClass {
    
    //function to retrieve the final match percentage from two txt file
    public static float compareResult(String source1,String source2) 
            throws FileNotFoundException, IOException{
        
        //initializing the deviation
        int averageDeviation=0;
        
        //opning the two files
        File f1=new File(source1);
        File f2=new File(source2);
        FileInputStream fi1=new FileInputStream(f1);
        FileInputStream fi2=new FileInputStream(f2); 
        DataInputStream di1=new DataInputStream(fi1);
        BufferedReader br1=new BufferedReader(new InputStreamReader(di1));
        DataInputStream di2=new DataInputStream(fi2);
        BufferedReader br2=new BufferedReader(new InputStreamReader(di2));
        String lineOfFile1; // from first file
        String lineOfFile2; // from second file
        
               
        ArrayList<String> finalArray = new ArrayList<>();
        
        try {
            //calcukating deviations of every pixel for every R,G,B value
            while ((lineOfFile1 = br1.readLine()) != null && (lineOfFile2 = br2.readLine()) != null) {
            // convert current characters from both files to lowerCase
            int valueFromFile1 = Integer.parseInt(lineOfFile1);
            int valueFromFile2 = Integer.parseInt(lineOfFile2);
            
            int currentDeviation = Math.abs(valueFromFile1-valueFromFile2);
            
            //storing the deviations intoo an arayList
            finalArray.add(String.valueOf(currentDeviation));
            }
             
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        
        try{
            //now calculate the total average deviation of two file
            //from the arrayList
            averageDeviation = Integer.parseInt(finalArray.get(0));
            for(int i=1;i<finalArray.size();++i){
                averageDeviation = (averageDeviation+Integer.parseInt(finalArray.get(i)))/2;
            }
            
        }catch(Exception e){
            
        }
        
        //calculting the final Matched percentage
        //one line creates an unknown error
        //thats why done with seperate statements
        float finalMatchPercentage = 255 - averageDeviation;
        finalMatchPercentage = finalMatchPercentage/255;
        finalMatchPercentage = finalMatchPercentage*100;
        
                
        return finalMatchPercentage;
        }
    
    
}
