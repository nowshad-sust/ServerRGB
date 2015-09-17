/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nowshad
 */
public class CompareWithAllFiles {
    File folder;
    String sourcePath;
    
    public static ArrayList<String> filesPath = new ArrayList<>();
    
    public static HashMap<Float, String[]> matchInfo = new HashMap<Float, String[]>(); 
    
    public CompareWithAllFiles(String filePath,String folderDestination){
        folder = new File(folderDestination);
        sourcePath = filePath;
        listFilesForFolder(folder);
    }
    
    public String compare(){
        float matchPercent,highestMatch=0;
        for(int i=0; i < filesPath.size(); i++){
            try {
                matchPercent =
                    CompareImageCodeClass.compareResult(sourcePath, filesPath.get(i));
                File file = new File(filesPath.get(i));
                String folderName = file.getParentFile().getName();
                String arr[]= {filesPath.get(i), folderName};
                
                if(matchPercent > highestMatch)
                    highestMatch = matchPercent;
                
                matchInfo.put(matchPercent, arr);
                
            } catch (IOException ex) {
                System.out.println("Exception in compare");
            }
        }
        return findMatchInfo(highestMatch);
    }
    public String findMatchInfo(float value){
        
        String arr[] = matchInfo.get(value);
        String resultString = "<html>"
                                + "<h3>Search Results:</h3> Best matched:"
                                + value+"% <br>"
                                + " with: "
                                + arr[0]+"<br>"
                                + " value: "
                                + arr[1]+" taka"
                             +"</html>";
        return resultString;
    }
    
    //fucntions that lists all files under a dirctory
    //even subdirectory files
  
  public void listFilesForFolder(final File folder) {
      
    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        // System.out.println("Reading files under the folder "+folder.getAbsolutePath());
        listFilesForFolder(fileEntry);
      } else {
        if (fileEntry.isFile()) {
          String temp = fileEntry.getName();
          if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("txt")){
              //System.out.println("File= " + folder.getAbsolutePath()+ "\\" + fileEntry.getName());
              filesPath.add(folder.getAbsolutePath()+ "\\" + fileEntry.getName());
          }   
        }
      }
    }
  }

    
 
}
