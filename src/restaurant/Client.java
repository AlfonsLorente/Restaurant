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
public class Client extends Thread {
    
    //VARIABLES
    private int count;
    private Table table;
    private boolean hasEaten = false;
    private boolean hasMeat = false;
    
    //CONSTRUCTOR
    public Client(Table table) {
        this.count = 0;
        this.table = table;
    }

    //GETTERS AND SETTERS
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public boolean getHasMeat() {
        return hasMeat;
    }

    public void setHasMeat(boolean hasMeat) {
        this.hasMeat = hasMeat;
    }
    
    //PUBLIC METHODS
    //eatMeat: goes to table, takes a meat and then eat it
    public void eatMeat(Table table){
        //Take a meal from the table
        hasEaten = table.takeMeal(this);
        try {
            //Eat the meat
            Thread.sleep((int)Math.floor(Math.random()*(5000-2000+1)+2000));
            hasMeat = false;
            Thread.sleep((int)Math.floor(Math.random()*(3000-1000+1)+1000));

        } catch (InterruptedException ex) {
            Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Add count to the amount of eaten meats
        if(hasEaten){
            count++;
        }
       
    }
    
    //run: overrides from Runnable and loops infinitely calling the eatMeat method
    @Override
    public void run() {
        while(true){
            this.eatMeat(table);
        }
    }
}
