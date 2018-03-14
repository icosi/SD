package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args)
    {
        String nomMaquina, str;
        int numPort, value;

        InetAddress maquinaServidora;
        Socket socket = null;
        ComUtils comUtils;

        numPort    = 1234;
        
        Scanner scan = new Scanner(System.in);

        try
        {
            /* Obtenim la IP de la maquina servidora */
            maquinaServidora = InetAddress.getLocalHost();

            /* Obrim una connexio amb el servidor */
            socket = new Socket(maquinaServidora, numPort);

            /* Obrim un flux d'entrada/sortida amb el servidor */
            comUtils = new ComUtils(socket);

            
            
            /* Demanem comanda al client*/
            /*
            System.out.println("Comanda a enviar");
            String c = scan.next();
            comUtils.writeCommand(c);
            
            char carta = comUtils.readChar();
            */
            
            comUtils.writePLY();
            String carta = comUtils.readChar();

            System.out.println("Tens la carta: "+carta);
            
            
            
            /* Enviem el valor 10 al servidor */
            //comUtils.write_int32(10);

            /* Llegim la resposta del servidor */
            //value = comUtils.read_int32();

            //System.out.println("He enviat un 10, la resposta del servidor es " + value);

            /* Tornem a enviar un altre valor */
            //comUtils.write_int32(12);

            /* Ara hauriem de rebre un missatge d'error */
            /*
            value = comUtils.read_int32();

            if (value != 99)
            {
                System.out.println("No he rebut un 99 de resposta, sino un " + value);
                System.exit(1);
            }
            else
            {
                str = comUtils.read_string();
                System.out.println("He enviat un 12, la resposta és el següent missatge d'error: " + str);
            }
            */
            

        }
        catch (IOException e)
        {
            System.out.println("Els errors han de ser tractats correctament en el vostre programa.");
        }
        finally
        {
            try {
                if(socket != null) socket.close();
            }
            catch (IOException ex) {
                System.out.println("Els errors han de ser tractats correctament pel vostre programa");
            } // fi del catch
        }
    } // fi del main
} // fi de la classe