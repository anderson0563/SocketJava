package tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String args[]) {
    	Socket socket = null;
    	DataOutputStream dataOutputStream = null;
    	
    	String nome = "localhost";
        int porta = 12345;
        
        try {
            socket = new Socket(nome, porta);
            System.out.println("O cliente está conectado ao servidor " + socket.getRemoteSocketAddress());
                        
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            
            @SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
            while (true) {
                try {
                    System.out.print("Cliente - Escreva uma frase: ");
                    String mensagem = scanner.nextLine();
                    if(mensagem.equals("tchau")){
                        break;
                    }
                    dataOutputStream.writeUTF(mensagem);
                    dataOutputStream.flush();
                } catch (IOException e) {
                    break;
                }
            }
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}