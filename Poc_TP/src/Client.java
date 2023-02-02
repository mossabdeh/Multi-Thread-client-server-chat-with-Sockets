import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {
			Socket s=new Socket("localhost",2000);
			InputStream is =s.getInputStream();
			OutputStream os = s.getOutputStream();
			
			Scanner scanner = new Scanner(System.in);
			System.out.print("Donner un nomber : ");
			int nb=scanner.nextInt();
			os.write(nb);
			int rep=is.read();
			System.out.println("Resultat = "+rep);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
