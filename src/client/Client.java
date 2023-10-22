package client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			System.out.println("Je suis un client");
			Socket socket=new Socket("localhost",1234); // une socket va etre crée 
		    // localhost c'est la machine du client et 1234 c'est le port
		    //cette instruction est pour la demande de connexion de client au serveur
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
