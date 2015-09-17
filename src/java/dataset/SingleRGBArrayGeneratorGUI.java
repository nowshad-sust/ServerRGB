//simple GUI to test the programs
package dataset;
//importing requird libraries 

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class SingleRGBArrayGeneratorGUI extends JPanel {
    private String textFieldLabel;
    private String buttonLabel;
     
    private JLabel label;
    private JTextField textField;
    private JButton browseButton;
    private JButton processButton;
    private JButton resizeButton;
    
     
    private JFileChooser fileChooser;
    private JComboBox categoryList;
    
    private String fileName, categorySelected, outputDestination, filePath;
    
    private int mode;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;
     
    public SingleRGBArrayGeneratorGUI(String textFieldLabel, String buttonLabel) {
        this.textFieldLabel = textFieldLabel;
        this.buttonLabel = buttonLabel;
         
        fileChooser = new JFileChooser();
         
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
 
        // creates the GUI
        label = new JLabel(textFieldLabel);
         
        textField = new JTextField(30);
        
        browseButton = new JButton(buttonLabel);
        
        resizeButton = new JButton("Resize");
        
        processButton = new JButton("Process");
        
        String[] categories = { "1","2","5","10","20","50","100","500","1000" };

        //Create the combo box.
        categoryList = new JComboBox(categories);
        //the line below to select an Index by default
        //otherwise Index 0 is shown 
        //categoryList.setSelectedIndex(0);
        
        //now get the default selected value first & assign it to categorySelected
        this.categorySelected = (String)categoryList.getSelectedItem();
        System.out.println("selected1 : " + categorySelected);
        
        //if user selects a category then this happens
        categoryList.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                getCategory(evt);    
            }
        });
        
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browseButtonActionPerformed(evt);            
            }
        });
        resizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                resizeButtonActionPerformed(evt);            
            }
        });
        processButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                processButtonActionPerformed(evt);
            }
            
        });
        
         
        add(label);
        add(textField);
        add(browseButton);
        add(categoryList);
        add(resizeButton);
        add(processButton); 
    }
    
    //getting the selected item from the list
    private void getCategory(ActionEvent evt){
        
        JComboBox cb = (JComboBox)evt.getSource();
        String category = (String)cb.getSelectedItem();
        this.categorySelected = category;
        System.out.println("selected : " + categorySelected);
    }
    
    //process button action
    private void processButtonActionPerformed(ActionEvent evt){
        //this.filePath = getSelectedFilePath();
        try {
           
            processSelectedImage();
           
        } catch (URISyntaxException ex) {
            Logger.getLogger(SingleRGBArrayGeneratorGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void browseButtonActionPerformed(ActionEvent evt) {
        if (mode == MODE_OPEN) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (mode == MODE_SAVE) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
        this.filePath = getSelectedFilePath();
    }
    
    //method to create rgb array of an image
    private void processSelectedImage() throws URISyntaxException{
        
        this.filePath = getSelectedFilePath();
        
        //retrieving file name
        String fullPath = filePath;
        int index = fullPath.lastIndexOf("\\");
        String Name = fullPath.substring(index + 1);
        
        //determining the output Destination directory
        this.outputDestination = ".\\src\\workshop\\Output\\"+categorySelected+"\\";
        
        
        try{
            File file = new File(outputDestination, Name);
            //need to assign the filepath & filename dynamically
            
            //need to check the files already exists or not
            //if not then write directly
            //if exists then rename it
            /*
            String tempFileName=fileName+".0.txt";
            File file = new File(outputDestination, tempFileName);
            if(file.exists()){
                System.out.println("File already exists");
                System.out.println("Renaming the file");
                for(int i = 0;file.exists();++i){
                    tempFileName=fileName +"."+ i+".txt";
                    System.out.println(tempFileName+ " exists");
                    //file.renameTo(new File(outputDestination, tempFileName));
                    file = new File(outputDestination, tempFileName);                    
                }
            }
            fileName = tempFileName;
            */
        }catch(Exception ex){
            
            
        }
        String fileNameWithOutExt = method(Name);
        
        
        //printing infos for testing purposes
        System.out.println("FilePath: "+ filePath);
        System.out.println("FileName: "+ fileNameWithOutExt+".txt");
        System.out.println("Destination: "+ outputDestination);
        System.out.println("started creating the RGB array");
        
        
        //finally creating the RGB array
        CreateRGBarray RGBArrayObject = new CreateRGBarray(filePath, fileNameWithOutExt+".txt", outputDestination);
        
    }
    public String method(String str) {
    if (str.length() > 0) {
      str = str.substring(0, str.length()-4);
    }
    return str;
}
    //method to resize an image & set the text to the resized image path
    private void resizeButtonActionPerformed(ActionEvent evt){
        
        this.filePath = getSelectedFilePath();
        //retrieving file name
        String fullPath = filePath;
        int index = fullPath.lastIndexOf("\\");
        String Name = fullPath.substring(index + 1);
        
        //determining the output image destination
        outputDestination = ".\\src\\workshop\\Input\\"+categorySelected+"\\";
        
        //printing all infos [just for testing purposes]
        System.out.println("FilePath: "+ filePath);
        System.out.println("FileName: "+ Name);
        System.out.println("Destination: "+ outputDestination);
        System.out.println("started resizing the image");
        
        //creating a ResizeImage class object
        //this will resize & store the given image
        ResizeImage RI = new ResizeImage(600, 251, filePath, Name, outputDestination);
        
        //just printing the full relative path of resized image
        String relativePath = outputDestination+"\\"+Name;
        System.out.println(relativePath);
        
        //set the text to the resized image path
        //for further use of this image for processing
        //the textFiled text will be used as imagePath
        textField.setText(relativePath);
    }
 
    public void addFileTypeFilter(String extension, String description) {
        FileTypeFilter filter = new FileTypeFilter(extension, description);
        fileChooser.addChoosableFileFilter(filter);
    }
     
    public void setMode(int mode) {
        this.mode = mode;
    }
     
    public String getSelectedFilePath() {
        return textField.getText();
    }
     
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
}
