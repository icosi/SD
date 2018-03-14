/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.ComUtils;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Jairo
 */
public class Client extends Thread{
    int UID;
    String maquinaServidora;
    int port;
    int stack;
    Card card;
    String trun;
    ComUtils comUtils;
    
    public Client(int UID,String ms, int port) {
        this.UID = UID;
        this.maquinaServidora = ms;
        this.port = port;
    }
    
    public void conectarServidor(){
        
        InetAddress ms;
        Socket socket = null;
        
        try{
            ms = InetAddress.getByName(maquinaServidora);
            socket = new Socket(ms,port);
            /* Obrim un flux d'entrada/sortida amb el servidor */
            comUtils = new ComUtils(socket);
            System.out.println("Se ha establecido conexion");
        }
        catch(IOException e){
            System.out.println("No se ha establecido conexion");
            System.exit(1);
        }
        
    }
    
    public void run(){
        System.out.println("run");
        /*while(true){
            
        }*/
    }

    public int getUID() {
        return UID;
    }

    public int getPort() {
        return port;
    }
    
    public ComUtils getComUtils() {
        return comUtils;
    }

    public String getTrun() {
        return trun;
    }

    public void setTrun(String trun) {
        this.trun = trun;
    }
    
}
