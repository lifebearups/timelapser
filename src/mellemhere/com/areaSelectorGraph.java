/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mellemhere.com;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.SwingWorker;

/**
 *
 * @author MellemHere
 */
public class areaSelectorGraph extends SwingWorker<String, String>{
        
    private final areaSelector as;
    private Point firstPoint = null;
    private Point secondPoint;
    public Point mouseLocation;
    public boolean running = true;
    private final Graphics gra;
    
    public areaSelectorGraph(areaSelector as) {
        this.as = as;
        this.gra = as.getGraphics();
    }
    
    public void setMouseLocation(Point location){
        mouseLocation = location;
    }
    
    public void clickDone(MouseEvent e){
        
        if(this.firstPoint == null){
            this.firstPoint(e.getLocationOnScreen());
        }else{
            
        }
        
    }
    
    private void firstPoint(Point p){
        this.firstPoint = p;
        this.running = true;
        System.out.println("Executed");
        execute();
    }

    private void finilizedraw(){
        this.running = false;
    }
    
    
    @Override
    protected String doInBackground() throws Exception {
        while(this.running){
            this.draw();
            System.out.println("Drwa");
        }
        return null;
    }

    private void draw() {
        System.out.println("Printed1");
        gra.dispose();
        
        //gra.drawLine(x1, y1, x2, y2);
        
        System.out.println("Printed");
        
        as.update(gra);
        as.repaint();
        
    }
    
    
    
}
