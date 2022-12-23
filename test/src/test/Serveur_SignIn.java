package test;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Serveur_SignIn {

	public static void main(String[] args) {
		try{  
			ServerSocket serverSocket = new ServerSocket(402);
			System.out.print("le serveur attend la connexion \n");
			
			while(true) {
				Class.forName("com.mysql.cj.jdbc.Driver");
			    
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db","root","");
				
				Socket socket = serverSocket.accept();
				System.out.println("le client est connecté!");
				
				InputStream sr = socket.getInputStream();
				InputStreamReader r = new InputStreamReader(sr);
				BufferedReader br = new BufferedReader(r);
				
				String[] s = br.readLine().split(",");
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("select * from users3"); 
				
				OutputStream os = socket.getOutputStream();
                OutputStreamWriter osr = new OutputStreamWriter(os);
                PrintWriter pw =new PrintWriter(osr,true);

                
                String res = "rien";
				
				while(rs.next()) {
					if(s[0].equals(rs.getString(1)) && s[1].equals(rs.getString(3))) {
						res = ""+rs.getString(4);
						pw.println(res);
						break;
					}
				}
				
				/*if(!rs.next()) {
					res = "User does not exist";
					pw.println(res);
					System.out.println("Resultat :" + res);
				}*/
				
				con.close();  
				socket.close();
			}
			
	   }catch(Exception e){System.out.println(e);} 

	}
}
