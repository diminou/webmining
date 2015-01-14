package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Fenetre extends JFrame {

	private JButton bouton = new JButton("Rechercher");
	private JTextField jtf = new JTextField();
	// private JTextField jtf = new JTextField("Valeur par défaut");
	private JPanel containerGlobal = new JPanel();
	private JPanel container = new JPanel();
	private JPanel resultatContainer = new JPanel();

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
				Rechercher(jtf.getText(), getParent(), resultatContainer);
				containerGlobal.remove(resultatContainer);
				containerGlobal.add(resultatContainer);
				setContentPane(containerGlobal);
				repaint();
			}
		});
		top.add(bouton);
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

		// this.setContentPane(container);
		this.setContentPane(containerGlobal);

		this.setVisible(true);

	}

	public static void Rechercher(String textChamp, Container p, JPanel jp) {
		if (!(textChamp.equals(""))) {
			// TODO appeller la méthode d'affichage des docs pertinents
			jp.repaint();
			System.out.println("You clicked the button " + textChamp);

			List<String> l = new ArrayList<String>();
			l.add("Un");
			l.add("Deux");
			l.add("trois");
			l.add("quatre");

			TableauRes modele = new TableauRes(l);

			JTable table = new JTable(modele);
			table.setRowMargin(7);
			table.setRowHeight(30);
			table.setShowGrid(false);

			table.getColumn("A").setHeaderValue("Nom des documents correspondant à la requête");

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