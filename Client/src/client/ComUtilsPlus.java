/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import static com.sun.org.apache.xml.internal.utils.XMLCharacterRecognizer.isWhiteSpace;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author jsalvagu9.alumnes
 */
public class ComUtilsPlus extends ComUtils {
    
    public ComUtilsPlus(File file) throws IOException {
        super(file);
    }
    
    
    /*
    
    ComUtils functions:
        public void writeSpace():
        public char readSpace(char ch);
    
        public void writeCommand();
        public String readCommand(String command);
        
        public void writeCommandOneParametre();
        public String readCommandOneParametre(String command, String parametre);
    
        public void writeCommandTwoParametre();
        public String readCommandTwoParametre(String command, String parametre1, String parametre2);
    
        public int string2int(String str);
        public String int2String(int int);
    
  
    
    */
    
    
    
    public void writeSpace() {
        char space = ' ';
        
    }
    
    
    public char readSpace(char ch){
        char test = 0;
        if(isWhiteSpace(ch)){
            return ch;
        }
        return test;
    }
    
    
    public void writeCommand(){
        
    }

    
    public String readCommand(String command){
        return command;
    }
    
    
    public void writeCommandOneParametre(){
        
    }

    public void readCommandOneParametre(String command, String parametre){
        
    }

    
    
    public int string2int(String myString){
        int i = Integer.parseInt(myString);
        return i;
    }
    
    public String int2String(int myInt){
        String str = Integer.toString(myInt);
        return str;
    }
       
    
    
}
