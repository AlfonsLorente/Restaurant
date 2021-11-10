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
    private int count;
    private Table table;
    boolean hasEaten = false;

    public Client(Table table) {
        this.count = 0;
        this.table = table;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void eatBurger(Table table){
        hasEaten = table.takeMeal();
        try {
            Thread.sleep((int)Math.floor(Math.random()*(8000-5000+1)+5000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(hasEaten){
            count++;
            System.out.println("Este cliente lleva pilladas " + count);
        }
       
    }
    
    @Override
    public void run() {
        while(true){
        this.eatBurger(table);
        }
    }
}
