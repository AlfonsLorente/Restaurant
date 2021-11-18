/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author alfon
 */
public class Pictures {
    //VARIABLES
    Hashtable<String, BufferedImage> imgs = new Hashtable<>();
    
    //CONSTRUCTOR
    public Pictures(){
        setImages();
    }
    
    //PUBLIC METHODS
    //loadImage: search in the hashtable for an image and returns it.
    public BufferedImage loadImage(String name){
        BufferedImage img = imgs.get(name);
        return img;
    }
    
    //PRIVATE METHODS
    //setImages: sets up the hashtable variable
    private void setImages(){
        try {
            this.imgs.put("background", ImageIO.read(new File("IMG/background.png")));
            this.imgs.put("meatChef", ImageIO.read(new File("IMG/meatchef.png")));
            this.imgs.put("standbyChef", ImageIO.read(new File("IMG/standbychef.png")));
            this.imgs.put("meatClient", ImageIO.read(new File("IMG/meatclient.png")));
            this.imgs.put("standbyClient", ImageIO.read(new File("IMG/standbyclient.png")));
            this.imgs.put("table", ImageIO.read(new File("IMG/table.png")));
            
        } catch (IOException ex) {
            Logger.getLogger(Pictures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}


