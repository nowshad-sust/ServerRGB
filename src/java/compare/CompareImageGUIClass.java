//completing the FInal GUI for comapare program
//using the FilePicker GUI

package compare;

//importing required packages
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
 
 
public class CompareImageGUIClass extends JFrame {
    
    private String categorySelected;
    private JLabel resultLabel;
    
    //Constructor
    public CompareImageGUIClass() {
        super("CompareImage");
        
        //setLayout(new BorderLayout());
        
        Container mainContainer = new Container();
        mainContainer.setLayout(new BorderLayout());
        
        // set up a file picker component
        FilePicker filePicker = new FilePicker("Pick a file", "Browse...");
        filePicker.setMode(FilePicker.MODE_SAVE);
        filePicker.addFileTypeFilter(".jpg", "JPEG Images");
        filePicker.addFileTypeFilter(".png", "PNG images");
         
        // access JFileChooser class directly
        JFileChooser fileChooser = filePicker.getFileChooser();
        //default fileChooser browsing directory
        fileChooser.setCurrentDirectory(new File("H:\\Project-200\\src\\workshop"));
         
        
        //compare button panel
        JPanel bottomPanel = new JPanel();
        
        bottomPanel.setLayout(new FlowLayout());
        
        JButton compareButton = new JButton("Compare");
        compareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {            
                    compareButtonActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(CompareImageGUIClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        //result showing area
        resultLabel = new JLabel();
        resultLabel.setText("Result Label");
        
        bottomPanel.add(compareButton);
        
        //adding all components to mainPanel
        mainContainer.add(filePicker, BorderLayout.NORTH);
        mainContainer.add(bottomPanel, BorderLayout.CENTER);
        mainContainer.add(resultLabel, BorderLayout.SOUTH);
        
        add(mainContainer);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 300);
        setResizable(false);
        setLocationRelativeTo(null);

    }
    
    //compare button clicked
    private void compareButtonActionPerformed(ActionEvent evt) throws IOException{
        //retrieve the RGB match percentage
        //show the result to the Result label
        
        
        //resultLabel.setText("Match Percentage : " + matchPercent +"%");
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //creating the GUI object
                new CompareImageGUIClass().setVisible(true);
            }
        });
    }
 
}
