/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Ico
 */
public class Controller {
    
    private Client client;
    
    
    public Controller() {
        
    }
    
    public void iniClient(int IDU, String ms, int port){
        this.client = new Client(IDU, ms, port);
        this.client.conectarServidor();
    }
    
    
    public Client getClient() {
        return client;
    }
    
    
    /*
    
    public void enviarComandes...
    */
    
}
