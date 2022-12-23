package test;

import java.io.*;
import java.net.Socket;
import java.sql.*;

import java.net.ServerSocket;

import java.net.Socket;

public class Serveur_SignUp {
	public static void main(String[] args) {
		try{  
			ServerSocket serverSocket = new ServerSocket(401);
			System.out.print("le serveur attend la connexion \n");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		    
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db","root",""); 
			
			Socket socket = serverSocket.accept();
			System.out.println("le client est connecté!");
			
			Statement stmt = con.createStatement();
		    
			InputStream sr = socket.getInputStream();
			InputStreamReader r = new InputStreamReader(sr);
			BufferedReader br = new BufferedReader(r);
			
			String[] s = br.readLine().split(",");

			String sql = "INSERT INTO users3 VALUES (?,?,?,?)";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, s[0]);
			statement.setString(2, s[1]);
			statement.setString(3, s[2]);
			statement.setString(4, s[3]);
			statement.executeUpdate();
			
			System.out.println("Insert Successfull !!");
			
			con.close();  
			socket.close();
	   
	   }catch(Exception e){System.out.println(e);} 
	}
}

