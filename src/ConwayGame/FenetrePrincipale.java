package ConwayGame;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
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
	private Modele leModele;
	private CaseVue[][] lCasesVues;
	private final JButton bReInit;
	private final JButton bDemarrer;
	private final JButton bPause;

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
		// Taille de la fenêtre principale
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
		// Ajout d'un conteneur à droite de type FlowLayout
		this.pDroite = new JPanel(new FlowLayout());
		this.pDroite.setPreferredSize(new Dimension(150, 25));
		this.pPrincipal.add(this.pDroite, BorderLayout.EAST);
		//Bouton démarrer, qui déclenche la fonction play() du modèle au clic
		this.bDemarrer = new JButton("Démarrer");
		this.bDemarrer.setPreferredSize(new Dimension(140,25));
		this.bDemarrer.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				leModele.play();
			}
		});
		//Bouton pause, qui déclenche la fonction pause() du modèle au clic
		this.bPause = new JButton("Pause");
		this.bPause.setPreferredSize(new Dimension(140,25));
		this.bPause.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				leModele.pause();
			}
		});
		//Bouton réinitialiser, qui déclenche la fonction reInit() du modèle au clic
		this.bReInit = new JButton("Initialiser");
		this.bReInit.setPreferredSize(new Dimension(140,25));
		this.bReInit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				if(bReInit.getText().equals("Réinitialiser")){
					bReInit.setText("Initialiser");
				}
				else{
					bReInit.setText("Réinitialiser");
				}
				leModele.reInit(bReInit.getText());
			}
		});
		// Ajout des boutons dans le conteneur de la region EAST
		this.pDroite.add(this.bDemarrer);
		this.pDroite.add(this.bPause);
		this.pDroite.add(this.bReInit);
		// Ajout de la grille
		this.pCentre = new JPanel();
		this.pPrincipal.add(this.pCentre, BorderLayout.CENTER);
		this.pCentre.setLayout(new GridLayout(uneGrilleDimension,
				uneGrilleDimension));
		// Remplissage de la grille, sauvegarde des références cases créent dans
		// lCases
		this.lCasesVues = new CaseVue[uneGrilleDimension][uneGrilleDimension];
		for (int caseX = 0; caseX < uneGrilleDimension; caseX++) {
			for (int caseY = 0; caseY < uneGrilleDimension; caseY++) {
				lCasesVues[caseX][caseY] = new CaseVue(caseX,caseY);
				// On transmet au modèle l'évènement "clic sur la case" pour qu'il la mette à jour
				lCasesVues[caseX][caseY].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent event) {
						bReInit.setText("Réinitialiser");
						CaseVue laCaseVueClic = (CaseVue) event.getSource();
						leModele.updateCase(laCaseVueClic.getPosX(),laCaseVueClic.getPosY());
					}
				});
				//On ajoute la CaseVue dans le GridLayout pCentre
				this.pCentre.add(lCasesVues[caseX][caseY]);
			}
		}
	}
	/**
	 * Mise à jour de la grille selon le modèle
	 */
	@Override
	public void update(Observable Modele, Object arg1) {
		Case[][] grille = (Case[][]) arg1;
		for (int caseX = 0; caseX < cDimension; caseX++) {
			for (int caseY = 0; caseY < cDimension; caseY++) {
				if ((grille[caseX][caseY]).isAllumee()) {
					lCasesVues[caseX][caseY].setBackground(Color.blue);
				} else {
					lCasesVues[caseX][caseY].setBackground(Color.white);
				}
			}
		}
	}
}
