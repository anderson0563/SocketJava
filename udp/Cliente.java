package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			
			InetAddress inetAddress = InetAddress.getByName("localhost");
			int porta = 12345;
			
			@SuppressWarnings("resource")
			DatagramSocket datagramSocket = new DatagramSocket();

			while (true) {
				byte[] bufferEnvio = new byte[1024];
				byte[] bufferRetorno = new byte[1024];
				
				System.out.println("Cliente - Escreva uma frase: ");
				
				String mensagemCliente = scanner.nextLine();
				bufferEnvio = mensagemCliente.getBytes();
				
				DatagramPacket packet = new DatagramPacket(bufferEnvio, bufferEnvio.length, inetAddress, porta);
				datagramSocket.send(packet);

				DatagramPacket packet2 = new DatagramPacket(bufferRetorno, bufferRetorno.length);
				datagramSocket.receive(packet2);
				String mensagemServidor = new String(packet2.getData());
				System.out.println(mensagemServidor);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
