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
        //if the count is less than the max allowed meals and the table is not in use
        if(count < MAXMEALS && !tableInUse){
            tableInUse = true;//table setted in use
            chef.setHasMeat(false);//chef setted without meat
            count++;//add meat
            hasCooked = true;//set that he cooked
            tableInUse = false;//set free the table
            //suposed time to get back to cooking
            try {
                Thread.sleep((int)Math.floor(Math.random()*(1000-500+1)+500));
           
            } catch (InterruptedException ex) {
                Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
            }
        //if the table is in use just wait
        }else{
            while(tableInUse){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //notify all the people that somthing might have changed
        this.notifyAll();
        return hasCooked;
    }
    
    //takeMeal: synchronized method that lets one client at a time take the meat
    public synchronized boolean takeMeal(Client client){
        hasEaten = false;
        //if the count is more than the min allowed meals and the table is not in use
        if(count > 0 && !tableInUse){
            tableInUse = true;//table setted in use
            client.setHasMeat(true);//client setted with meat
            count--;//leve meat
            hasEaten = true;//set that he eated
            tableInUse = false;//set free the table
            //suposed time that he take to start eating
            try {
                Thread.sleep((int)Math.floor(Math.random()*(1000-500+1)+500));
           
            } catch (InterruptedException ex) {
                Logger.getLogger(Chef.class.getName()).log(Level.SEVERE, null, ex);
            }   
        //if the table is in use just wait    
        }else{
            while(tableInUse){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //notify all the people that somthing might have changed
        this.notifyAll();
        return hasEaten;

    }
    
}
