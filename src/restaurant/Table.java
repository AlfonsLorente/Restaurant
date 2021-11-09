/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

/**
 *
 * @author alfon
 */
public class Table {
    private int count = 0;
    private int MAXMEALS = 50;
    public int getCount() {
        return count;
    }

    public void setCount(int contador) {
        this.count = contador;
    }
    
    public synchronized boolean placeMeal(){
        boolean bool = false;
        if(count < MAXMEALS){
            this.setCount(this.getCount()+1);
            System.out.println("Ahora hay " + count);
            bool = true;
        }
        this.notifyAll();
        return bool;
    }
    public synchronized boolean takeMeal(){
        boolean bool = false;
        if(count > 0){
            this.setCount(this.getCount()-1);
            System.out.println("Ahora hay " + count);
            bool = true;
        }
        this.notifyAll();
        return bool;

    }
    
}
