package tcp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Servidor {

    public static void main(String args[]) {
    	int porta = 12345;
        Socket socket = null;
        ServerSocket serverSocket = null;
        BufferedInputStream bufferedInputStream = null;
        DataInputStream dataInputStream = null;
        
    	boolean isPangram;
    	
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("Servidor ouvindo a porta " + porta + "...");
            
            socket = serverSocket.accept();

            bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            dataInputStream = new DataInputStream(bufferedInputStream);

            while (true) {
                try {
                    String mensagemCliente = dataInputStream.readUTF();
                    if (mensagemCliente.equals("tchau")) {
                        break;
                    }
                    
                    isPangram = isFrasePangram(mensagemCliente);
                    if(isPangram) {
                    	System.out.println("Servidor: Esta frase é um pangram ");
                    } else {
                    	System.out.println("Servidor: Esta frase não é um pangram ");
                    }
                    
                } catch (IOException e) {
                    break;
                }
            }
            dataInputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Erro: " + e);
        }
    }
    
    public static boolean isFrasePangram(String frasePangram) {
    	if (frasePangram == null) {
            return false;
        }
        Boolean[] alfabeto = new Boolean[26];
        Arrays.fill(alfabeto, false);
        int contador = 0;
        frasePangram = frasePangram.toUpperCase();
        for (int i = 0; i < frasePangram.length(); i++) {
            if ('A' <= frasePangram.charAt(i) && frasePangram.charAt(i) <= 'Z') {
                contador = frasePangram.charAt(i) - 'A';
                alfabeto[contador] = true;
            }
        }
        for (boolean index : alfabeto) {
            if (!index) {
                return false;
            }
        }
        return true;
    }
}