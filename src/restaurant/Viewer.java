/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfon
 */
public class Viewer extends Canvas{ //implements Runnable {
    Graphics graphics;
    private BufferStrategy bs;
    
    public Viewer(){
        this.setBackground(Color.CYAN);
        graphics = this.getGraphics();
        this.setVisible(true);
    }
    
     @Override
    public void paint(Graphics g){
       
           
            g.setColor(Color.MAGENTA);
            g.drawRect(70, 70, 200, 200);
 
        }

    //@Override
    //public void run() {
        
   // }
        
        
        
} 
    
    
    

