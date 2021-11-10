/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author alfon
 */
public class MyTask extends JFrame {
    public static void main(String[] args) {
        new MyTask();
    }
    
    
    private int chefAmount =10;
    private int clientAmount = 16;
    private ArrayList<Chef> chefs = new ArrayList<Chef>();
    private ArrayList<Client> clients = new ArrayList<Client>();
    private Table table = new Table();

   public MyTask(){
        createChefs();
        createClients();
        executeChefs();
        executeClients();
   }
    
    
    public void createChefs(){
        for(int i = 0; i < chefAmount; i++){
            chefs.add(new Chef(table));
        }
        System.out.println("Chefs creado");
    }
    
    public void createClients(){
        for(int i = 0; i < clientAmount; i++){
            clients.add(new Client(table));
            
        }
        System.out.println("Clientes creados");
    }
    
    public void executeChefs(){
        for(int i = 0; i < chefAmount; i++){
           Thread thread = new Thread(chefs.get(i));
           thread.start();
        }
        
        System.out.println("chefs ejecutados");
    }
    
    public void executeClients(){
        for(int i = 0; i < clientAmount; i++){
           Thread thread = new Thread(clients.get(i));
           thread.start();
        }
        
        System.out.println("clientes ejecutados");
    }
    
    
    
    
    
    
    
    
    
}
