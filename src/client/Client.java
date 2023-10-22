package client;

import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			System.out.println("Je suis un client");
			Socket socket=new Socket("localhost",1234);
			System.out.println("je suis un client connecté");
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
     
	}

}
