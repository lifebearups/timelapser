package mellemhere.com;

import java.awt.Dimension;
import javax.swing.SwingUtilities;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author MellemHere
 */
public class controler {
    
    
    public tGUI gui;
    private preview preview;
    
    public static boolean running = false;
    
    public recorder recorder;
    
    public String savePath = "";
    
    private String real_savePath;
    
    public String projectName = "Project Name";
    
    public Dimension screenSize = null;
    public Dimension screenPosition = null;
    public boolean customArea = false;
    
    public void toogleMouse(){
       
    }
    
    
    
    /*
    
        INITIALIZE TIMELAPSER
    
    */
    private void init(){
        this.gui = new tGUI(this);
        this.gui.setTitle("TimeLapser");
        this.preview = new preview(gui,this);
        this.gui.updateSaveLocationHint(this.getSavePath());
        this.gui.setVisible(true);
        this.recorder = new recorder(this);
        
        
        this.preview.execute();//Starts preview

        this.savePath = getSavePath();
    }
    
    
    public void startRecording(){
        running = true;
        
        gui.lockOptions();

    }

    void setCustomArea(int winX, int winY, int winW, int winH){
        this.customArea = true;
        this.screenSize = new Dimension(winW,winH);
        this.screenPosition = new Dimension(winX,winY);
    }
    
    void setProjectName(String name){
        
        this.projectName = name;
        gui.updateSaveLocationHint(this.real_savePath +  "\\" + projectName );
        
    }
    
    void stopRecording() {
        
        gui.unlockOptions();
               
        recorder.stopCapture();
        
        running = false;
        
    }
    
    public controler(){
        init();
    }

    void savePath(String absolutePath) {
        this.savePath = absolutePath + "\\" + projectName;
        this.real_savePath = absolutePath;
        gui.updateSaveLocationHint(absolutePath + "\\" + projectName);
    }

    public String getSavePath(){
        if(savePath.equals("")){
            this.real_savePath = System.getProperty("user.dir");
            return System.getProperty("user.dir") + "\\" + projectName;
        }else{
            return this.savePath;
        }
        
    }
    
    public int getFrameRate(){
        return this.gui.getFrameRate();
    }
    
}
