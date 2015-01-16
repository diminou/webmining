package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.Iterator;

import benoit.HashMapWrapDouble;
import benoit.HashMapWrapper;
import benoit.TravailFichier;
import fileSysUtils.IndexWrapper;
import fileSysUtils.MainForestier;
import quentin.GestionRequete;

public class Fenetre extends JFrame {

	private JButton bouton = new JButton("Rechercher");
	private JButton boutonCreateIndex = new JButton("Créer l'index");
	private JTextField jtf = new JTextField();
	private JPanel containerGlobal = new JPanel();
	private JPanel container = new JPanel();
	private JPanel resultatContainer = new JPanel();

	/*final static IndexWrapper index = new IndexWrapper();
	final static HashMapWrapper hmw = new HashMapWrapper();
	final static HashMapWrapDouble hmwDouble = new HashMapWrapDouble();*/
	
	static IndexWrapper index = MainForestier.index;
	static HashMapWrapper hmw = MainForestier.hmw;
	static HashMapWrapDouble hmwDouble = MainForestier.hmwd;
	public Fenetre() {

		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		int hauteur = (int) tailleEcran.getHeight() - 100;
		int largeur = (int) tailleEcran.getWidth() - 200;
		this.setTitle("Recherche Text Mining");
		this.setSize(largeur, hauteur);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		Font police = new Font("Arial", Font.BOLD, 14);
		jtf.setFont(police);
		jtf.setPreferredSize(new Dimension(400, 30));
		jtf.setForeground(Color.BLACK);
		jtf.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					resultatContainer.removeAll();
					Rechercher(jtf.getText(), getParent(), resultatContainer);
					containerGlobal.remove(resultatContainer);
					containerGlobal.add(resultatContainer);
					setContentPane(containerGlobal);
					repaint();

				}

			}
		});

		top.add(jtf);

		bouton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resultatContainer.removeAll();
				Rechercher(jtf.getText(), getParent(), resultatContainer);
				containerGlobal.remove(resultatContainer);
				containerGlobal.add(resultatContainer);
				setContentPane(containerGlobal);
				repaint();
			}
		});
		
		boutonCreateIndex.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			  
		    	
				try {
					TravailFichier.createIndexStemming("./corpus/",MainForestier.index);
					index.serializeRoot();
					
					
					/*HashMap<String, Integer> hm = TravailFichier.createIndexNumberDocs("./corpus/");
					hmw.setHM(hm);
					hmw.serializeHM();
					
					HashMap<String, Double> hmDouble = TravailFichier.createIndexDocs("./corpus/").getMapDocs();
					hmwDouble.setHM(hmDouble);
					hmw.serializeHM();*/
					
					
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				repaint();
				
			}
		});
		
		
		
		
		top.add(bouton);
		top.add(boutonCreateIndex);
		top.setBackground(Color.white);
		containerGlobal.setBackground(Color.white);
		container.setBackground(Color.white);
		resultatContainer.setBackground(Color.white);
		container.add(top, BorderLayout.NORTH);

		containerGlobal.add(container);
		resultatContainer.setPreferredSize(new Dimension((largeur - container
				.getWidth()), (hauteur - container.getHeight())));

		resultatContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
		containerGlobal.add(resultatContainer);

		this.setContentPane(containerGlobal);

		this.setVisible(true);

	}

	public static void Rechercher(String textChamp, Container p, JPanel jp) {
		if (!(textChamp.equals(""))) {
			
			//TODO vérif si cliquage createindex avt 
			
			
			jp.repaint();
			System.out.println("You clicked the button " + textChamp);
			
			
			List<String> l = new ArrayList<String>();

			//TODO remplacer index 
			//TODO IndexWrapper index = Méhode diminou 
			
//			IndexWrapper index = new IndexWrapper();
//			try {
//				TravailFichier.createIndex("corpus/",index);
//			} catch (IOException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
//			hmw.deserializeHM();
			
			HashMap<Integer, Double> HM = GestionRequete.CalculAllScore(textChamp, MainForestier.index,
					MainForestier.hmwd, MainForestier.hmw);
//			HashMap<String, Double> HM = new HashMap<String, Double>();
//			HM.put("trois", 3.0);
//			HM.put("deux", 2.0);
//			HM.put("quatre", 4.0);
//			HM.put("un", 1.0);
//			HM.put("cinq", 5.0);
			
			TreeMap<Integer, Double>  treeMap = GestionRequete.classerDocument(HM);
			List<Integer> lint = new ArrayList<Integer>();
			lint= GestionRequete.mapKeyToListe(treeMap);
			
			HashMap<String,Integer> mapIndexFileNumber=TravailFichier.createIndexNumberDocs("corpus/");
			
			
			
			for(int aaa : lint){
				
				java.util.Iterator<String> i=mapIndexFileNumber.keySet().iterator();
				while(i.hasNext()){
				String str= i.next();
					if(aaa==mapIndexFileNumber.get(str)){
						l.add(str);
						break;
					}
				}
			}
		
			
//			l.add("Un");
//			l.add("Deux");
//			l.add("trois");
//			l.add("quatre");

			TableauRes modele = new TableauRes(l);

			final JTable table = new JTable(modele);
			table.setRowMargin(7);
			table.setRowHeight(30);
			table.setShowGrid(false);

			table.getColumn("A").setHeaderValue("Nom des documents correspondant à la requête");
			
			table.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					int rowIndex = table.getSelectedRow();
					System.out.println(table.getValueAt(rowIndex, 0).toString());
					
					// méthode pour ouvrir un document texte automatiquement avec gedit
					Desktop desk = Desktop.getDesktop();
					
					//TODO à remplacer par le bon document
						File f = new File(table.getValueAt(rowIndex, 0).toString());
//						File f = new File("results/texte.95-1.txt");
						f.setWritable(false);
						f.setExecutable(false);
						f.setReadable(true);
						try {
							desk.open(f);
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			JScrollPane js = new JScrollPane(table);

			js.setPreferredSize(new Dimension((jp.getWidth() - 14), (jp.getHeight() - 14)));
			js.getViewport().setBackground(Color.white);

			jp.add(js);
			jp.setBackground(Color.white);

		} else {
			JOptionPane.showMessageDialog(p,
					"Veuillez remplir le champ de saisie.");
		}
	}

}
