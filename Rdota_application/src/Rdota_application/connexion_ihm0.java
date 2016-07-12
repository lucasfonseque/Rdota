package Rdota_application ;

import java.awt.BorderLayout;
import java.awt.EventQueue;
	import java.awt.EventQueue;
	import java.awt.Image;
	import javax.swing.JFrame;
	import javax.swing.ImageIcon;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import javax.swing.JButton;
	import java.awt.Color;
	import javax.swing.JLabel;
	import javax.swing.JTextField;
	import java.awt.Font;
	import javax.swing.JOptionPane;
	import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.Statement;
import java.util.Calendar;
import java.io.*;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
	import javax.swing.JCheckBox;
	import javax.swing.JPasswordField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class connexion_ihm0 extends JFrame {

	private JTextField textid;
	private JPasswordField testmdp;
	
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() 
				{
						
					try {
						  connexion_ihm0 frame = new connexion_ihm0();
						  frame.setVisible(true);
						      
						  Connection conn =null; 
						  Class.forName("org.postgresql.Driver"); 
						  conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Rdota", "postgres", "LFS31"); 
						  //Statement st = conn.createStatement(); 
						  System.out.println("Connexion effective !");
				
						}
					catch (Exception e) 
					{
						JOptionPane.showMessageDialog(null, "Pas de connexion à la base de donnée Rdota" );
						e.printStackTrace();
					}	
				}
			});
		}

		/**
		 * Create the frame.
		 */
		public connexion_ihm0() {
			JPanel contentPane;

			
			setTitle("Connexion Rives Dicostanzo");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 725, 451);
			contentPane = new JPanel();
			contentPane.setForeground(new Color(47, 79, 79));
			contentPane.setBackground(new Color(176, 196, 222));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JButton btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try
					{
					String sTest=textid.getText();
					String pTest=testmdp.getText();
					if (((sTest.contains("admin")) && (pTest.contains("admin")))&&((sTest.contains("")) && (pTest.contains("")))||((sTest.contains("LFONSEQUE")) && (pTest.contains("LFS31"))))
					{
					menu_Rdota frame = new menu_Rdota();
					frame.setVisible(true);
					}
					else 
					{
				            JOptionPane.showMessageDialog(null, " Identifiants invalides ! " );
				            
				    }
					       
					}catch(Exception e){
					    JOptionPane.showMessageDialog(null, "Connexion échec");
					}
					
////////////////////////////////////LOG ///////////////////////////////////////////
					
					Calendar cal = new GregorianCalendar();
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH);
					month++; // car le programme compte le 0
					int year = cal.get(Calendar.YEAR);
					
					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR);
					
					String tempo = "  Time :  "+hour+":"+minute+":"+second+"  -  Date :  "+day+"/"+month+"/"+year ;
					
					
					String fileName1="log.txt";
					//System.out.println(""+fileName1+"");
				try{
				PrintWriter outputStream = new PrintWriter(fileName1);
			
				//outputStream = new PrintWriter(fileName1);
				System.out.println("Done.");
				outputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

                String exp = "C:\\Program Files\\PostgreSQL\\9.3\\data\\"+fileName1+"";
                exp = exp.replace("\\", "/");
                       
                	//File fichier = new File (exp);
                	File fichier=new File("C:\\Program Files\\PostgreSQL\\9.3\\data\\log.txt"); // définir l'arborescence
					//fichier.createNewFile();
					//FileWriter fichier_ecrire=new FileWriter(fichier);
					//fichier_ecrire.write("****** ");  // écrire une ligne dans le fichier resultat.txt
					//fichier_ecrire.write("\n"); // forcer le passage à la ligne
					/*ffw.close(); // fermer le fichier à la fin des traitements*/
                	try {
                        // Creation du fichier
                        //fichier .createNewFile();
                        // creation d'un writer (un écrivain)
                        final FileWriter writer = new FileWriter(fichier, true);
                        try {
                        	String sTest=textid.getText();
                            //writer.write("ceci est un texte\n");
                            writer.write("Connexion utilisateur : \n"+sTest+" \n");
                            writer.write ("\r\n");
                            writer.write(" "+tempo+" \n");
                            writer.write ("\r\n");
                           
                        } finally {
                            // quoiqu'il arrive, on ferme le fichier
                            writer.close();
                        }
                    } catch (Exception e) {
                        System.out.println("Impossible de creer le fichier");
                    }
				}
			});
			btnLogin.setBackground(Color.CYAN);
			btnLogin.setBounds(501, 324, 122, 31);
			contentPane.add(btnLogin);
			
			JCheckBox clik_enregistre = new JCheckBox("Se souvenir des identifiants");
			clik_enregistre.setBounds(497, 283, 206, 23);
			contentPane.add(clik_enregistre);
			
			JLabel lblConnexionRdot = new JLabel("Connexion \u00E0 Rdota ");
			lblConnexionRdot.setForeground(new Color(0, 0, 255));
			lblConnexionRdot.setFont(new Font("Arial", Font.BOLD, 30));
			lblConnexionRdot.setBounds(350, 59, 307, 51);
			contentPane.add(lblConnexionRdot);
			
			testmdp = new JPasswordField();
			testmdp.setBounds(501, 211, 122, 31);
			contentPane.add(testmdp);
			
			textid = new JTextField();
			textid.setBounds(501, 139, 122, 31);
			contentPane.add(textid);
			textid.setColumns(10);
			
			JLabel label = new JLabel("");
			label.setBounds(26, 59, 46, 14);
			contentPane.add(label);
			
			JLabel label_1 = new JLabel("");
			label_1.setIcon(new ImageIcon("C:\\Users\\LFONSEQUE\\Pictures\\1434474203.jpeg.png"));
			label_1.setBounds(36, 59, 250, 259);
			contentPane.add(label_1);
			
			JButton btnIdentifiant = new JButton("Identifiant");
			btnIdentifiant.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnIdentifiant.setBounds(296, 139, 173, 31);
			contentPane.add(btnIdentifiant);
			
			JButton btnMotDePasse = new JButton("Mot de passe");
			btnMotDePasse.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnMotDePasse.setBounds(296, 211, 173, 31);
			contentPane.add(btnMotDePasse);
			
		

		}	
}
