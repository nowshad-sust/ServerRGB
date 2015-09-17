//The Main file for resizing image in different formats
package dataset;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
public class ResizeImage {
    
	//explicit declaration of the targeted dimension of the image
        //have to make it a flexible function when attached to the main program
        private static int IMG_WIDTH;
	private static int IMG_HEIGHT;
        private static String IMG_PATH;
        private static String IMG_NAME;
        private static String IMG_DESTINATION;
        
        public ResizeImage(int width, int height, String ImagePath, String fileName,String destination){
            this.IMG_WIDTH = width;
            this.IMG_HEIGHT = height;
            this.IMG_PATH = ImagePath;
            this.IMG_NAME = fileName;
            this.IMG_DESTINATION = destination;
            
            convertImage(IMG_PATH, IMG_NAME,IMG_DESTINATION);
        }
        
	public void convertImage(String ImagePath, String fileName,String destination){
            
        //load image files & pass them to correspondng function created below block    
	try{
                //loading the main image file
            
		BufferedImage originalImage = ImageIO.read(new File(ImagePath));
                
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                /*
                //writing resized jpg
		BufferedImage resizeImageJpg = resizeImage(originalImage, type);
		ImageIO.write(resizeImageJpg, "jpg", new File("H:\\java\\Project-200\\src\\workshop\\20Tknote.jpg")); 
                //writing resized png
		BufferedImage resizeImagePng = resizeImage(originalImage, type);
		ImageIO.write(resizeImagePng, "png", new File("H:\\java\\Project-200\\src\\workshop\\20Tknote.png")); 
                */
                //writing resized jpg with hint/increased quality
		BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
		ImageIO.write(resizeImageHintJpg, "jpg", new File(destination+"\\"+fileName)); 
                //writing resized png with hint/increased quality
		//BufferedImage resizeImageHintPng = resizeImageWithHint(originalImage, type);
		//ImageIO.write(resizeImageHintPng, "png", new File(destination+"\\"+fileName+".png")); 
 
	}catch(IOException e){
		System.out.println("resizing error " + e.getMessage());
	}
            //System.out.println("Image resized completed");
 
    }
 
    //method to resize image without any quality inhancement
        private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();
 
	return resizedImage;
    }
    //method to resize image with quality inhancement
    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){
 
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();	
	g.setComposite(AlphaComposite.Src);
 
	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
 
	return resizedImage;
    }
}