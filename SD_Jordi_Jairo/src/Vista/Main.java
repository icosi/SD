/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author Jairo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*if (args.length != 1 && args.length != 4 && args.length != 6){
          System.out.println("Us: java Client -h if need help");
          System.exit(1);
        }
        
        if (args.length == 1){
            switch (args[0]){
                case "-h":
                    System.out.println("Us: java Client -s <maquina_servidora> -p <port> [-i 0|1|2]");
                    break;
                default:
                    System.out.println("Us: java Client -h if need help");
                    System.exit(1);
                    break;
            }
        }
        
        if (args.length == 4) {
            int arg1;
            int arg3;
            
            switch (args[0]){
                case "-s":
                    try {
                        arg1 = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        System.err.println("Argument" + args[1] + " must be an integer.");
                        System.exit(1);
                    }
                    break;
                default:
                    System.out.println("Us: java Client -h if need help");
                    System.exit(1);
                    break;
            }
            
            switch (args[2]){
                case "-p":
                    try {
                        arg3 = Integer.parseInt(args[3]);
                        //Manual
                        MenuCliente menu= new MenuCliente();
                        menu.iniMenu(Integer.parseInt(args[1]), arg3, 0);
                        
                    } catch (NumberFormatException e) {
                        System.err.println("Argument" + args[3] + " must be an integer.");
                        System.exit(1);
                    }
                    break;
                default:
                    System.out.println("Us: java Client -h if need help");
                    System.exit(1);
                    break;
            }
            
            
        }
        
        if (args.length == 6) {
            int arg1;
            int arg3;
            int arg5;
            
            switch (args[0]){
                case "-s":
                    try {
                        arg1 = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        System.err.println("Argument" + args[1] + " must be an integer.");
                        System.exit(1);
                    }
                    break;
                default:
                    System.out.println("Us: java Client -h if need help");
                    System.exit(1);
                    break;
            }
            
            switch (args[2]){
                case "-p":
                    try {
                        arg3 = Integer.parseInt(args[3]);
                    } catch (NumberFormatException e) {
                        System.err.println("Argument" + args[3] + " must be an integer.");
                        System.exit(1);
                    }
                    break;
                default:
                    System.out.println("Us: java Client -h if need help");
                    System.exit(1);
                    break;
            }
            
            switch (args[4]){
                case "-i":
                    try {
                        arg5 = Integer.parseInt(args[5]);
                        
                        if(arg5!= 1 || arg5!= 2 ){
                            System.out.println("Us: java Client -h if need help");
                            System.exit(1);
                        }
                        //Con IA
                        MenuCliente menu= new MenuCliente();
                        menu.iniMenu(Integer.parseInt(args[1]),Integer.parseInt(args[3]),arg5);
                        
                        
                    } catch (NumberFormatException e) {
                        System.err.println("Argument" + args[5] + " must be an integer.");
                        System.exit(1);
                    }
                    break;
                default:
                    System.out.println("Us: java Client -h if need help");
                    System.exit(1);
                    break;
            }
            
        }*/
        int port=8080,ia=0;
        MenuCliente menu= new MenuCliente();
        menu.iniMenu("localhost",port,ia);
    }
    
}
