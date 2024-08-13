package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Scanner;

public class Servidor {

	public static void main(String[] args) throws SocketException, IOException {
		@SuppressWarnings("resource")
		DatagramSocket socket = new DatagramSocket(12345);

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		boolean isPangram = false;

		while (true) {
			byte[] bufferEnvio = new byte[1024];
			byte[] bufferRetorno = new byte[1024];

			DatagramPacket packet = new DatagramPacket(bufferRetorno, bufferRetorno.length);
			socket.receive(packet);

			InetAddress endereco = packet.getAddress();
			int porta = packet.getPort();

			String mensagemCliente = new String(packet.getData());
			//System.out.println("Cliente: " + mensagemCliente);

			isPangram = isFrasePangram(mensagemCliente);

			if (isPangram) {
				System.out.println("Servidor: Esta frase é um pangram ");
			} else {
				System.out.println("Servidor: Esta frase não é pangram ");
			}

			String mensagemServidor = scanner.nextLine();
			bufferEnvio = mensagemServidor.getBytes();

			DatagramPacket packet2 = new DatagramPacket(bufferEnvio, bufferEnvio.length, endereco, porta);
			socket.send(packet2);
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
