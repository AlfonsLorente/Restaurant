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
public class Table {
    
    //VARIABLES
    private int count = 0;
    private int MAXMEALS = 50;
    boolean hasCooked = false;
    boolean hasEaten = false;
    
    //GETTERS AND SETTERS
    public int getCount() {
        return count;
    }

    public void setCount(int contador) {
        this.count = contador;
    }
    
    //PUBLIC METHODS
    //placeMeal: synchronized method that lets one chef at a time leave the meat
    public synchronized boolean placeMeal(Chef chef){
        hasCooked = false;
        while(count > MAXMEALS){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        chef.setHasMeat(false);//chef setted without meat
        count++;//add meat
        hasCooked = true;//set that he cooked
        //notify all the people that somthing might have changed
        this.notifyAll();
        return hasCooked;
    }
    
    //takeMeal: synchronized method that lets one client at a time take the meat
    public synchronized boolean takeMeal(Client client){
        hasEaten = false;
        //if the count is more than the min allowed meals and the table is not in use 
        while(count < 1){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        count--;//leve meat
        client.setHasMeat(true);//client setted with meat
        hasEaten = true;//set that he eated

        //notify all the people that somthing might have changed
        this.notifyAll();
        return hasEaten;

    }
    
}
