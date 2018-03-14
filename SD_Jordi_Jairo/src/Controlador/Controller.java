/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Client;

/**
 *
 * @author Jairo
 */
public class Controller {
    
    private Client client;
    private ProtocolAuxiliar aux;
    
    public Controller() {
        
    }
    
    public void iniClient(int IDU,String ms, int port ){
        this.client = new Client(IDU,ms,port);
        
        System.out.println("Iniciando Cliente...");
        this.client.conectarServidor();
    }
    
    public void enviarUID(){
        System.out.println("Enviando ID Usuario....");
        aux.send_UID(this.client.getComUtils(), this);
    }
    
    public void enviarPLAY(){
        System.out.println("Enviando Play....");
        aux.send_PLAY(this.client.getComUtils(), this);
    }
    
    public void enviarSTOP(){
        System.out.println("Enviando Stop....");
        aux.send_STOP(this.client.getComUtils(), this);
    }
    
    public void enviarBET(){
        System.out.println("Enviando Bet....");
        aux.send_BET(this.client.getComUtils(), this);
    }
    
    public void enviarCALL(){
        System.out.println("Enviando Call....");
        aux.send_CALL(this.client.getComUtils(), this);
    }
    
    public void enviarFOLD(){
        System.out.println("Enviando Fold....");
        aux.send_FOLD(this.client.getComUtils(), this);
    }
    
    public void enviarCHECK(){
        System.out.println("Enviando Check....");
        aux.send_CHECK(this.client.getComUtils(), this);
    }
    
    public void enviarSHOW(){
        System.out.println("Enviando Show....");
        aux.send_SHOW(this.client.getComUtils(), this);
    }
    
    public void recibirSTACK(){
        System.out.println("Recibiendo Stack...");
        aux.receive_STACK(this.client.getComUtils());
    }
    
    public void recibirTURN(){
        System.out.println("Recibiendo Turno...");
        this.client.setTrun(aux.receive_TURN(this.client.getComUtils()));
    }
    
    public void recibirBET(){
        System.out.println("Recibiendo Bet...");
        aux.receive_BET(this.client.getComUtils());
    }
    
    public void recibirCARD(){
        System.out.println("Recibiendo Card...");
        aux.receive_CARD(this.client.getComUtils());
    }
    
    public void recibirWIN(){
        System.out.println("Recibiendo Win...");
        aux.receive_WIN(this.client.getComUtils());
    }
    
    public void recibirCALL(){
        System.out.println("Recibiendo CALL...");
        aux.receive_CALL(this.client.getComUtils());
    }
    
    public void recibirCHECK(){
        System.out.println("Recibiendo CHECK...");
        aux.receive_CHECK(this.client.getComUtils());
    }
    
    public Client getClient() {
        return client;
    }
    
    public String getTrun(){
        return this.client.getTrun();
    }
    
}
