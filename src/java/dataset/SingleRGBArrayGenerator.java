package dataset;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
 
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
 
public class SingleRGBArrayGenerator extends JFrame {
 
    public SingleRGBArrayGenerator() {
        super("MakeRGBarray");
         
        setLayout(new BorderLayout());
 
        // set up a file picker component
        SingleRGBArrayGeneratorGUI filePicker = new SingleRGBArrayGeneratorGUI("Pick a file", "Browse...");
        filePicker.setMode(SingleRGBArrayGeneratorGUI.MODE_SAVE);
        filePicker.addFileTypeFilter(".jpg", "JPEG Images");
         
        // access JFileChooser class directly
        JFileChooser fileChooser = filePicker.getFileChooser();
        //default fileChooser directory
        fileChooser.setCurrentDirectory(new File(".\\src\\workshop"));
         
        // add the component to the frame
        add(filePicker);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 100);
        setLocationRelativeTo(null);    // center on screen
        setVisible(true);
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SingleRGBArrayGenerator();
            }
        });
    }
 
}
