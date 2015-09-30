/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mellemhere.com;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author MellemHere
 */
public class save {

    
    private final controler con;

    
    private String format = "png";
    
    private int counter = 1;
    
    public save(controler con) {
        this.con = con;
        verifyLocation();
        getFormat();
    }
    
    private void verifyLocation(){
        
        File file = new File(con.getSavePath());
        
        if (!file.exists()) {

            file.mkdir();
            
        }
        
    }
    
    private void getFormat(){
        
        this.format = con.gui.getFormat();
        
    }
    
    public void saveShot(BufferedImage img){
        
        try {

            File outputfile = new File(con.getSavePath() + "\\" + this.counter + "." + format);
            ImageIO.write(img, format, outputfile);
            
        } catch (IOException e) {
            
            System.out.println("Could not save file");
            
        }
        
        counter++;
        
    }
    
    
    
    
    
}
