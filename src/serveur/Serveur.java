package serveur;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Serveur extends Thread{
	//activite 3.1
	private int nombreClient;
	public static void main(String[] args) {
	(new Serveur()).start(); /* on a appeler le thread , la methode start() fait appel à la methode run() et on peut l'utiliser que lorsque
	la classe herite de classe Thread */	
	}
	public void run() {
		try {
			ServerSocket ss=new ServerSocket(1234); //on a fait une reservation du port 
			System.out.println("demarrage du serveur");
			while(true){//tand que le serveur fonctionne 
				Socket s=ss.accept();// on a accepter la connexion du client
				//on doit maintenant creer un thread pour chaque client connecté au serveur
				new ClientProcess(s,++nombreClient).start(); /*on a creer un thread qui prend dans ces param une socket et 
				++numClient pour faire l'incrementation à chaque client connecté*/
			}
		} catch (IOException e) 
		{e.printStackTrace();}
	}
	public class ClientProcess extends Thread{
		private int numClient;
		private Socket s;
		public ClientProcess(Socket s, int numClient) {
			this.s=s;
			this.numClient=numClient;
		}
		public void run() {
			System.out.println("le client num:"+ numClient + "de l'adresse IP:" +s.getRemoteSocketAddress());/*getRemoteSocketAddress() methode qui donne
			 l'@ IP de la machine connecter sur le serveur ,càd elle détecte l’adresse IP d’un client connecté */
			try {
				(new PrintWriter(s.getOutputStream(),true)).println("Bienvenu vous etes le client num"+numClient); /*PrintWriter() nous permet d'envoyer 
				la chaine de caractere caractere par caractere , le true indique que le PrintWriter sera automatiquement vidé
				 à chaque fois qu'une ligne de texte est écrite */
			} catch (IOException e) {
				e.printStackTrace();}
			
		}
	}
}
