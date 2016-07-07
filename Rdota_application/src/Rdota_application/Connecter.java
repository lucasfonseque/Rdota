package Rdota_application;


import java.sql.*;


public class Connecter {

	
	Connection conn;
	
	public Connecter (){
		
		try {
			
			  Class.forName("org.postgresql.Driver"); 
		
			}catch (ClassNotFoundException e) 
			{
				System.err.println(e);
			}
		try {
			
			 conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Rdota", "postgres", "LFS31"); 
		
			}catch (SQLException e){System.err.println(e);}
			}
		Connection obtenirconnexion(){return conn;}
	
}
