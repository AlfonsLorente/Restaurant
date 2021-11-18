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
    
    private Thread thread ;
    private Viewer viewer;
    private int chefAmount =30;
    private int clientAmount = 45;
    private ArrayList<Chef> chefs = new ArrayList<Chef>();
    private ArrayList<Client> clients = new ArrayList<Client>();
    private Table table = new Table();

   public MyTask(){
        createChefs();
        createClients();
        executeChefs();
        executeClients(); 
        
        viewer = new Viewer();
        viewer.setChefs(chefs);
        viewer.setClients(clients);
        viewer.setTable(table);
        this.setFrame();
        this.add(viewer);
        try{
            thread = new Thread(viewer);
            thread.start();
        }catch(Exception e){
            e.getMessage();
        }
        this.setVisible(true);
        
        
        
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
    
    
    private void setFrame() {
        
        this.setTitle("Flame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 1510, 800);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);        
    }
    
    
    
    
    
    
    
}
