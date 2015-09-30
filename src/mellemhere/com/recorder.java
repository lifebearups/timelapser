package mellemhere.com;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MellemHere
 */
public class recorder extends SwingWorker<String, String>{

    

    private JLabel screen;
    private int frameRate = 1; //One frame every X seconds
    private boolean runnning = true;
    private final controler con;
    private save saver;
    
    
    public recorder(controler con){
        this.con = con;
    }

    
    
    public void setFrameRate(int fr){
        this.frameRate = fr;
    }
    
    private int getFrameRate(){ //IN MS
        
        int seconds_f = this.frameRate / 60;
        
        return seconds_f * 1000;
    }
    
    public void startCapture(){
        
        this.saver = new save(con);
        this.runnning = true;
        
        System.out.println("Starting with frame rate of " + frameRate + "(" + con.gui.getFrameRate() + "ms) Saving in: " + con.getSavePath());
    }
    
    public void stopCapture(){
        this.runnning = false;
    }
    
    

    private void doScreenShot(){
        
        try {
            Robot robot = new Robot();
            BufferedImage screenshot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            this.saver.saveShot(screenshot);
            
        } catch (AWTException ex) {
            Logger.getLogger(recorder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @Override
    protected String doInBackground() throws Exception {
        
        startCapture();
        
        while(runnning){
            
            doScreenShot();  
            Thread.sleep(con.gui.getFrameRate());
            
        }
        return null;
    }
    
    
    
    
}
