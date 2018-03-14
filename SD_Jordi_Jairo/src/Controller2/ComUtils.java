package Controller;

/**
 *
 * @author jordi
 */
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Card;

public class ComUtils
{
    /* Mida d'una cadena de caracters */
    private final int STRSIZE = 40;
    /* Objectes per escriure i llegir dades */
    private DataInputStream dis;
    private DataOutputStream dos;

    public ComUtils(Socket socket) throws IOException
    {
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    public ComUtils(File file) throws IOException
    {
        dis = new DataInputStream(new FileInputStream(file));
        dos = new DataOutputStream(new FileOutputStream(file));
    }

    /* Llegir un enter de 32 bits */
    public int read_int32() throws IOException
    {
        byte bytes[] = new byte[4];
        bytes  = read_bytes(4);

        return bytesToInt32(bytes,"be");
    }

    /* Escriure un enter de 32 bits */
    public void write_int32(int number) throws IOException
    {
        byte bytes[]=new byte[4];

        int32ToBytes(number,bytes,"be");
        dos.write(bytes, 0, 4);
    }

    /* Llegir un string de mida STRSIZE */
    public String read_string() throws IOException
    {
        String str;
        byte bStr[] = new byte[STRSIZE];
        char cStr[] = new char[STRSIZE];

        bStr = read_bytes(STRSIZE);

        for(int i = 0; i < STRSIZE;i++)
            cStr[i]= (char) bStr[i];

        str = String.valueOf(cStr);

        return str.trim();
    }

    /* Escriure un string */
    public void write_string(String str) throws IOException
    {
        int numBytes, lenStr;
        byte bStr[] = new byte[STRSIZE];

        lenStr = str.length();

        if (lenStr > STRSIZE)
            numBytes = STRSIZE;
        else
            numBytes = lenStr;

        for(int i = 0; i < numBytes; i++)
            bStr[i] = (byte) str.charAt(i);

        for(int i = numBytes; i < STRSIZE; i++)
            bStr[i] = (byte) ' ';

        dos.write(bStr, 0,STRSIZE);
    }

    /* Passar d'enters a bytes */
    private int int32ToBytes(int number,byte bytes[], String endianess)
    {
        if("be".equals(endianess.toLowerCase()))
        {
            bytes[0] = (byte)((number >> 24) & 0xFF);
            bytes[1] = (byte)((number >> 16) & 0xFF);
            bytes[2] = (byte)((number >> 8) & 0xFF);
            bytes[3] = (byte)(number & 0xFF);
        }
        else
        {
            bytes[0] = (byte)(number & 0xFF);
            bytes[1] = (byte)((number >> 8) & 0xFF);
            bytes[2] = (byte)((number >> 16) & 0xFF);
            bytes[3] = (byte)((number >> 24) & 0xFF);
        }
        return 4;
    }

    /* Passar de bytes a enters */
    private int bytesToInt32(byte bytes[], String endianess)
    {
        int number;

        if("be".equals(endianess.toLowerCase()))
        {
            number=((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) |
                    ((bytes[2] & 0xFF) << 8) | (bytes[3] & 0xFF);
        }
        else
        {
            number=(bytes[0] & 0xFF) | ((bytes[1] & 0xFF) << 8) |
                    ((bytes[2] & 0xFF) << 16) | ((bytes[3] & 0xFF) << 24);
        }
        return number;
    }
    //llegir bytes.
    private byte[] read_bytes(int numBytes) throws IOException{
        int len=0 ;
        byte bStr[] = new byte[numBytes];
        int bytesread=0;
        do {
            bytesread= dis.read(bStr, len, numBytes-len);
            if (bytesread == -1)
                throw new IOException("Broken Pipe");
            len += bytesread;
        } while (len < numBytes);
        return bStr;
    }

    /* Llegir un string  mida variable size = nombre de bytes especifica la longitud*/
    public  String read_string_variable(int size) throws IOException
    {
        byte bHeader[]=new byte[size];
        char cHeader[]=new char[size];
        int numBytes=0;

        // Llegim els bytes que indiquen la mida de l'string
        bHeader = read_bytes(size);
        // La mida de l'string ve en format text, per tant creem un string i el parsejem
        for(int i=0;i<size;i++){
            cHeader[i]=(char)bHeader[i]; }
        numBytes=Integer.parseInt(new String(cHeader));

        // Llegim l'string
        byte bStr[]=new byte[numBytes];
        char cStr[]=new char[numBytes];
        bStr = read_bytes(numBytes);
        for(int i=0;i<numBytes;i++)
            cStr[i]=(char)bStr[i];
        return String.valueOf(cStr);
    }

    /* Escriure un string mida variable, size = nombre de bytes especifica la longitud  */
    /* String str = string a escriure.*/
    public  void write_string_variable(int size,String str) throws IOException
    {

        // Creem una seqüència amb la mida
        byte bHeader[]=new byte[size];
        String strHeader;
        int numBytes=0;

        // Creem la capçalera amb el nombre de bytes que codifiquen la mida
        numBytes=str.length();

        strHeader=String.valueOf(numBytes);
        int len;
        if ((len=strHeader.length()) < size)
            for (int i =len; i< size;i++){
                strHeader= "0"+strHeader;}
        for(int i=0;i<size;i++)
            bHeader[i]=(byte)strHeader.charAt(i);
        // Enviem la capçalera
        dos.write(bHeader, 0, size);
        // Enviem l'string writeBytes de DataOutputStrem no envia el byte més alt dels chars.
        dos.writeBytes(str);
    }

    public void writeTest() {
        try {
            write_string("Hello World I'm Jordi !");
        } catch (IOException ex) {
            Logger.getLogger(ComUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String readTest() {
        String a = "";
        try {
            a = read_string();
        } catch (IOException ex) {
            Logger.getLogger(ComUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public void closeAllIO_Streams() {
        try {
            dos.close();
            dis.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private void write_command3(String str) throws IOException {
        int numBytes, lenStr;
        byte bStr[] = new byte[3];

        lenStr = str.length();

        if (lenStr > 3)
            numBytes = 3;
        else
            numBytes = lenStr;

        for(int i = 0; i < numBytes; i++)
            bStr[i] = (byte) str.charAt(i);

        dos.write(bStr, 0, 3);
    }

    private void write_command2(String str) throws IOException {
        int numBytes, lenStr;
        byte bStr[] = new byte[2];

        lenStr = str.length();

        if (lenStr > 2)
            numBytes = 2;
        else
            numBytes = lenStr;

        for(int i = 0; i < numBytes; i++)
            bStr[i] = (byte) str.charAt(i);

        dos.write(bStr, 0, 2);
    }

    public void write_space() throws IOException {
        byte bStr[] = new byte[1];
        String str = " ";

        bStr[0] = (byte) str.charAt(0);

        dos.write(bStr, 0, 1);
    }

    public void write_char(String s) throws IOException {
        byte bStr[] = new byte[1];
        String str = s;

        bStr[0] = (byte) str.charAt(0);

        dos.write(bStr, 0, 1);
    }

    public void write_bet() throws IOException {
        write_command3("BET");
    }

    public void write_call() throws IOException {
        write_command3("CAL");
    }

    public void write_fold() throws IOException {
        write_command3("FLD");
    }

    public void write_check() throws IOException {
        write_command3("CHK");
    }

    public void write_show() throws IOException {
        write_command3("SHW");
    }

    public void write_userId() throws IOException {
        write_command3("UID");
    }

    public void write_win() throws IOException {
        write_command3("WIN");
    }

    public void write_turn() throws IOException {
        write_command3("TRN");
    }

    public void write_card() throws IOException {
        write_command3("CRD");
    }

    public void write_stack() throws IOException {
        write_command3("STK");
    }

    public void write_error() throws IOException {
        write_command3("ERR");
    }

    public void write_error_num(int n) {
        switch (n) {
            case 1:
                try {
                    write_command2("01");
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 2:
                try {
                    write_command2("02");
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 3:
                try {
                    write_command2("03");
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 4:
                try {
                    write_command2("04");
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 10:
                try {
                    write_command2("10");
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
        }
    }

    public void write_card_code(Card c) throws IOException {
        byte card[] = new byte[1];
        card[0] = (byte) c.getCode();
        dos.write(card,0,1);
    }

    public String read_command3() throws IOException {
        byte bStr[];
        char cStr[] = new char[3];

        bStr = read_bytes(3);
        for(int i = 0; i < 3; i++)
            cStr[i] = (char) bStr[i];

        return String.valueOf(cStr);
    }

    public String read_space() throws IOException {
        byte bStr[];
        char cStr[] = new char[1];
        bStr = read_bytes(1);
        cStr[0] = (char) bStr[0];
        return String.valueOf(cStr);
    }

    public String read_card() throws IOException {
        byte bStr[];
        char cStr[] = new char[1];
        bStr = read_bytes(1);
        cStr[0] = (char) bStr[0];
        return String.valueOf(cStr);
    }












}
