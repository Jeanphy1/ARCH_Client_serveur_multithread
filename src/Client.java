import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe correspondant à un serveur TCP.
 * Le client envoie la chaine 'Bonjour' et lit une reponse de la part du serveur.
 * Le client envoie ensuite la chaine 'Au revoir' et lit une reponse.
 * Le numero de port du serveur est specifie dans la classe ServeurTCP.
 * @author Cyril Rabat
 * @version 07/10/2013
 */

public class Client {

	 public static void main(String[] zero){
		  
		  
		  boolean fin=false;
		  Socket socket = null;
		  BufferedReader in;
		  PrintWriter out;
		  
		  try {
			  Scanner sc = new Scanner(System.in);
			  socket = new Socket(InetAddress.getLocalHost(),2018); 
	          System.out.println("Demande de connexion");
	          in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	          String message_distant = in.readLine();
	          System.out.println(message_distant);
	          System.out.println("Entrez votre message");
	          out = new PrintWriter(socket.getOutputStream());
	        
			  while(!fin)
			  {
				 
				 String message=sc.nextLine();
				 out.println(message);
				 out.flush();
				
				 if (message.equals("0x23"))
				 {
					 fin=true;
				 }
				 
				 message_distant = in.readLine();
				 System.out.println("SERVEUR: "+message_distant+"\n");
					 
			  }
		   		         
		  }catch (Exception e) {
			  	  
		   e.printStackTrace();
		   try {
			socket.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		 }
	 }
}
