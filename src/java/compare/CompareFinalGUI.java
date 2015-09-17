//completing the FInal GUI for comapare program
//using the FilePicker GUI


//done this class
package compare;

//importing required packages
import crop.MoravecCustomized;
import dataset.CreateRGBarray;
import dataset.ResizeImage;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
 
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
 
public class CompareFinalGUI extends JFrame {
    
    private JLabel resultLabel;
    
    //Constructor
    public CompareFinalGUI() {
        super("CompareFinal");
        
        //setLayout(new BorderLayout());
        
        Container mainContainer = new Container();
        mainContainer.setLayout(new BorderLayout());
        
        // set up a file picker component
        FilePicker filePicker = new FilePicker("Pick a file", "Browse...");
        filePicker.setMode(FilePicker.MODE_SAVE);
        filePicker.addFileTypeFilter(".jpg", "JPEG Images");
         
        // access JFileChooser class directly
        JFileChooser fileChooser = filePicker.getFileChooser();
        //default fileChooser browsing directory
        fileChooser.setCurrentDirectory(new File(""));
         
        
        //compare button panel
        JPanel bottomPanel = new JPanel();
        
        bottomPanel.setLayout(new FlowLayout());
        
        JButton cropButton = new JButton("Crop");
        cropButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Runnable searchImage = new Runnable(){
                         public void run() {
                            try{
                                String source = FilePicker.textField.getText();
                                String destination = ".\\src\\workshop\\temp\\tempImage.jpg";
                                MoravecCustomized.ImageCrop(source, destination);
                                FilePicker.textField.setText(destination);
                            }catch(Exception e){
                               
                            }
                            
                            }
		         };
		 	(new Thread(searchImage)).start();
                    
                } catch(Exception ex){
                    
                }
            }
            
        });
        
        
        JButton compareButton = new JButton("Compare");
        compareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    //first setting the result label to searching
                    resultLabel.setText("Searching ...");
                    //compareButtonActionPerformed(evt);
                    Runnable searchImage = new Runnable(){
                         public void run() {
                            try{
                                compareButtonActionPerformed(evt);                                
                            }catch(Exception e){
                               
                            }
                            
                            }
		         };
		 	(new Thread(searchImage)).start();
                } catch (Exception ex) {
                    Logger.getLogger(CompareImageGUIClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        //result showing areaimport javax.swing.JOptionPane;
        /*Some piece of code*/
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try{
 
                    File imagefile = new File(".\\src\\workshop\\temp\\tempImage.jpg");
                    File txtfile = new File(".\\src\\workshop\\temp\\tempFile.txt");
                    
                    if(txtfile.delete() && imagefile.delete())
                        System.out.println(imagefile.getName() +" & "
                                    +txtfile.getName()+ " are deleted!");
                    dispose();
                }catch(Exception e){

                    e.printStackTrace();

                }
            }
        });
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        
        resultLabel = new JLabel();
        resultLabel.setText("Result Label");
        resultPanel.add(resultLabel);
        
        bottomPanel.add(cropButton);
        bottomPanel.add(compareButton);
        
        //adding all components to mainPanel
        mainContainer.add(filePicker, BorderLayout.NORTH);
        mainContainer.add(bottomPanel, BorderLayout.CENTER);
        mainContainer.add(resultPanel, BorderLayout.SOUTH);
        
        add(mainContainer);
        
        
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 200);
        setResizable(false);
        setLocationRelativeTo(null);

    }
    
    //compare button clicked
    private void compareButtonActionPerformed(ActionEvent evt) throws IOException{
        
        
        //first resize & process image to make a new rgb image
        //store it to a temp direcoty
        ////RESIZE
        String filePath = FilePicker.textField.getText();
        String fileName = "tempImage";
        String outputDestination = ".\\src\\workshop\\temp";
        ResizeImage RI = new ResizeImage(600, 251, filePath, fileName+".jpg", outputDestination);
        
        String relativePath = outputDestination+"\\"+fileName+".jpg";
        System.out.println(relativePath);
        
        //set the text to the resized image path
        //for further use of this image for processing
        //the textFiled text will be used as imagePath
        FilePicker.textField.setText(relativePath);
        ////PROCESS AS RGB FILE
        
        //finally creating the RGB array
        //filePath is relative path here
        //filePath = relativePath;
        fileName = "tempFile.txt";
        File file = new File(outputDestination, fileName);
        CreateRGBarray RGBArrayObject = new CreateRGBarray(relativePath, fileName, outputDestination);
        filePath = outputDestination+"\\"+fileName;
        FilePicker.textField.setText(filePath);
        
        CompareWithAllFiles obj = new CompareWithAllFiles(filePath,".\\src\\workshop\\output\\");

        //show the result to the Result label
        resultLabel.setText(obj.compare());
    }
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
 
                    File imagefile = new File(".\\src\\workshop\\temp\\tempImage.jpg");
                    File txtfile = new File(".\\src\\workshop\\temp\\tempFile.txt");
                    
                    if(txtfile.delete() && imagefile.delete())
                        System.out.println(imagefile.getName() +" & "
                                    +txtfile.getName()+ " are deleted!");
                }catch(Exception e){

                    e.printStackTrace();

                }
                //creating the GUI object
                new CompareFinalGUI().setVisible(true);
            }
        });
    }
 
}
