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
     boolean hasCooked = false;

    public Chef(Table table) {
        this.count = 0;
        this.table = table;
    }
     
     
     
     public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void cookBurger(Table table){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
        }
        hasCooked = table.placeMeal();
        if(hasCooked){
            this.setCount(this.getCount()+1);
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
