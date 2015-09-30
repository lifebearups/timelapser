package mellemhere.com;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;


/**
 *
 * @author MellemHere
 */
public class preview extends SwingWorker<String, String>{

    
    private final boolean runnable = true;
    private tGUI frame;
    private final JLabel preview;
    private final controler con;
    private boolean AS;
    private areaSelector area;
    private JFrame jframe;
    private boolean hideFrame = false;
    
    public preview(tGUI frame, controler con) {
        this.jframe = frame;
        this.frame = frame;
        this.preview = frame.getPreview();
        this.con = con;
    }

    public preview(areaSelector aSelector, controler con) {
        this.preview = aSelector.getPreview();
        this.jframe =  aSelector;
        this.con = con;
        this.AS = true;
        System.out.println("yay" + this.AS);
    }
    
    
    private void updateFrame(BufferedImage img){
        /*
            RESIZE imgade to fit frame
        */
        
        if(con.customArea || this.AS){
           img = img.getSubimage(con.screenPosition.width, con.screenPosition.height, con.screenSize.width, con.screenSize.height);
        }
        Image reimg = img.getScaledInstance(this.preview.getWidth(), this.preview.getHeight(), Image.SCALE_SMOOTH);
        
        
        this.preview.setIcon(new ImageIcon(reimg));
        
    }
    
    private void dopreviewShot(){
        
       
        
        try {
            Robot robot = new Robot();
            BufferedImage previewshot;
            if(AS == false){
                previewshot = robot.createScreenCapture(new Rectangle(new Dimension(this.frame.getRHeight(),this.frame.getRWidth())));
            }else{
                previewshot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            }
            updateFrame(previewshot);
        } catch (AWTException ex) {
            Logger.getLogger(recorder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @Override
    protected String doInBackground() throws Exception {
        while(runnable){
            dopreviewShot();
            try {
                Thread.sleep(con.getFrameRate());
            } catch (InterruptedException ex) {
                Logger.getLogger(preview.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void executSnapShot() {
        dopreviewShot();
    }


    
    
    
    
    
}
