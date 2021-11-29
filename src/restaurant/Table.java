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
    boolean tableInUse = false;
    
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
        while(tableInUse || count > MAXMEALS){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tableInUse = true;//table setted in use
        chef.setHasMeat(false);//chef setted without meat
        count++;//add meat
        hasCooked = true;//set that he cooked
        tableInUse = false;//set free the table
        //suposed time to get back to cooking
        try {
            Thread.sleep((int)Math.floor(Math.random()*(500-200+1)+200));

        } catch (InterruptedException ex) {
            Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
        }
        //notify all the people that somthing might have changed
        this.notifyAll();
        return hasCooked;
    }
    
    //takeMeal: synchronized method that lets one client at a time take the meat
    public synchronized boolean takeMeal(Client client){
        hasEaten = false;
        //if the count is more than the min allowed meals and the table is not in use 
        while(tableInUse || count < 1){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tableInUse = true;//table setted in use
        count--;//leve meat
        client.setHasMeat(true);//client setted with meat
        hasEaten = true;//set that he eated
        tableInUse = false;//set free the table
        //suposed time that he take to start eating
        try {
            Thread.sleep((int)Math.floor(Math.random()*(500-200+1)+200));

        } catch (InterruptedException ex) {
            Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
        }   
        //notify all the people that somthing might have changed
        this.notifyAll();
        return hasEaten;

    }
    
}
