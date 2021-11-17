/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author alfon
 */
public class Viewer extends Canvas{ //implements Runnable {
    private Graphics graphics;
    private BufferStrategy bs;
    private ArrayList<Chef> chefs = new ArrayList<Chef>();
    private ArrayList<Client> clients = new ArrayList<Client>();
    private Table table;
    private Pictures images;
    

    public Viewer(){
        images = new Pictures();
        this.setBackground(Color.CYAN);
        graphics = this.getGraphics();
        this.setVisible(true);
        Timer timer = new Timer(800, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Viewer.this.repaint();
            }
        });
        timer.start();
    }
    

    public void paint(Graphics g){
        /*bs = this.getBufferStrategy();
        if (bs==null){
            this.createBufferStrategy(2);
        }else{
            g = bs.getDrawGraphics();
        }*/
        paintTable(g);
        paintChefs(g);
        paintClients(g);
       /*bs.show();
       g.dispose();*/
       g.dispose();
        
 
    }
    
    


    public void setChefs(ArrayList<Chef> chefs) {
        this.chefs = chefs;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    

    private void paintTable(Graphics g) {
        g.drawImage(images.loadImage("table").getScaledInstance(400, -1, BufferedImage.SCALE_SMOOTH), this.getWidth()/3+63,this.getHeight()/3-50, this);
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 40)); 
        g.drawString(Integer.toString(table.getCount()),  this.getWidth()/2,this.getHeight()/3+15);
    }

    private void paintChefs(Graphics g) {
        int w = 0;
        int h = 0;
        
        for(int i = 0; i < chefs.size(); i++){
            if(i%15 == 0 && i !=0){
                h = h+110;
                w= 0;
            }
            if(chefs.get(i).hasCooked){
                g.drawImage(images.loadImage("meatChef").getScaledInstance(100, -1, BufferedImage.SCALE_SMOOTH), w, h, this);
            }else{
                g.drawImage(images.loadImage("standbyChef").getScaledInstance(100, -1, BufferedImage.SCALE_SMOOTH), w, h, this);

            }
            
            w = w + 100;

        }
    }

    private void paintClients(Graphics g) {
        int w = 0;
        int h = 400;
        
        for(int i = 0; i < clients.size(); i++){
            if(i%15 == 0 && i !=0){
                h = h+110;
                w= 0;
            }
            if(clients.get(i).hasEaten){
                g.drawImage(images.loadImage("meatClient").getScaledInstance(100, -1, BufferedImage.SCALE_SMOOTH), w, h, this);
            }else{
                g.drawImage(images.loadImage("standbyClient").getScaledInstance(100, -1, BufferedImage.SCALE_SMOOTH), w, h, this);

            }
            
            w = w + 100;

        }
    }
        
    // @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        graphics = this.getGraphics();
        if(graphics == null){
            return;
        }
        paint(graphics);
        }
    }
        
        
} 
    
    
    

