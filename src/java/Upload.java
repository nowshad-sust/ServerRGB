/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import GlobalAccess.ConvertImage;
import GlobalAccess.GlobalAccessToSearch;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.misc.BASE64Decoder;

/**
 *
 * @author nowshad
 */
public class Upload extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("img_name");
        String image = req.getParameter("encoded_image");
        String cropString = req.getParameter("crop");
        boolean crop = false;
        if(cropString == "1"){
            crop = true;
        }else{
            crop = false;
        }
        System.out.println(name);
        //System.out.println(encodedImage);
        PrintWriter writer = resp.getWriter();
        //writer.println("Image Uploaded Successfully");
        //InputStream input = req.getInputStream();
        //byte[] buffer= new byte[1000];
        FileOutputStream out = new FileOutputStream("H:\\java\\ServerRGB\\src\\java\\workshop\\temp\\"+name);
        BASE64Decoder decoder = new BASE64Decoder();
        byte arr[] = decoder.decodeBuffer(image);
        
        out.write(arr);
        out.close();
        
        byte[] byteArray = ConvertImage.imageToByteArray("H:\\java\\ServerRGB\\src\\java\\workshop\\temp\\"+name);
        
            GlobalAccessToSearch obj = new GlobalAccessToSearch(byteArray, crop);
        
            String search = obj.search();
            writer.println(search);
        
       
        // writer.println(search);
       
    }
}
