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
    //MAIN
    public static void main(String[] args) {
        new MyTask();
    }
    
    //VARIABLES
    private Thread thread ;
    private Viewer viewer;
    private int chefAmount =30;
    private int clientAmount = 45;
    private ArrayList<Chef> chefs = new ArrayList<Chef>();
    private ArrayList<Client> clients = new ArrayList<Client>();
    private Table table = new Table();

    //CONSTRUCTOR
    public MyTask(){
        //Create and execute the people
        createChefs();
        createClients();
        executeChefs();
        executeClients(); 
        
        //Set up the viewer and the frame
        viewer = new Viewer();
        viewer.setChefs(chefs);
        viewer.setClients(clients);
        viewer.setTable(table);
        this.setFrame();
        this.add(viewer);
        //Start the thread
        try{
            thread = new Thread(viewer);
            thread.start();
        }catch(Exception e){
            e.getMessage();
        }
        this.setVisible(true);
        
        
        
  }
    
    //PRIVATE METHODS
    //createChefs: fills the chefs array
    private void createChefs(){
        for(int i = 0; i < chefAmount; i++){
            chefs.add(new Chef(table));
        }
    }
        
    //createChefs: fills the clients array
    private void createClients(){
        for(int i = 0; i < clientAmount; i++){
            clients.add(new Client(table));
            
        }
    }
    
    //executeChefs: Sets all the chefs into different threads
    private void executeChefs(){
        for(int i = 0; i < chefAmount; i++){
           Thread thread = new Thread(chefs.get(i));
           thread.start();
        }
        
    }
        
    //executeClients: Sets all the clients into different threads
    private void executeClients(){
        for(int i = 0; i < clientAmount; i++){
           Thread thread = new Thread(clients.get(i));
           thread.start();
        }
        
    }
    
    //Sets up the frame
    private void setFrame() {
        
        this.setTitle("Restaurant");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 1510, 800);
        this.setResizable(false);        
    }
    
    
    
    
    
    
    
}
