package client;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
public class Client3 {
	//activite 3.2

	public static void main(String[] args)  {
		try{
			System.out.println("je suis un client");
		    Socket socket=new Socket("Localhost",500); // une socket va etre crée 
		    // localhost c'est la machine du client et 1234 c'est le port
		    //cette instruction est pour la demande de connexion de client au serveur 
		    System.out.println("je suis un client connecté");         
		    InputStream is = socket.getInputStream(); // elle lu le flux qui est dans le port 1234
		    OutputStream os = socket.getOutputStream(); // elle fait sortie le contenue de is et le renvoi au serveur
		    InputStreamReader isr= new InputStreamReader(is); //elle lu 4 octects 
	        BufferedReader br= new BufferedReader(isr); //Bufferedreader collecte toute la chaine
		    System.out.println(br.readLine());
		    ObjectOutputStream oos = new ObjectOutputStream(os); //un objet est crée
		    Scanner scanner=new Scanner(System.in);
		    int op1;
		    int op2;
		    String op;
		    System.out.println("donner le premier nombre:");
		    op1=scanner.nextInt();
		    System.out.println("donner le deuxieme nombre:");
		    op2=scanner.nextInt();
		    do {
		    	System.out.println("donner l'operation (+,-,*,/):");
		    	op=scanner.nextLine();// nextLine pour lire un une chaine de caractères ou un caractère		    
		    	}
		    while(!op.equals("+")&& !op.equals("-")&& !op.equals("*")&& !op.equals("/"));
		    Operation oop= new Operation(op1,op2,op.charAt(0));
		    oos.writeObject(oop); //envoyer le fichier au serveur, writeObject() fait la serialisation de l'objet 
	        ObjectInputStream ois= new ObjectInputStream(is);
	        Operation opp  =(Operation)ois.readObject();
	        System.out.println("le resultat de "+op1+" "+op+" "+op2+"est egale à"+opp.getResult() );
		    System.out.println("deconnexion client");
	        socket.close();
		}
		catch(Exception e) {e.printStackTrace();};
	}
}
