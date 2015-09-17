package dataset;


import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 *
 * @author nowshad
 */

//this class creates an object containging RGB values
class createRGBObject {
    public int red;
    public int green;
    public int blue;
    
    
    public createRGBObject(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}

//this class loads an image
//creates an 2D array as the same dimension on the image
//fills the array indexes with corresponding pixel RGBObjects 
public class CreateRGBarray implements Serializable{
    public static int height, width;
    public static String fileName, outputDestination;
    
    public CreateRGBarray(String ImagePath, String fileName, String outputDestination){
        this.fileName = fileName;
        this.outputDestination = outputDestination;
        try {
      // get the BufferedImage, using the ImageIO class
        BufferedImage image = 
        //ImageIO.read(this.getClass().getResource(ImagePath));
        ImageIO.read(new File(ImagePath));
        //image dimension setup
        getImageDimension(image);
        //call to process the image
        marchThroughImage(image);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    //getting images dimension
    private void getImageDimension(BufferedImage image){
        this.width = image.getWidth();
        this.height = image.getHeight();
        //System.out.println("width, height: " + width + ", " + height);
    }
    
    //the core function to process the image & fill the array with RGB objects
    private void marchThroughImage(BufferedImage image) throws IOException {
    
     createRGBObject[][] RGBarray = new createRGBObject[height][width];
    
    //now looping through the whole image pixel by pixel
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGBarray[y][x] = returnRGBObject(image.getRGB(x,y));
        //System.out.print("[");
        //System.out.print(RGBarray[y][x].red +","+RGBarray[y][x].green+","+RGBarray[y][x].blue);
        //System.out.print("]");
      }
      //System.out.println();
    }
    //call to a method which writes the RGBarray in a file
    //writeRGBArray(RGBarray, fileName, outputDestination);
    writeRGBArrayInNewFormat(RGBarray, fileName, outputDestination);
  }
    
    
   //The method which writes the RGBarray to a file same as the given format
    public void writeRGBArray(createRGBObject[][] RGBarray, String fileName, String outputDestination) throws IOException{
        BufferedWriter output = null;
        try {
            //need to assign the filepath & filename dynamically
            //this is just recreating a file object
            //try using the file object created in JFilePicker
            // during the renaming process
            File file = new File(outputDestination, fileName);
            
            output = new BufferedWriter(new FileWriter(file));
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                      output.write("[");
                      output.write(RGBarray[y][x].red +","+RGBarray[y][x].green+","+RGBarray[y][x].blue);
                      output.write("]");
                    }
                    output.write("/n");
                  }
                //System.out.println("RGB array created successfully");
            
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) output.close();
        }
              
    }
    
    
    //The method which writes the RGBarray to a file in new format
    public void writeRGBArrayInNewFormat(createRGBObject[][] RGBarray, String fileName, String outputDestination) throws IOException{
        BufferedWriter output = null;
        try {
            //need to assign the filepath & filename dynamically
            //this is just recreating a file object
            //try using the file object created in JFilePicker
            // during the renaming process
            File file = new File(outputDestination, fileName);
            
            output = new BufferedWriter(new FileWriter(file));
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                      output.write(RGBarray[y][x].red+"\n");
                      output.write(RGBarray[y][x].green+"\n");
                      output.write(RGBarray[y][x].blue+"\n");
                    }
                    //output.write("/n");
                  }
                //System.out.println("RGB array created successfully");
            
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) output.close();
        }
              
    }
    
    
    public createRGBObject returnRGBObject(int pixel) {
    //running bitwise operations with the color conversion
    int alpha = (pixel >> 24) & 0xff;
    int red = (pixel >> 16) & 0xff;
    int green = (pixel >> 8) & 0xff;
    int blue = (pixel) & 0xff;
    
    return new createRGBObject(red,green,blue);
  }
   
    //Main function only for testing purpose of this file
    
    /*public static void main(String args[]) throws FileNotFoundException, IOException{
        CreateRGBarray RGBArrayObject = new CreateRGBarray("\\Input\\20Tknote.jpg", "20.txt", ".\\src\\workshop\\Output\\");
        
    }*/
}