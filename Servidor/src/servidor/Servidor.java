package servidor;

import java.io.*;
import java.net.*;

public class Servidor {

    private EstatJoc state;
    private static ComUtils comUtils;

    private void sendStack() throws IOException {
        int mS = this.state.getMonedesServidor();
        int mC = this.state.getMonedesClient();
        this.comUtils.writeCommandTwoParametre("STK", comUtils.int2String(mS), comUtils.int2String(mC));
    }

    private void sendTurn() throws IOException {
        char turn = this.state.getTurn();
        this.comUtils.writeCommandOneParametre("TRN", "" + turn);
    }

    private void betServidor(){
        int bS = state.getbS();
        bS++;
        this.state.setbS(bS);
    }

    private void betClient(){
        int bC = state.getbC();
        bC++;
        this.state.setbC(bC);
    }

    private void callServidor(){
        int bC = state.getbC();
        this.state.setbS(bC);
    }

    private void callClient(){
        int bS = state.getbS();
        this.state.setbC(bS);
    }

    private void foldServidor(){
        this.state.setFoldServidor(true);
    }

    private void foldClient(){
        this.state.setFoldClient(true);
    }

    private void checkServidor(){
        this.state.setTurn('C');
    }

    private void checkClient(){
        this.state.setTurn('S');
    }

    private void sendCard(){

    }
    
    public EstatJoc getState(){
        return this.state;
    }
    
    
    
    public static void main(String[] args) {
        ServerSocket serverSocket=null;


        Socket socket;

        int portServidor = 1234;
        int value;
        String str;
        EstatJoc prova;

        if (args.length > 1)
        {
            System.out.println("Us: java Servidor [<numPort>]");
            System.exit(1);
        }

        if (args.length == 1)
            portServidor = Integer.parseInt(args[0]);

        try {
            /* Creem el servidor */
            serverSocket = new ServerSocket(portServidor);
            System.out.println("Servidor socket preparat en el port " + portServidor);

            while (true) {
                System.out.println("Esperant una connexió d'un client.");

                /* Esperem a que un client es connecti amb el servidor */
                socket = serverSocket.accept();
                System.out.println("Connexió acceptada d'un client.");

                /* Associem un flux d'entrada/sortida amb el client */
                comUtils = new ComUtils(socket);

                /* Esperem una comanda */
                str = comUtils.readCommand3();
                System.out.println("Em llegit la comanda: "+str);
                
                

                switch(str){
                    case "PLY":
                        System.out.println("Anem a començar el joc");
                        prova = new EstatJoc(10,10);
                        prova.setPlayers();
                        
                        char turn = prova.getTurn();
                        comUtils.writeChar(comUtils.char2String(turn));
                        if (turn == 'C'){
                            System.out.println("    El servidor es player 2 i agafa segon la carta");
                        }
                        if (turn == 'S'){
                            System.out.println("    El servidor es player 1 i comença");
                        }
                        
                        prova.setCardsCS(turn);

                        char card = prova.getCardClient();
                        comUtils.writeChar(comUtils.char2String(card));
                        
                        char cardServidor = prova.getCardServidor();
                        System.out.println("    La carta del servidor es: "+cardServidor);
                        
                        break;
                        
                    case "STP":
                        
                        break;
                }

              

                
            } // fi del while infinit
        } // fi del try
        catch (IOException ex) {
            System.out.println("Els errors han de ser tractats correctament pel vostre programa");
        } // fi del catch
        finally
        {
            /* Tanquem la comunicacio amb el client */
            try {
                if(serverSocket != null) serverSocket.close();
            }
            catch (IOException ex) {
                System.out.println("Els errors han de ser tractats correctament pel vostre programa");
            } // fi del catch
        }

    } // fi del main
} // fi de la classe




/*
  ID  |  $
̣--------------
      |
      |
      |

s'ha de fer un hashmap amb el seu getters i setters


*/