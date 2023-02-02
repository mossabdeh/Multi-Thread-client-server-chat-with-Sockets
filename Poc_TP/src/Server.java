import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(2000);
			System.out.println("Attend connexion client");
			Socket s =ss.accept();
			InputStream is =s.getInputStream();
			OutputStream os = s.getOutputStream();
			
			System.out.println("Attend un nomber");
			int nb =is.read();
			int rep=nb*8;
			
			System.out.println("envoi reponse");
			os.write(rep);
			s.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
