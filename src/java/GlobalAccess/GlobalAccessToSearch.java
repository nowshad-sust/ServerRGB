/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GlobalAccess;

import compare.CompareWithAllFiles;
import crop.MoravecCustomized;
import dataset.CreateRGBarray;
import dataset.ResizeImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nowshad
 */
public class GlobalAccessToSearch {
    
    private String targetPath;
    private String tempName;
    private String currentFullPath;
    private String resultString;
    public static HashMap<String, String[]> matchInfo = new HashMap<String, String[]>();
    
    public GlobalAccessToSearch(byte[] byteArray, boolean crop) {
        targetPath = "H:\\java\\ServerRGB\\src\\java\\workshop\\temp\\";
        tempName = "tempImage.jpg";
        //convert byte array into an jpg image & strong it
        //to a temporary directory
        convertAndWriteImage(byteArray);
        //checking the cropping requirements & act in respect
        if(crop == true){
            crop(currentFullPath, targetPath + "cropped.jpg");
            currentFullPath = targetPath + "cropped.jpg";
        } else if(crop == false)
        {
            //do nothing
        }
        resize();
        System.out.println("Object created & processed");
    }
    private void resize(){
        String filePath = currentFullPath;
        String fileName = tempName;
        String outputDestination = targetPath;
        ResizeImage RI = new ResizeImage(600, 251, filePath, fileName, outputDestination);
    }
    
    public String search(){
        
        String filePath = targetPath;
        String fileName = tempName;
        String outputDestination = targetPath;
        
        String relativePath = outputDestination+"\\"+fileName;
        System.out.println(relativePath);
        
        //finally creating the RGB array
        //filePath is relative path here
        //filePath = relativePath;
        fileName = "tempFile.txt";
        File file = new File(outputDestination, fileName);
        CreateRGBarray RGBArrayObject = new CreateRGBarray(relativePath, fileName, outputDestination);
        filePath = outputDestination+"\\"+fileName;
        
        CompareWithAllFiles obj = new CompareWithAllFiles(filePath,"H:\\java\\ServerRGB\\src\\java\\workshop\\Output\\");

        //show the result to the Result label
        resultString = obj.compare();
        
        return resultString;
    }
    
    private void convertAndWriteImage(byte[] byteArray){
        try {
            BufferedImage img = ConvertImage.byteArrayToBufferedImage(byteArray);
            currentFullPath = targetPath + tempName;
            ConvertImage.writeConvertedImage(img, currentFullPath);
            
        } catch (IOException ex) {
            Logger.getLogger(GlobalAccessToSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crop(String source, String destination) {
        try{
                MoravecCustomized.ImageCrop(source, destination);
            }catch(Exception e){
                System.out.println("image cropping failed");
            }
    }
		
    
}
