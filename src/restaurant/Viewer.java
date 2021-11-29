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
public class Viewer extends Canvas implements Runnable {   

    //VARIABLES
    private ArrayList<Chef> chefs = new ArrayList<Chef>();
    private ArrayList<Client> clients = new ArrayList<Client>();
    private Table table;
    private Pictures images;
    
    //CONSTRUCTOR
    public Viewer(){
        images = new Pictures();
        this.setVisible(true);

    }
    
    //GETTERS AND SETTERS
    public void setChefs(ArrayList<Chef> chefs) {
        this.chefs = chefs;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    
    //PUBLIC METHODS
    //paint: Paints the background, the state of the chefs, the clients and the table in the canvas
    public void paint(){
        //Uses the buffered strategy
        BufferStrategy bs = this.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        if (bs==null){
            return;
        }
        if (g==null){
            return;
        }
        //paint all
        g.drawImage(images.loadImage("background"), 0, 0, this);
        paintTable(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.ITALIC, 20)); 
        paintChefs(g);
        paintClients(g);
        bs.show();
        g.dispose();
    }
    

    //run: Override method of Runnable that paints all every 0.1 seconds
    @Override
    public void run() {
        //Initial sleep to prevent pair error
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //implements the buffer strategy
        createBufferStrategy(2);
        while(true){
            //infinite loop painting all
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
            }
            paint();
        }  
    }
        
    //PRIVATE METHODS
    //paintChefs: paints all the chefs at the canvas
    private void paintChefs(Graphics g) {
        int w = 0;
        int h = 0;
        //loops around all the chefs
        for(int i = 0; i < chefs.size(); i++){
            if(i%15 == 0 && i !=0){
                h = h+110;
                w = 0;
            }
            //draw the chefs depending on its state
            if(chefs.get(i).getHasMeat()){
                g.drawImage(images.loadImage("meatChef").getScaledInstance(100, -1, BufferedImage.SCALE_SMOOTH), w, h, this);
            }else{
                g.drawImage(images.loadImage("standbyChef").getScaledInstance(100, -1, BufferedImage.SCALE_SMOOTH), w, h, this);

            }
            g.drawString(Integer.toString(chefs.get(i).getCount()),w+15,h+80);

            w = w + 100;

        }
    }
    //paintClients: paints all the clients at the canvas
    private void paintClients(Graphics g) {
        int w = 0;
        int h = 400;
        //loops around all the clients
        for(int i = 0; i < clients.size(); i++){
            if(i%15 == 0 && i !=0){
                h = h+110;
                w= 0;
            }
            //draw the clients depending on its state
            if(clients.get(i).getHasMeat()){
                g.drawImage(images.loadImage("meatClient").getScaledInstance(100, -1, BufferedImage.SCALE_SMOOTH), w, h, this);
            }else{
                g.drawImage(images.loadImage("standbyClient").getScaledInstance(100, -1, BufferedImage.SCALE_SMOOTH), w, h, this);
            }
            g.drawString(Integer.toString(clients.get(i).getCount()),w+15,h+80);

            w = w + 100;

        }
    }
    
    //paitTable: Paints the table at the canvas
    private void paintTable(Graphics g) {
        g.drawImage(images.loadImage("table").getScaledInstance(400, -1, BufferedImage.SCALE_SMOOTH), this.getWidth()/3+63,this.getHeight()/3-50, this);
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 40)); 
        g.drawString(Integer.toString(table.getCount()),  this.getWidth()/2,this.getHeight()/3+15);
    }

    
} 
    
