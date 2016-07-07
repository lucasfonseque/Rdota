package Rdota_application;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JMonthChooser;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDateChooser;

public class menu_Rdota extends JFrame {



	private JLabel lblidchauffeur;
	private JMenuBar menuBar;  // ------------------------------------------------------------à voir
	private JTabbedPane tabbedPane;// --------------------------------------------------------à voir
	private JPanel panel_addchaufeur;// ------------------------------------------------------à voir
	private JPanel panel_affichertabs; //---------------------------------------------------- à voir 
	private JPanel panel_addsociete;
	private JPanel contentPane;
	private JPanel panel_control_hoair;
	private JTextField textidChauffeur;
	private JTextField textField_nomchauffeur_control_horaire;
	private JTextField textchauf;//---------------------------------------------------- à voir 
	private JTextField textnum;//---------------------------------------------------- à voir 
	private JTextField textprenom;//---------------------------------------------------- à voir 
	private JTextField textsociete;	//---------------------------------------------------- à voir 
	private JTable table_4;
	private JTable table_3;
	private JTable table_2;
	private JTable table_1;
	private JTable table;
	private Statement stm2;
	private Statement stm3;
	private Statement stm4;		
	private Statement stm;
	private Statement stm1;
	private DefaultTableModel model4 = new DefaultTableModel();
	private DefaultTableModel model = new DefaultTableModel();
	private DefaultTableModel model2 = new DefaultTableModel();
	private DefaultTableModel model1 = new DefaultTableModel();
	private DefaultTableModel model3 = new DefaultTableModel();
	private Connecter conn1=new Connecter();
	private Connecter conn2=new Connecter();
	private Connecter conn3=new Connecter();
	private Connecter conn4=new Connecter();
	private Connecter conn5=new Connecter();
	private String date_debut, date_fin;
	private String nom_chauffeur;
	
		/**
		 * Lancement de l'application.
		 */
	
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					
					menu_Rdota base = new menu_Rdota ();
					base.setVisible(true);
					
				}});
		}

		public void presentation_ihm()
		{
			//Déclarations
			JMenu mnNew;
			JMenu mnFile;
			JMenuItem mntmExit;
			JSeparator separator;
			
			
			//Programme
			setTitle("Menu Rdota");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 970, 786);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblQueSouahitezVous = new JLabel("Que souhaitez vous faire ? ");
			lblQueSouahitezVous.setBounds(242, 52, 440, 53);
			lblQueSouahitezVous.setFont(new Font("Tahoma", Font.BOLD, 30));
			contentPane.add(lblQueSouahitezVous);
			
			JLabel label_Rives = new JLabel("");
			label_Rives.setBounds(10, 24, 188, 115);
			label_Rives.setIcon(new ImageIcon("C:\\Users\\LFONSEQUE\\Pictures\\logo.png"));
			contentPane.add(label_Rives);
			
			menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 954, 21);
			contentPane.add(menuBar);
			
			mnFile = new JMenu("File");
			mnFile.setBackground(new Color(0, 0, 0));
			menuBar.add(mnFile);
			
			//mnNew = new JMenu("New");
			//mnFile.add(mnNew);
			
			separator = new JSeparator();
			mnFile.add(separator);
			
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(JFrame.EXIT_ON_CLOSE);
					
				}
			});
			mnFile.add(mntmExit);
			
			JLabel label_Note = new JLabel("");
			label_Note.setBounds(734, 24, 140, 128);
			contentPane.add(label_Note);
			label_Note.setIcon(new ImageIcon("C:\\Users\\LFONSEQUE\\Pictures\\contract.png"));
			
			
		}
		
		public void clock ()
		{
			//Déclaration
			JLabel lblClock;
					
			//Programme
			lblClock = new JLabel("clock");
			lblClock.setEnabled(false);
			menuBar.add(lblClock);
			lblClock.setFont(new Font("Tahoma", Font.BOLD, 14));
			
			Thread clock=new Thread()
			{
				public void run()
				{
					try {
						for(;;){
						Calendar cal = new GregorianCalendar();
						int day = cal.get(Calendar.DAY_OF_MONTH);
						int month = cal.get(Calendar.MONTH);
						month++; // car le programme compte le 0
						int year = cal.get(Calendar.YEAR);
						
						int second = cal.get(Calendar.SECOND);
						int minute = cal.get(Calendar.MINUTE);
						int hour = cal.get(Calendar.HOUR);
						
						lblClock.setText("  Time :  "+hour+":"+minute+":"+second+"  -  Date :  "+day+"/"+month+"/"+year);
					
						sleep(1000);
							}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			};
			
			clock.start();
			
			}
		
		public void refreshtab_chauffeur()
		{
     		JScrollPane tableau_chauffeur_scrollPane = new JScrollPane();
			tableau_chauffeur_scrollPane.setBounds(85, 183, 709, 285);
			panel_addchaufeur.add(tableau_chauffeur_scrollPane);
			
//////////////////////////////////////////////TABLEAU Chauffeur ////////////////////////////////////////////////////////////////////////
			table = new JTable();
			tableau_chauffeur_scrollPane.setViewportView(table);	
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						int i =table.getSelectedRow();
					}catch(Exception e){System.err.println(e);
					JOptionPane.showMessageDialog(null, "erreur de deplacement"+e.getLocalizedMessage());}
				}
			});
			table.setModel(model);			
			
			int nio = model.getRowCount();
			
			for(int i=nio-1;i>=0;i--)
				model.removeRow(i);
			
			
			try {
			  
			  stm=conn1.obtenirconnexion().createStatement(); 
			  ResultSet Rs = stm.executeQuery("SELECT * FROM tchauffeur");
			  
				     while(Rs.next())
				     {
				     
					 model.addRow(new Object[]{Rs.getString("nom"),Rs.getString("prenom"),Rs.getString("num"),Rs.getString("société"),Rs.getString("idChauffeur") });	 

				     }
			}catch (Exception e) 
			{
				System.err.println(e);
			}
			
		}
		
		public void refreshtab_horaire()
		{
			
			JScrollPane scrollPane_tabhoraire_control = new JScrollPane();
			scrollPane_tabhoraire_control.setBounds(38, 227, 812, 262);
			panel_control_hoair.add(scrollPane_tabhoraire_control);
			
////////////////////////////////////////////// TABLEAU HORAIRE ////////////////////////////////////////////////////////////////////////
			table_4 = new JTable();
			scrollPane_tabhoraire_control.setViewportView(table_4);
			scrollPane_tabhoraire_control.setViewportView(table_4);
			scrollPane_tabhoraire_control.setViewportView(table_4);
			table_4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						int i =table_4.getSelectedRow();
					}catch(Exception e){System.err.println(e);
					JOptionPane.showMessageDialog(null, "erreur de deplacement"+e.getLocalizedMessage());}
				
					
				}
			});
			table_4.setModel(model4);
	
			int n = model4.getRowCount();
			
			for(int i=n-1;i>=0;i--)
				model4.removeRow(i);

			
					try {
						   stm4=conn5.obtenirconnexion().createStatement(); 
					  ResultSet Rs4 = stm4.executeQuery("SELECT * FROM thoraires WHERE date >= '"+date_debut+"' AND date <= '"+date_fin+"' AND chauffeur = '"+nom_chauffeur+"' ");
					  
						     while(Rs4.next())
						     {
						     
							 model4.addRow(new Object[]{Rs4.getString("chauffeur"),Rs4.getString("date"),Rs4.getString("connexion"),Rs4.getString("deconnexion"),Rs4.getString("idHoraires")});	 
		
						     }
						}catch (Exception e) 
						{
						System.err.println(e);
						}
			}
			
		public void add_chauffeur()
		{
						 
			// Programme 
			 
			panel_addchaufeur = new JPanel();
			tabbedPane.addTab("Add Chauffeur", null, panel_addchaufeur, null);
			panel_addchaufeur.setLayout(null);
			
			JLabel label_nomchauf_addchauff = new JLabel("Nom du chauffeur  :");
			label_nomchauf_addchauff.setFont(new Font("Tahoma", Font.BOLD, 14));
			label_nomchauf_addchauff.setBounds(59, 0, 150, 33);
			panel_addchaufeur.add(label_nomchauf_addchauff);
			
			textchauf = new JTextField();
			textchauf.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textchauf.setColumns(10);
			textchauf.setBounds(59, 44, 140, 40);
			panel_addchaufeur.add(textchauf);
			
			JLabel label_num_addchauf = new JLabel("Num\u00E9ro :");
			label_num_addchauf.setFont(new Font("Tahoma", Font.BOLD, 14));
			label_num_addchauf.setBounds(368, 0, 107, 33);
			panel_addchaufeur.add(label_num_addchauf);
			
			textnum = new JTextField();
			textnum.setColumns(10);
			textnum.setBounds(368, 44, 107, 40);
			panel_addchaufeur.add(textnum);
			
			textprenom = new JTextField();
			textprenom.setColumns(10);
			textprenom.setBounds(228, 44, 107, 40);
			panel_addchaufeur.add(textprenom);
			
			JLabel label_prenom_addchauf = new JLabel("Prenom :");
			label_prenom_addchauf.setFont(new Font("Tahoma", Font.BOLD, 14));
			label_prenom_addchauf.setBounds(228, 0, 133, 33);
			panel_addchaufeur.add(label_prenom_addchauf);
			
			textsociete = new JTextField();
			textsociete.setColumns(10);
			textsociete.setBounds(506, 44, 107, 40);
			panel_addchaufeur.add(textsociete);
			
			JLabel label_societe_addchauf = new JLabel("Soci\u00E9t\u00E9 :");
			label_societe_addchauf.setFont(new Font("Tahoma", Font.BOLD, 14));
			label_societe_addchauf.setBounds(506, 0, 107, 33);
			panel_addchaufeur.add(label_societe_addchauf);
			
			lblidchauffeur = new JLabel("idChauffeur :");
			lblidchauffeur.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblidchauffeur.setBounds(680, 0, 107, 33);
			panel_addchaufeur.add(lblidchauffeur);
			
			textidChauffeur = new JTextField();
			textidChauffeur.setBounds(680, 44, 121, 40);
			panel_addchaufeur.add(textidChauffeur);
			textidChauffeur.setColumns(10);
			
			JScrollPane tableau_chauffeur_scrollPane = new JScrollPane();
			tableau_chauffeur_scrollPane.setBounds(85, 183, 709, 285);
			panel_addchaufeur.add(tableau_chauffeur_scrollPane);
			
			
			///////////////////////////////// tableau chauffeur  /////////////////////////////////////////////
			
			table = new JTable();
			tableau_chauffeur_scrollPane.setViewportView(table);
			tableau_chauffeur_scrollPane.setViewportView(table);
			tableau_chauffeur_scrollPane.setViewportView(table);	
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						int i =table.getSelectedRow();
					}catch(Exception e){System.err.println(e);
					JOptionPane.showMessageDialog(null, "erreur de deplacement"+e.getLocalizedMessage());}
				}
			});
			table.setModel(model);
			
			model.addColumn("nom");
			model.addColumn("prenom");
			model.addColumn("num");
			model.addColumn("société");
			model.addColumn("idChauffeur");
			
			try {
			  
			  stm=conn1.obtenirconnexion().createStatement(); 
			  ResultSet Rs = stm.executeQuery("SELECT * FROM tchauffeur");
			  
				     while(Rs.next())
				     {
				     
					 model.addRow(new Object[]{Rs.getString("nom"),Rs.getString("prenom"),Rs.getString("num"),Rs.getString("société"),Rs.getString("idChauffeur") });	 

				     }
			}catch (Exception e) 
			{
				System.err.println(e);
			}
		}

		public void ajouter_chauffeur_addchauffeur()	// boutton valide
		{

			
			//JTextField textchauf=null;//---------------------------------------------------- à voir 
			//JTextField textnum=null;//---------------------------------------------------- à voir 
			//JTextField textprenom=null;//---------------------------------------------------- à voir 
			//JTextField textsociete=null;
			
			JButton buttonValid = new JButton("");
			buttonValid.setIcon(new ImageIcon("C:\\Users\\LFONSEQUE\\Pictures\\ok_button.png"));
			buttonValid.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
			try {
				
				Connection conn1 =null; 
				  Class.forName("org.postgresql.Driver"); 
				  conn1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Rdota", "postgres", "LFS31"); 
				  Statement st = conn1.createStatement(); 
				  String sTest=textchauf.getText();
				  String sTest_1=textnum.getText(); 
				  String sTest_2=textprenom.getText(); 
				  String sTest_3=textsociete.getText(); 
				//if ( textchauf.getText() != null && textnum.getText() !=null && textprenom.getText()!=null && textsociete.getText()!=null )
				  //{
				  String sql = ("INSERT INTO tchauffeur VALUES ('"+sTest+"','"+sTest_2+"', '"+sTest_1+"', '"+sTest_3+"');");
				  st.executeUpdate(sql);
				  System.out.println("Chauffeur Ajouté!");
				  JOptionPane.showMessageDialog(null, "Succés! Chauffeur ajouté" );
				 // }
				//else   JOptionPane.showMessageDialog(null, "Erreur ! Compléter les cases vides" );
				}
				catch (Exception eedf) 
				{
					JOptionPane.showMessageDialog(null, "Erreur : Chauffeur non ajouté à Rdota !" );
					eedf.printStackTrace();
				}
			refreshtab_chauffeur();
		}
			
		});
		buttonValid.setIcon(new ImageIcon("C:\\Users\\LFONSEQUE\\Pictures\\ok_button.png"));
		buttonValid.setBounds(217, 106, 50, 40);
		panel_addchaufeur.add(buttonValid);

	}	
		
		public void modifier_addchauffeur()				// boutton modifier
		{
			// Déclarations
			JButton btnModifier;
			
			//Programme
			btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					

					try {
						  Connection conne =null; 
						  Class.forName("org.postgresql.Driver"); 
						  conne = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Rdota", "postgres", "LFS31"); 
						  Statement st = conne.createStatement(); 
						  String sTest=textchauf.getText();
						  String sTest_1=textprenom.getText(); 
						  String sTest_2=textnum.getText(); 
						  String sTest_3=textsociete.getText(); 
						  String sTest_31=textidChauffeur.getText();
						if ( textchauf != null && textnum !=null && textprenom!=null && textsociete!=null && textidChauffeur != null )
						  {
						  String sql = ("UPDATE tchauffeur SET nom = '"+sTest+"', prenom='"+sTest_1+"', num='"+sTest_2+"', société='"+sTest_3+"'  WHERE \"idChauffeur\"='"+sTest_31+"';");
						  st.executeUpdate(sql);
						  System.out.println("Chauffeur Ajouté!");
						  }
						else	  JOptionPane.showMessageDialog(null, "Erreur ! Compléter les cases vides" );
						
						}
						catch (Exception ee) 
						{
							JOptionPane.showMessageDialog(null, "Erreur : Chauffeur non modifié de Rdota !" );
							ee.printStackTrace();
						}
					
					refreshtab_chauffeur();
				}
			});
			btnModifier.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnModifier.setBounds(298, 106, 89, 40);
			panel_addchaufeur.add(btnModifier);
		}
		
		public void supprimer_chauffeur_addchauffeur()   // boutton delete
		{
			JButton buttonDelete = new JButton("");
			buttonDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						  Connection conn1 =null; 
						  Class.forName("org.postgresql.Driver"); 
						  conn1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Rdota", "postgres", "LFS31"); 
						  Statement st = conn1.createStatement(); 
						  String sTest=textidChauffeur.getText();

						  String sql1 = ("DELETE FROM tchauffeur WHERE \"idChauffeur\" ='"+sTest+"';");
						  st.executeUpdate(sql1);
						  System.out.println("Chauffeur Supprimé!");
						  JOptionPane.showMessageDialog(null, "Chauffeur supprimé" );

						}
						catch (Exception ee) 
						{
							JOptionPane.showMessageDialog(null, "Erreur : Chauffeur non supprimé à Rdota !" );
							ee.printStackTrace();
						}
					
					refreshtab_chauffeur();
				}
			});
			buttonDelete.setIcon(new ImageIcon("C:\\Users\\LFONSEQUE\\Pictures\\cancel.gif"));
			buttonDelete.setBounds(421, 106, 54, 40);
			panel_addchaufeur.add(buttonDelete);
			
			JTextPane txtpnCompleter = new JTextPane();
			txtpnCompleter.setText("( \u00E0 completer que pour modifier ou supprimer la ligne voulu)");
			txtpnCompleter.setBounds(676, 106, 126, 49);
			panel_addchaufeur.add(txtpnCompleter);
			
			JButton btnNewRafraichirchauf = new JButton("Rafraichir");
			btnNewRafraichirchauf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					refreshtab_chauffeur();
				}
			});
			btnNewRafraichirchauf.setBounds(500, 106, 113, 40);
			panel_addchaufeur.add(btnNewRafraichirchauf);
		}
		
		public void addsociete()
		{
			// Déclaration local
			JTextField textnomsociete;
			JButton buttonV;
			JButton buttonX;
			JLabel label_nomsociete_addsociete;
			
			// Programme
			panel_addsociete = new JPanel();
			tabbedPane.addTab("Add Société", null, panel_addsociete, null);
			panel_addsociete.setLayout(null);
			
			textnomsociete = new JTextField();
			textnomsociete.setBounds(308, 27, 183, 40);
			textnomsociete.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textnomsociete.setColumns(10);
			panel_addsociete.add(textnomsociete);
			
			
			
			label_nomsociete_addsociete = new JLabel("Nom de la soci\u00E9t\u00E9  :");
			label_nomsociete_addsociete.setBounds(48, 27, 209, 33);
			label_nomsociete_addsociete.setFont(new Font("Tahoma", Font.BOLD, 18));
			panel_addsociete.add(label_nomsociete_addsociete);
			
			JScrollPane scrollPane_tab_addsociete = new JScrollPane();
			scrollPane_tab_addsociete.setBounds(48, 91, 785, 378);
			panel_addsociete.add(scrollPane_tab_addsociete);
			
			/////////////////////// TABLEAU SOCIETE //////////////////////////////////////
			
			table_1 = new JTable();
			scrollPane_tab_addsociete.setViewportView(table_1);
			scrollPane_tab_addsociete.setViewportView(table_1);
			scrollPane_tab_addsociete.setViewportView(table_1);
			table_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						int i =table_1.getSelectedRow();
					}catch(Exception e){System.err.println(e);
					JOptionPane.showMessageDialog(null, "erreur de deplacement"+e.getLocalizedMessage());}
				}
			});
			table_1.setModel(model1);
			
			
			model1.addColumn("Nom");
			model1.addColumn("idSociete");
	
			
			try {
			  
			  stm1=conn2.obtenirconnexion().createStatement(); 
			  ResultSet Rs1 = stm1.executeQuery("SELECT * FROM tsociete");
			  
				     while(Rs1.next())
				     {
				     
					 model1.addRow(new Object[]{Rs1.getString("Nom"),Rs1.getString("idSociete") });	 

				     }
			}catch (Exception e) 
			{
				System.err.println(e);
			}
		
			/////////////////////////////////////// bouttons interface /////////////////////////////////////////////
			
			
			buttonV = new JButton("");
			buttonV.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							  
							
							  Connection conni =null; 
							  Class.forName("org.postgresql.Driver"); 
							  conni = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Rdota", "postgres", "LFS31"); 
							  Statement st = conni.createStatement(); 
							  String sTest=textnomsociete.getText();
							  if (textnomsociete != null)
							  {String sql = ("INSERT INTO tsociete VALUES ('"+sTest+"');");
							  st.executeUpdate(sql);
							  System.out.println("Société Ajouté!");
							  JOptionPane.showMessageDialog(null,"Société ajouté");
							  //rafraichir_tsociete ();
							  }
							}
							catch (Exception ee) 
							{
								JOptionPane.showMessageDialog(null, "Société Non Ajouté à Rdota" );
								ee.printStackTrace();
							}
						rafraichir_tsociete();
						
					}
				});
			buttonV.setIcon(new ImageIcon("C:\\Users\\LFONSEQUE\\Pictures\\ok_button.png"));
			buttonV.setBounds(562, 27, 50, 40);
			panel_addsociete.add(buttonV);
			
			buttonX = new JButton("");
			buttonX.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						  Connection conn =null; 
						  Class.forName("org.postgresql.Driver"); 
						  conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Rdota", "postgres", "LFS31"); 
						  Statement st = conn.createStatement(); 
						  String sTest1=textnomsociete.getText();


						  String sql1 = ("DELETE FROM tsociete WHERE \"Nom\" ='"+sTest1+"';");
						  st.executeUpdate(sql1);
						  System.out.println("Société Supprimé!");
						  JOptionPane.showMessageDialog(null, "Société Supprimé !" );


						}
						catch (Exception ee) 
						{
							JOptionPane.showMessageDialog(null, "Erreur : Chauffeur non supprimé à Rdota !" );
							ee.printStackTrace();
						}
					
					rafraichir_tsociete ();
					
				}
			});
			buttonX.setIcon(new ImageIcon("C:\\Users\\LFONSEQUE\\Pictures\\cancel.gif"));
			buttonX.setBounds(656, 27, 54, 40);
			panel_addsociete.add(buttonX);
			
			JButton btnRafraichir = new JButton("Rafraichir");
			btnRafraichir.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnRafraichir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rafraichir_tsociete ();
				}
			});
			btnRafraichir.setBounds(740, 27, 106, 40);
			panel_addsociete.add(btnRafraichir);
			
			
		}
						
		public void afficher_2tab()
		{
			// Déclarations 
			JScrollPane scrollPane_chauf_afftab;
			JScrollPane scrollPane_societe_afftabs;
			
			//Programme
			
			JButton btnAffichageDesTables = new JButton("Rafraichir les tables");
			btnAffichageDesTables.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {	
					rafraichir_affichage2tab();
				}
			});
			btnAffichageDesTables.setBounds(21, 421, 838, 48);
			btnAffichageDesTables.setForeground(Color.BLACK);
			btnAffichageDesTables.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnAffichageDesTables.setBackground(new Color(222, 184, 135));
			panel_affichertabs.add(btnAffichageDesTables);
			
			// tab chauffeur*************************************************************************************************************************************
			scrollPane_chauf_afftab = new JScrollPane();
			scrollPane_chauf_afftab.setBounds(21, 11, 403, 388);
			panel_affichertabs.add(scrollPane_chauf_afftab);
			
			table_2 = new JTable();
			scrollPane_chauf_afftab.setViewportView(table_2);

			table_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						int i =table_2.getSelectedRow();
					}catch(Exception e){System.err.println(e);
					JOptionPane.showMessageDialog(null, "erreur de deplacement"+e.getLocalizedMessage());}
				
					
				}
			});
			table_2.setModel(model2);
			
			
			model2.addColumn("nom");
			model2.addColumn("prenom");
			model2.addColumn("num");
			model2.addColumn("société");
			model2.addColumn("idChauffeur");
			
			try {
			  
			  stm2=conn3.obtenirconnexion().createStatement(); 
			  ResultSet Rs2 = stm2.executeQuery("SELECT * FROM tchauffeur");
			  
				     while(Rs2.next())
				     {
				     
					 model2.addRow(new Object[]{Rs2.getString("nom"),Rs2.getString("prenom"),Rs2.getString("num"),Rs2.getString("société"),Rs2.getString("idChauffeur") });	 

				     }
			}catch (Exception e) 
			{
				System.err.println(e);
			}
			 
			//tab societe *************************************************************************************************************************************
			
			scrollPane_societe_afftabs = new JScrollPane();
			scrollPane_societe_afftabs.setBounds(472, 11, 387, 387);
			panel_affichertabs.add(scrollPane_societe_afftabs);

			table_3 = new JTable();
			scrollPane_societe_afftabs.setViewportView(table_3);
		
			scrollPane_societe_afftabs.setViewportView(table_3);
			scrollPane_societe_afftabs.setViewportView(table_3);
			table_3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						int i =table_3.getSelectedRow();
					}catch(Exception e){System.err.println(e);
					JOptionPane.showMessageDialog(null, "erreur de deplacement"+e.getLocalizedMessage());}
				
					
				}
			});
			table_3.setModel(model3);
			
			
			model3.addColumn("nom");
			model3.addColumn("idSociete");
	
			
			try {
			  
			  stm3=conn4.obtenirconnexion().createStatement(); 
			  ResultSet Rs3 = stm3.executeQuery("SELECT * FROM tsociete");
			  
				     while(Rs3.next())
				     {
				     
					 model3.addRow(new Object[]{Rs3.getString("Nom"),Rs3.getString("idSociete") });	 

				     }
			}catch (Exception e) 
			{
				System.err.println(e);
			}
			
		}
	
		public void impexp()
		{
			//Déclarations
			JLabel label_choisir_fichier_impexp;
			JLabel label_importer;
			JLabel label_exporter;
			JButton btnParcourir_export;
			Connecter conn6=new Connecter();
			Connecter conn7=new Connecter();
			//Programme
			
			JPanel panel_import_export = new JPanel();
			tabbedPane.addTab("Import Export Table", null, panel_import_export, null);
			panel_import_export.setLayout(null);
			
			label_choisir_fichier_impexp = new JLabel("Veuillez choisir le fichier \u00E0 importer ou exporter ! ");
			label_choisir_fichier_impexp.setFont(new Font("Tahoma", Font.BOLD, 16));
			label_choisir_fichier_impexp.setBounds(251, 57, 414, 32);
			panel_import_export.add(label_choisir_fichier_impexp);
			
			label_importer = new JLabel("Importer :");
			label_importer.setFont(new Font("Tahoma", Font.BOLD, 18));
			label_importer.setBounds(287, 201, 112, 32);
			panel_import_export.add(label_importer);
			
			label_exporter = new JLabel("Exporter :");
			label_exporter.setFont(new Font("Tahoma", Font.BOLD, 18));
			label_exporter.setBounds(287, 123, 112, 32);
			panel_import_export.add(label_exporter);
			

			
			btnParcourir_export = new JButton("Parcourir");
			btnParcourir_export.setBackground(Color.WHITE);
			btnParcourir_export.setForeground(Color.BLACK);
			btnParcourir_export.setBounds(396, 122, 188, 40);
			
			panel_import_export.add(btnParcourir_export);
			
			JButton buttonParcourir_imp = new JButton("Parcourir");
			buttonParcourir_imp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					 String chemin="";
                    JFileChooser fc = new JFileChooser();
                             int retval = fc.showOpenDialog(null);
                             if (retval == JFileChooser.APPROVE_OPTION) {
                                 chemin = fc.getSelectedFile().getAbsolutePath();
                                 chemin = chemin.replace("\\", "/");
                                 
                 try {
				  
                 Statement stm7=conn7.obtenirconnexion().createStatement(); 
				  stm7.executeQuery("COPY   tchauffeur from '"+chemin+"' with DELIMITER ';' "); // "DELETE FROM tsociete WHERE \"Nom\" ='"+sTest1+"';"
				  JOptionPane.showMessageDialog(null, "Fichier bien importé ! ");
					    
				}catch (Exception e) 
				{
					System.err.println(e);
					JOptionPane.showMessageDialog(null, "Fichier non importé ! ");
				}
                 }	
				}
			});
			buttonParcourir_imp.setForeground(Color.BLACK);
			buttonParcourir_imp.setBackground(Color.WHITE);
			buttonParcourir_imp.setBounds(396, 200, 188, 40);
			panel_import_export.add(buttonParcourir_imp);
			
			JButton btnInformationImportante = new JButton("Information Importante :");
			btnInformationImportante.setForeground(Color.RED);
			btnInformationImportante.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnInformationImportante.setBounds(42, 280, 279, 54);
			panel_import_export.add(btnInformationImportante);
			
			JEditorPane dtrpnFbnw = new JEditorPane();
			dtrpnFbnw.setFont(new Font("Tahoma", Font.PLAIN, 14));
			dtrpnFbnw.setText("Pour importer ou exporter une table, il vous faut un fichier .CSV avec les droit n\u00E9c\u00E9ssaire \u00E0 l'importation et/ou l'exportation. Veuillez suivre la d\u00E9marche suivante :\r\n\r\n1.Clique droit sur votre fichier .csv\r\n2.Propri\u00E9r\u00E9 , puis, dans l'onglet s\u00E9curit\u00E9 cliquez sur Modifier, ensuite cliquez sur Ajouter\r\n3.Ecrire Tout dans la zone de texte et faire v\u00E9rifier les noms\r\n4.S\u00E9lectionnez Tout le monde et valider\r\n5.S\u00E9lectionnez le fichier \u00E0 importer ou exporter\r\n");
			dtrpnFbnw.setBounds(370, 280, 448, 190);
			panel_import_export.add(dtrpnFbnw);
			
			
			btnParcourir_export.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
                    String chemin="";
                      System.out.println("fghjkl11111");
                     JFileChooser fc = new JFileChooser();
                     System.out.println("fghjkl2222");
                              int retval = fc.showOpenDialog(null);
                              System.out.println("fghjkl33333");
                              if (retval == JFileChooser.APPROVE_OPTION) {
                                  System.out.println("fghjkl44444");
                                  chemin = fc.getSelectedFile().getAbsolutePath();
                                  System.out.println("fghjkl555555");
                                  chemin = chemin.replace("\\", "/");
                                  
                  try {
				  
                  Statement stm6=conn6.obtenirconnexion().createStatement(); 
				  stm6.executeQuery("COPY   tchauffeur to '"+chemin+"' with DELIMITER ';' "); // "DELETE FROM tsociete WHERE \"Nom\" ='"+sTest1+"';"
				  JOptionPane.showMessageDialog(null, "Fichier bien exporté ! ");
					    
				}catch (Exception e) 
				{
					System.err.println(e);
					JOptionPane.showMessageDialog(null, "Fichier non exporté ! ");
				}
                  }
              }
        });
	
			
			
			
		}
		
		public void control_horaire()
		{
			// Déclarations
			JLabel label_nomchauffeur_control;
			JLabel img_chauffeur_tete;
			JButton button_validation;
			JLabel label_date_debut;
			JLabel label_date_fin;

			// Programme
			
			panel_control_hoair = new JPanel();
			tabbedPane.addTab("Control horaire", null, panel_control_hoair, null);
			panel_control_hoair.setLayout(null);
			
			label_nomchauffeur_control = new JLabel("Nom du chauffeur  :");
			label_nomchauffeur_control.setBounds(98, 11, 150, 33);
			label_nomchauffeur_control.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel_control_hoair.add(label_nomchauffeur_control);
			
			textField_nomchauffeur_control_horaire = new JTextField();
			textField_nomchauffeur_control_horaire.setBounds(271, 12, 164, 28);
			textField_nomchauffeur_control_horaire.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textField_nomchauffeur_control_horaire.setColumns(10);
			panel_control_hoair.add(textField_nomchauffeur_control_horaire);
			
			
			
			label_date_debut = new JLabel("Date de D\u00E9but :");
			label_date_debut.setBounds(10, 55, 98, 14);
			label_date_debut.setFont(new Font("Tahoma", Font.BOLD, 12));
			panel_control_hoair.add(label_date_debut);
			
			
			label_date_fin = new JLabel("Date de Fin :");
			label_date_fin.setBounds(271, 55, 96, 14);
			label_date_fin.setFont(new Font("Tahoma", Font.BOLD, 12));
			panel_control_hoair.add(label_date_fin);
			
			img_chauffeur_tete = new JLabel(""); 
			img_chauffeur_tete.setBounds(576, 11, 150, 120);
			img_chauffeur_tete.setIcon(new ImageIcon("C:\\Users\\LFONSEQUE\\Pictures\\chauf1.png"));
			img_chauffeur_tete.setBackground(new Color(230, 230, 250));
			panel_control_hoair.add(img_chauffeur_tete);
			
			JScrollPane scrollPane_tabhoraire_control = new JScrollPane();
			scrollPane_tabhoraire_control.setBounds(38, 227, 812, 262);
			panel_control_hoair.add(scrollPane_tabhoraire_control);
			
			////////////////////////////////////////////// TABLEAU HORAIRE ////////////////////////////////////////////////////////////////////////
			table_4 = new JTable();
			scrollPane_tabhoraire_control.setViewportView(table_4);
			scrollPane_tabhoraire_control.setViewportView(table_4);
			scrollPane_tabhoraire_control.setViewportView(table_4);
			table_4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						int i =table_4.getSelectedRow();
					}catch(Exception e){System.err.println(e);
					JOptionPane.showMessageDialog(null, "erreur de deplacement"+e.getLocalizedMessage());}
				}
			});
			table_4.setModel(model4);		

			model4.addColumn("chauffeur");
			model4.addColumn("date");
			model4.addColumn("connexion");
			model4.addColumn("deconnexion");
			model4.addColumn("idHoraires");
			
			try {
			  
			  stm4=conn5.obtenirconnexion().createStatement(); 
			  ResultSet Rs4 = stm4.executeQuery("SELECT * FROM thoraires");
			  
				     while(Rs4.next())
				     {
				     
					 model4.addRow(new Object[]{Rs4.getString("chauffeur"),Rs4.getString("date"),Rs4.getString("connexion"),Rs4.getString("deconnexion"),Rs4.getString("idHoraires")});	 

				     }
			}catch (Exception e) 
			{
				System.err.println(e);
			}
			
			JCalendar calendar_debut = new JCalendar();
			calendar_debut.setBounds(59, 80, 189, 137);
			panel_control_hoair.add(calendar_debut);
			
			JCalendar calendar_fin = new JCalendar();
			calendar_fin.setBounds(271, 79, 189, 137);
			panel_control_hoair.add(calendar_fin);
			
			button_validation = new JButton("");
			button_validation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					int jour =calendar_debut.getCalendar().get(Calendar.DAY_OF_MONTH);
					//System.out.println(jour);
					int mois =calendar_debut.getCalendar().get(Calendar.MONTH);
					mois ++; // car le programme compte le 0
					//System.out.println(mois);
					int annee =calendar_debut.getCalendar().get(Calendar.YEAR);
					//System.out.println(annee);
					
					//System.out.println("///////////////////////////////////////////////////////////////////////");
					
					int jour_fin =calendar_fin.getCalendar().get(Calendar.DAY_OF_MONTH);
					//System.out.println(jour_fin);
					int mois_fin =calendar_fin.getCalendar().get(Calendar.MONTH);
					mois_fin ++; // car le programme compte le 0
					//System.out.println(mois_fin);
					int annee_fin =calendar_fin.getCalendar().get(Calendar.YEAR);
					//System.out.println(annee_fin);
					
					nom_chauffeur = textField_nomchauffeur_control_horaire.getText();
					
					date_debut = ""+annee+"-"+mois+"-"+jour+"";
					date_fin =""+annee_fin+"-"+mois_fin+"-"+jour_fin+"";
					
					System.out.println(date_debut);
					System.out.println(date_fin);
					
					refreshtab_horaire();
					
				}
			});
			button_validation.setBounds(497, 153, 50, 40);
			button_validation.setIcon(new ImageIcon("C:\\Users\\LFONSEQUE\\Pictures\\ok_button.png"));
			panel_control_hoair.add(button_validation);

		}	
	
		public void rafraichir_affichage2tab()
		{
			
			JScrollPane scrollPane_chauf_afftab = new JScrollPane();
			scrollPane_chauf_afftab.setBounds(21, 11, 403, 388);
			panel_affichertabs.add(scrollPane_chauf_afftab);
		
//////////////////////////////////////////////TABLEAU chauffeur ////////////////////////////////////////////////////////////////////////

			
			table_2 = new JTable();
			scrollPane_chauf_afftab.setViewportView(table_2);
			scrollPane_chauf_afftab.setViewportView(table_2);
			scrollPane_chauf_afftab.setViewportView(table_2);
			table_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						int i =table_2.getSelectedRow();
					}catch(Exception e){System.err.println(e);
					JOptionPane.showMessageDialog(null, "erreur de deplacement"+e.getLocalizedMessage());}
				}
			});
			table_2.setModel(model2);
			
			int n = model2.getRowCount();
			
			for(int i=n-1;i>=0;i--)
				model2.removeRow(i);
			
			try {
			  
			  stm2=conn3.obtenirconnexion().createStatement(); 
			  ResultSet Rs2 = stm2.executeQuery("SELECT * FROM tchauffeur");
			  
				     while(Rs2.next())
				     {
				     
					 model2.addRow(new Object[]{Rs2.getString("nom"),Rs2.getString("prenom"),Rs2.getString("num"),Rs2.getString("société"),Rs2.getString("idChauffeur") });	 

				     }
			}catch (Exception e) 
			{
				System.err.println(e);
			}
			
			
			
			JScrollPane scrollPane_societe_afftabs = new JScrollPane();
			scrollPane_societe_afftabs.setBounds(472, 11, 387, 387);
			panel_affichertabs.add(scrollPane_societe_afftabs);
			
			
//////////////////////////////////////////////TABLEAU societe ////////////////////////////////////////////////////////////////////////


			table_3 = new JTable();
			scrollPane_societe_afftabs.setViewportView(table_3);
			scrollPane_societe_afftabs.setViewportView(table_3);
			scrollPane_societe_afftabs.setViewportView(table_3);
			table_3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						int i =table_3.getSelectedRow();
					}catch(Exception e){System.err.println(e);
					JOptionPane.showMessageDialog(null, "erreur de deplacement"+e.getLocalizedMessage());}
				}
			});
			table_3.setModel(model3);
			
			int no = model3.getRowCount();
			
			for(int o=no-1;o>=0;o--)
				model3.removeRow(o);
	
			
			try {
			  
			  stm3=conn4.obtenirconnexion().createStatement(); 
			  ResultSet Rs3 = stm3.executeQuery("SELECT * FROM tsociete");
			  
				     while(Rs3.next())
				     {
				     
					 model3.addRow(new Object[]{Rs3.getString("Nom"),Rs3.getString("idSociete") });	 

				     }
			}catch (Exception e) 
			{
				System.err.println(e);
			}

		}
		
		public void rafraichir_tsociete ()
		{
			JScrollPane scrollPane_tab_addsociete = new JScrollPane();
			scrollPane_tab_addsociete.setBounds(48, 91, 764, 378);
			panel_addsociete.add(scrollPane_tab_addsociete);
			
			/////////////////////// TABLEAU SOCIETE //////////////////////////////////////
			
			table_1 = new JTable();
			scrollPane_tab_addsociete.setViewportView(table_1);
			scrollPane_tab_addsociete.setViewportView(table_1);
			scrollPane_tab_addsociete.setViewportView(table_1);
			table_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						int i =table_1.getSelectedRow();
					}catch(Exception e){System.err.println(e);
					JOptionPane.showMessageDialog(null, "erreur de deplacement"+e.getLocalizedMessage());}
				}
			});
			table_1.setModel(model1);
			

			int y = model1.getRowCount();
			
			for(int a=y-1;a>=0;a--)
				model1.removeRow(a);
			
			try {
			  
			  stm1=conn2.obtenirconnexion().createStatement(); 
			  ResultSet Rs1 = stm1.executeQuery("SELECT * FROM tsociete");
			  
				     while(Rs1.next())
				     {
				     
					 model1.addRow(new Object[]{Rs1.getString("Nom"),Rs1.getString("idSociete") });	 

				     }
			}catch (Exception e) 
			{
				System.err.println(e);
			}
			
		}
		
		/**
		 * Create the frame.
		 */
		
		public menu_Rdota() {

			presentation_ihm();		

			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(21, 171, 895, 528);
			contentPane.add(tabbedPane);
					
			panel_affichertabs = new JPanel();
			tabbedPane.addTab("Afficher tab", null, panel_affichertabs, null);
			panel_affichertabs.setLayout(null);	
			
			add_chauffeur();
			ajouter_chauffeur_addchauffeur();
			modifier_addchauffeur();
			supprimer_chauffeur_addchauffeur();		
			addsociete();
			afficher_2tab();
			control_horaire();		
			clock();
			impexp();
			

			// j'aime les sushis !!
		}
	}
