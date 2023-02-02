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

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ServeurChat extends Application{
	int nbClients;
	private List<Socket> clientsConnectés=new ArrayList<Socket>();
	PrintWriter pw;
	//---------------
	ObservableList<String> listModel = FXCollections.observableArrayList();
	
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Serveur Application");
		BorderPane borderPane = new BorderPane();
		
		Button buttonDemarer = new Button("Demarer");
		Label labelNb = new Label("Numbre : ");
		TextField textFieldNumber =new TextField("Number Client");
		Label labelPort = new Label("Port : ");
		TextField textFieldPort =new TextField("2000");
		Button buttonArreter = new Button("Arreter");
		
		HBox hbox= new HBox();hbox.setSpacing(10);hbox.setPadding(new Insets(10));
		hbox.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
		hbox.getChildren().addAll(buttonDemarer,labelNb,textFieldNumber,labelPort,textFieldPort,buttonArreter);
		
		borderPane.setTop(hbox);
		VBox vbox =new VBox();vbox.setSpacing(10);vbox.setPadding(new Insets(10));
        // list ta3 javaFX
        
		ListView<String> listView= new ListView<String>(listModel);
		vbox.getChildren().addAll(listView);
		borderPane.setCenter(vbox);
		
		
		
		
	
		Scene scene = new Scene(borderPane,700,400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		buttonDemarer.setOnAction((evt)->{
			
			
			new Thread(()->{
				System.out.println("Attendre une connexion");
				
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
						Conversation.add(listView);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}).start();
			
			
		});
	}
	// ------------------------------ Coverstaoin--------------------
	class Conversation extends Thread {
		 private Socket socket;
		 private int numeroClient;
		 
		 public Conversation(Socket socket , int num) {
			super();
			this.socket = socket;
			this.numeroClient =num;
		}
		 
		 public static void add(ListView<String> listView) {
			// TODO Auto-generated method stub
			
		}

		// methode pour difuser les message bin les clients
		 public void broadCast(String message) {
			 for(Socket s:clientsConnectés) {
				 try {
					PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
					pw.println(message);
					listModel.add(message);
					
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

}
