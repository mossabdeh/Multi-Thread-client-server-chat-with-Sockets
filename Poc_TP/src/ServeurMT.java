import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServeurMT extends Thread {
	int nbClients;
	private List<Socket> clientsConnectés=new ArrayList<Socket>();
	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(2000);
			while(true) {
				// serveur chaque moment accepter les socket
				Socket s = ss.accept();
				// client ajoute dans la list
				clientsConnectés.add(s);
				// kol ma tasra cnx ++ clients
				++nbClients;
				new Conversation(s,nbClients).start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
 class Conversation extends Thread {
	 private Socket socket;
	 private int numeroClient;
	 
	 public Conversation(Socket socket , int num) {
		super();
		this.socket = socket;
		this.numeroClient =num;
	}
	 
	 // methode pour difuser les message bin les clients
	 public void broadCast(String message) {
		 for(Socket s:clientsConnectés) {
			 try {
				PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
				pw.println(message);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 }
		 
	 }

	@Override
		public void run() {
		 // communiquer aver ChCar
		try {
			// lire brk
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			// envoyer ecrire brk
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os,true);
			// hadi tjib adresse IP ta3 client
			String IP = socket.getRemoteSocketAddress().toString();
			System.out.println("Connexion du client numero "+numeroClient+" IP = "+IP);
			// hadi tb3at ll client 
			pw.println("Bienveune vous etes le client numero = " +numeroClient);
			
			// hadi chat(conversation)
			while(true) {
				String req;
				while((req=br.readLine()) != null) {
					System.out.println(IP+" a envoyée " +req);
					broadCast(req);
				}
				
				
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
	 
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    new ServeurMT().start();
	}

}
