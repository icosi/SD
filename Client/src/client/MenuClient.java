/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Ico
 */
public class MenuClient {
    
    private Scanner sc = new Scanner(System.in);
    private Controller ctr = new Controller();
    
    static private enum OpcionsMenu {OPCIO1, OPCIO2};
    static private String [] descOpcionsMenu = {"Play", "Sortir "};
    
    
    public MenuClient(){
        
    }
    
    public void iniMenu(String ms, int port) throws IOException{
        Scanner sc = new Scanner(System.in);
        int IDU;

        System.out.println("Introduce tu numero de Usuario: ");
        IDU = sc.nextInt();

        MenuClient m = new MenuClient();
        m.showMenu(sc,IDU,ms,port);
    }
    
    
    private void showMenu(Scanner sc,int IDU, String ms, int port) throws IOException{
        this.ctr.iniClient(IDU, ms, port);

        
        
        System.out.println("\nComanda a enviar: PLY/STP");
        String c = sc.next();
        
        switch(c){
            case "PLY":
                this.ctr.getClient().getComUtils().writePLY();
                menuPlay(sc);
                break;
            
            case "STP":
                this.ctr.getClient().getComUtils().writeSTP();
                break;
        }
        
        
    
    }
    
    private void menuPlay(Scanner sc) throws IOException{
        
        String turn = this.ctr.getClient().getComUtils().readChar();
        System.out.println("    El torn es: "+turn);      
        this.ctr.getClient().setTrun(turn);
        
        String carta = this.ctr.getClient().getComUtils().readChar();
        System.out.println("    Tens la carta: "+carta); 
        
        if ("C".equals(turn)){
            menuPlayer1(sc);
        }
        else{
            menuPlayer2(sc);
        }
        
             
        
    }

    private void menuPlayer1(Scanner sc){
        System.out.println("    Ets el player 1, aixi que comences primer.");

        System.out.println("\n  Comanda a enviar: CHK/BET/FLD");
        String c = sc.next();
       
        switch(c){
            case "CHK":
                break;
                
            case "BET":
                break;
            
            case "FLD":
                menuFold(sc);
                break;
                
        }

    }
    
    private void menuPlayer2(Scanner sc){
        System.out.println("    Ets el player 2. El servidor es primer.");

        System.out.println("\n  Comanda a enviar: CHK/BET/FLD");
        String c = sc.next();
       
        switch(c){
            case "CHK":
                break;
                
            case "BET":
                break;
            
            case "FLD":
                menuFold(sc);
                break;
                
        }
    }
    
    private void menuFold(Scanner sc){
        System.out.println("    Has fet fold. Has perdut.");
        
    }
    
    private void menuRemach(Scanner sc){
        System.out.println("    Vols tornar a jugar? SI/NO");
        String c = sc.next();
        
        switch (c){
            case "SI":
                
                break;
                
            case "NO":
                break;
        }
    }
    
    
}
