/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;

/**
 *
 * @author Ico
 */
public class Main{

    public static void main(String[] args) throws IOException{
        try{
            int port = 1234;
            MenuClient menu = new MenuClient();
            menu.iniMenu("localhost",port);
        }
        
        catch (IOException e){
            System.out.println("Els errors han de ser tractats correctament en el vostre programa.");
        }
        

    }
}

