package ConwayGame;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class FenetrePrincipale extends JFrame implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Attributs
	 */
	private JPanel pPrincipal, pCentre, pDroite;
	private JMenuBar menuBarre;
	private FlowLayout pBoutons;
	private int cDimension;
	private JPanel[][] lCases;
	private Modele leModele;

	/**
	 * Accesseurs pour leModele
	 * @return
	 */
	public Modele getLeModele() {
		return leModele;
	}
	public void setLeModele(Modele leModele) {
		this.leModele = leModele;
	}
	/**
	 * Constructeur
	 */
	public FenetrePrincipale(int uneGrilleDimension) {
		cDimension = uneGrilleDimension;
		// Taille de la fen�tre principale
		this.setSize(700, 700);
		// Ajout d'une barre de menu
		this.menuBarre = new JMenuBar();
		this.setJMenuBar(this.menuBarre);
		// Ajout d'un menu
		JMenu menu = new JMenu("Fichiers");
		this.menuBarre.add(menu);
		// Ajout d'un conteneur principal
		this.pPrincipal = new JPanel(new BorderLayout());
		setContentPane(this.pPrincipal);
		// Ajout de la grille
		this.pCentre = new JPanel();
		this.pPrincipal.add(this.pCentre, BorderLayout.CENTER);
		this.pCentre.setLayout(new GridLayout(uneGrilleDimension,
				uneGrilleDimension));
		// Remplissage de la grille, sauvegarde des r�f�rences cases cr�ent dans
		// lCases
		this.lCases = new JPanel[uneGrilleDimension][uneGrilleDimension];
		for (int caseY = 0; caseY < uneGrilleDimension; caseY++) {
			for (int caseX = 0; caseX < uneGrilleDimension; caseX++) {
				lCases[caseY][caseX] = new JPanel();
				lCases[caseY][caseX].setBackground(Color.white);
				lCases[caseY][caseX].setBorder(BorderFactory
						.createLineBorder(Color.black));
				lCases[caseY][caseX].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent event) {
						// On transmet au mod�le l'�v�nement "clic sur la case"
						
					}
				});

				this.pCentre.add(lCases[caseY][caseX]);
			}
		}
		// Ajout d'un conteneur � droite de type FlowLayout
		this.pDroite = new JPanel(new FlowLayout());
		this.pPrincipal.add(this.pDroite, BorderLayout.EAST);
		//Bouton d�marrer, qui d�clenche la fonction play() du mod�le au clic
		JButton bDemarrer = new JButton("D�marrer");
		bDemarrer.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				leModele.play();
			}
		});
		//Bouton pause, qui d�clenche la fonction pause() du mod�le au clic
		JButton bPause = new JButton("Pause");
		bPause.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				leModele.pause();
			}
		});
		//Bouton r�initialiser, qui d�clenche la fonction reInit() du mod�le au clic
		JButton bReInit = new JButton("R�initialiser");
		bReInit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				leModele.reInit();
			}
		});
		// Ajout des boutons dans le conteneur de la region EAST
		this.pDroite.add(bDemarrer);
		this.pDroite.add(bPause);
		this.pDroite.add(bReInit);
	}
	/**
	 * Mise � jour de la grille selon le mod�le
	 */
	@Override
	public void update(Observable Modele, Object arg1) {
		Case[][] grille = (Case[][]) arg1;
		for (int caseY = 0; caseY < cDimension; caseY++) {
			for (int caseX = 0; caseX < cDimension; caseX++) {
				if ((grille[caseX][caseY]).isAllumee()) {
					lCases[caseY][caseX].setBackground(Color.blue);
				} else {
					lCases[caseY][caseX].setBackground(Color.white);
				}
			}
		}
	}
}
