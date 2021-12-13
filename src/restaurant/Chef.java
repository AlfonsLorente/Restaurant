/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfon
 */
public class Chef extends Thread{
    
    //VARIABLES
     private int count;
     private Table table;
     private boolean hasCooked = false;
     private boolean hasMeat = false;

    //CONSTRUCTOR
    public Chef(Table table) {
        this.count = 0;
        this.table = table;
    }

    //SETTERS AND GETTERS
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void setHasMeat(boolean hasMeat) {
        this.hasMeat = hasMeat;
    }

    
    public boolean getHasMeat() {
        return hasMeat;
    }
     
    //PUBLIC METHODS
    //cookMeat: cooks a meat and take it to the table
    public void cookMeat(Table table){
        //Cook the meat
        try {
            Thread.sleep((int)Math.floor(Math.random()*(3000-1000+1)+1000));
            hasMeat = true;
            Thread.sleep((int)Math.floor(Math.random()*(2000-500+1)+500));

        } catch (InterruptedException ex) {
            Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Take the meat to the table
        hasCooked = table.placeMeal(this);
        //Add count to the amount of cooked meats
        if(hasCooked){
            count++;
       }
    }
    
   
    //run: overrides from Runnable and loops infinitely calling the cookMeat method
    @Override
    public void run() {
        while(true){
        this.cookMeat(table);
        }
    }
    
}
