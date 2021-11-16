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
    Hashtable<String, BufferedImage> imgs = new Hashtable<>();
    
    public Pictures(){
        setImages();
    }
    
    public BufferedImage loadImage(String name){
        BufferedImage img = imgs.get(name);
        return img;
    }
    
    private void setImages(){
        try {
            this.imgs.put("cookingchef", ImageIO.read(new File("IMG/cookingchef.png")));
            this.imgs.put("standbychef", ImageIO.read(new File("IMG/standbychef.png")));
            this.imgs.put("eatingclient", ImageIO.read(new File("IMG/eatingclient.png")));
            this.imgs.put("standbyclient", ImageIO.read(new File("IMG/standbyclient.png")));
            this.imgs.put("table", ImageIO.read(new File("IMG/table.png")));
            
        } catch (IOException ex) {
            Logger.getLogger(Pictures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}


