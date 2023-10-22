package client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client2 {
	public static void main(String[] args) {
		try {
			System.out.println("Je suis un client");
			InetAddress inetAddress = InetAddress.getByName("192.168.1.137");//cherche moi la machine qui est une @ip 192.168.100.13
			InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress,1234); //inetSocketAdd lien virtuelle entre les 2 machines
			Socket socket=new Socket();//socket va etre cree qui signifie un transporteur de données
			socket.connect(inetSocketAddress);//socket va connecter sur le chemin des 2 machines 
			System.out.println("je suis un client connecté");
			//traitement 
			InputStream is = socket.getInputStream(); // Pour lire le flux qui est dans le port 1234
			 InputStreamReader isr= new InputStreamReader(is); //InputStreamReader Lire la chaine caractère par caractère
			 BufferedReader br = new BufferedReader(isr); //Bufferedreader collecte toute la chaine
			 System.out.println(br.readLine());
			 // deconnexion du client
			 System.out.println("deconnexion client");
			socket.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
     
	}

}
