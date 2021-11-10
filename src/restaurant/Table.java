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
    private int count = 0;
    private int MAXMEALS = 50;
    boolean hasCooked = false;
    boolean hasEaten = false;
    boolean tableInUse = false;
    
    
    public int getCount() {
        return count;
    }

    public void setCount(int contador) {
        this.count = contador;
    }
    
    public synchronized boolean placeMeal(){
        hasCooked = false;
        if(count < MAXMEALS && !tableInUse){
            tableInUse = true;
            count++;
            System.out.println("Ahora hay " + count + " Hamburguesas");
            //isMeal = true;
            hasCooked = true;
            tableInUse = false;
        }else{
            while(tableInUse){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.notifyAll();
        return hasCooked;
    }
    public synchronized boolean takeMeal(){
        hasEaten = false;
        if(count > 0 && !tableInUse){
            tableInUse = true;
            count--;
            System.out.println("Ahora hay " + count + " Hamburguesas");
            hasEaten = true;
            tableInUse = false;

        }else{
            while(tableInUse){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.notifyAll();
        return hasEaten;

    }
    
}
