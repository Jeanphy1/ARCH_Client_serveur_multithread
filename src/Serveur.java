import java.io.IOException;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe correspondant à un serveur TCP.
 * Le client envoie la chaine 'Bonjour' et lit une reponse de la part du serveur.
 * Le client envoie ensuite la chaine 'Au revoir' et lit une reponse.
 * Le numero de port du serveur est specifie dans la classe ServeurTCP.
 * @author Cyril Rabat
 * @version 07/10/2013
 */

public class Serveur {

	 public static void main(String[] zero){
		  
		  ServerSocket  socketserver;
		  Socket socket;
		  try {
			  socketserver = new ServerSocket(2018);
		
		    
		    int nbrclient = 1;
		    
		     while(true){
		    	 System.out.println("En attente de client");
		     socket = socketserver.accept(); // Un client se connecte on l'accepte
		     System.out.println("Le client numéro "+nbrclient+ " est connecté !"); 
		     Thread t = new Thread(new  GestionClient(socket,nbrclient));
		     t.start();
		     nbrclient++;
		      }
		         
		   } catch (IOException e) {
		    e.printStackTrace();
		         }
		  
		}
	 
	static class GestionClient implements Runnable {
		  private boolean fin=false ; 
		  private BufferedReader in;
		  private PrintWriter out;
		 private Socket socketclient;
		 private int  idclient=0;
		    
		    
		    
		  public GestionClient(Socket s,int idclient){
		   socketclient = s;
		   this.idclient=idclient;
		  }
		  
		  public void run() {
			  
		         try
		         {
		        	 
		        	 out = new PrintWriter(socketclient.getOutputStream());
		        	 
		             out.println("Vous êtes connecté au serveur client "+idclient+"avec IP: "+socketclient.getInetAddress()+" port:"+socketclient.getPort());
		             out.flush();
		             in = new BufferedReader (new InputStreamReader (socketclient.getInputStream()));
		             String message_distant;
		             while(!fin)
		             {
		            	
		            	 try
		            	 {
		            		 if (!(message_distant = in.readLine()).equals("0x23"))
		            		 {
	            			 	out.println(message_distant.toUpperCase());
					            out.flush();
		            		 }
		            		 else
		            		 {
		            			 out.println("AU REVOIR");
						         out.flush();
						         fin=true;
		            		 }
		            		   
					        	   
		            	 }catch (IOException ess)
		            	 {
		            		 ess.printStackTrace();
		            	 }
			                      
		             }
		             
		         }catch (Exception e)
		         {
		        	 e.printStackTrace();
		        	 try
		        	 {
		        		 socketclient.close();
		        	 }catch (Exception es)
		        	 {
		        		 es.printStackTrace();
		        	 }
		        	 
		         }
		  }

	
		   
	 }

}
