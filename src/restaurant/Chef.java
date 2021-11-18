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
     private int count;
     private Table table;
     private boolean hasCooked = false;
     private boolean hasMeat = false;

    public Chef(Table table) {
        this.count = 0;
        this.table = table;
    }

    public void setHasMeat(boolean hasMeat) {
        this.hasMeat = hasMeat;
    }

    
    public boolean getHasMeat() {
        return hasMeat;
    }
     
     
     
     public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    public void cookBurger(Table table){
        
        try {
            Thread.sleep((int)Math.floor(Math.random()*(6000-3000+1)+3000));
            hasMeat = true;
        } catch (InterruptedException ex) {
            Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
        }
        hasCooked = table.placeMeal(this);
        
        if(hasCooked){
            count++;
            System.out.println("Este chef lleva hechas " + count);

       }
    }
    
   
    
    @Override
    public void run() {
        while(true){
        this.cookBurger(table);
        }
    }
    
}
