import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;



public class ClientMT extends Thread {
	private PrintWriter pw;
	private BufferedReader br;
	
	
	
	public ClientMT() {
		try {
			Socket s= new Socket("localhost",2000);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw =new PrintWriter(s.getOutputStream(),true);
			this.start();
			Scanner clavier = new Scanner(System.in);
			while(true) {
				System.out.println("Donner votre Requete ");
			    String req = clavier.nextLine();
			    pw.println(req);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				String rep=br.readLine();
				System.out.println(rep);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		new ClientMT().start();
	}

}
