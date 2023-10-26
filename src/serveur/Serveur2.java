package serveur;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import client.Operation;
public class Serveur2 extends Thread{
	//activite 3.2
	private int nombreClient;
	public static void main(String[] args) {
	(new Serveur2()).start(); /* on a appeler le thread , la methode start() fait appel à la methode run() et on peut l'utiliser que lorsque
	la classe herite de classe Thread */	
	}
	public void run() {
		try {
			ServerSocket ss=new ServerSocket(500); //on a fait une reservation du port 
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
	// Création du thread
	public class ClientProcess extends Thread{
		private int numClient;
		private Socket s;
		public ClientProcess(Socket s, int numClient) {
			this.s=s;
			this.numClient=numClient;
		}
		public void run() {
			// Affiche un message au serveur, indiquant le numéro du client et son adresse IP
			System.out.println("le client num:"+ numClient + "de l'adresse IP:" +s.getRemoteSocketAddress());/*getRemoteSocketAddress() methode qui donne
			 l'@ IP de la machine connecter sur le serveur ,càd elle détecte l’adresse IP d’un client connecté */
			try {
				(new PrintWriter(s.getOutputStream(),true)).println("Bienvenu vous etes le client num"+numClient); /*PrintWriter() nous permet d'envoyer 
				la chaine de caractere caractere par caractere , le true indique que le PrintWriter sera automatiquement vidé
				 à chaque fois qu'une ligne de texte est écrite */
				//traitement
		          // les 2 methode inputstream() et outputstream() sont 2 methodes d'echange de données avec le client
		          InputStream is = s.getInputStream(); /*inputStram reçue les données deja sérialisées coté client 
		          elle prend le contenue de os du client*/ 
		          OutputStream os = s.getOutputStream(); // Pour envoyer la resultat au client
		          ObjectInputStream ois= new ObjectInputStream(is);//ObjectInputStream est utilisé pour désérialiser les données reçues 
		          //à partir de l'inputStream 
		          Operation op=(Operation)ois.readObject();//readObject() elle lu l'objet sérialisé à partir du flux d'entrée 'ois' et
		          // le renvoit de type object c pour cela on a utiliser le casting pour le convertir en type operation
		          int res;
		          switch(op.getOperation()) {
		          case '+': res=op.getOp1()+op.getOp2();break;
		          case '-': res=op.getOp1()-op.getOp2();break;
		          case '/': res=op.getOp1()/op.getOp2();break;
		          case '*': res=op.getOp1()*op.getOp2();break;
		          default : res=0;
		          }
		          ObjectOutputStream oos= new ObjectOutputStream(os);
		          Operation opp=new Operation(op.getOp1(),op.getOp2(),op.getOperation());
		          opp.setResult(res);
		          oos.writeObject(opp);//envoie au client un objet de type Operation
			} 
			catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();} 
		  }
	}
}
