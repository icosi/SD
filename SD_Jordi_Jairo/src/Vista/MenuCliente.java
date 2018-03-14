/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controller.Controller;
import java.util.Scanner;

/**
 *
 * @author Jairo
 */
public class MenuCliente {
    
    private Controller controlador = new Controller();
    
    static private enum OpcionsMenu {
        PLAY(1), 
        STOP(2), 
        SORTIR(0);
        
        public int OpcInd;
        
        private OpcionsMenu(int OpcInd){ 
            this.OpcInd = OpcInd;
        }

        public static OpcionsMenu getByIndex(int OpcInd) {
           for (OpcionsMenu l : OpcionsMenu.values()) {
               if (l.OpcInd == OpcInd){ 
                   return l;
               }
           }
           throw new IllegalArgumentException("Leg not found.");
        }
        
    }
    
    static private enum MenuPlay {
        PLAY(1), 
        STOP(2), 
        SORTIR(0);
        
        public int OpcInd;
        
        private MenuPlay(int OpcInd){ 
            this.OpcInd = OpcInd;
        }

        public static OpcionsMenu getByIndex(int OpcInd) {
           for (OpcionsMenu l : OpcionsMenu.values()) {
               if (l.OpcInd == OpcInd){ 
                   return l;
               }
           }
           throw new IllegalArgumentException("Leg not found.");
        }
        
    }
    
    
    public void iniMenu(String ms, int port , int IA){
        
        switch(IA){
            case 0:
                Scanner sc = new Scanner(System.in);
                int IDU;
                
                System.out.println("Introduce tu numero de Usuario: ");
                IDU = sc.nextInt();
                
                MenuCliente m = new MenuCliente();
                m.showMenu(sc,IDU,ms,port);
                
                break;
            case 1:
                break;
            case 2:
                break;
        }
        
    }
    
    private void showMenu(Scanner sc,int IDU, String ms, int port){
        
        this.controlador.iniClient(IDU, ms, port);
        this.controlador.enviarUID();
        
        System.out.println("Opciones de Juagar");
        OpcionsMenu opcio = null;
        while (opcio != OpcionsMenu.SORTIR) {
            
            for(OpcionsMenu o : OpcionsMenu.values()){
                System.out.println(String.valueOf(o.OpcInd)+" "+ o);
            }
            opcio = OpcionsMenu.getByIndex(sc.nextInt());
            
            switch(opcio) {
                case PLAY:
                    this.controlador.enviarPLAY();
                    this.controlador.recibirSTACK();
                    this.controlador.recibirTURN();
                    this.controlador.enviarBET();
                    this.controlador.recibirBET();
                    this.controlador.recibirCARD();
                    if(this.controlador.getTrun().equals("C")){
                        //Hacer otro menu para cada caso
                        showPlay();
                    }
                    else{
                        //Otro menu de espera
                    }
                    
                    break;
                
                case STOP:
                    this.controlador.enviarSTOP();
                    
                    break;
                
                case SORTIR:
                    System.out.println("Fins Aviat!");
                    break;
                    
            }
            
        }
    }
    
    private void showPlay(){
    
    
    }
    
}
