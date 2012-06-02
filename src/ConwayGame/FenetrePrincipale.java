package ConwayGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

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
	private int cDimension;
	private Modele leModele;
	private CaseVue[][] lCasesVues;
	private final JButton bInit;
	private final JButton bDemarrer;
	private final JButton bPause;
	private final JButton bRandom;
	private boolean isMouseDown = false;

	/**
	 * Accesseurs pour leModele
	 * 
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
		// Ajout d'un conteneur � droite de type FlowLayout
		this.pDroite = new JPanel(new FlowLayout());
		this.pDroite.setPreferredSize(new Dimension(150, 25));
		this.pPrincipal.add(this.pDroite, BorderLayout.EAST);
		// Bouton d�marrer, qui d�clenche la fonction play() du mod�le au clic
		this.bDemarrer = new JButton("D�marrer");
		this.bDemarrer.setPreferredSize(new Dimension(140, 25));
		this.bDemarrer.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				leModele.play();
			}
		});
		// Bouton pause, qui d�clenche la fonction pause() du mod�le au clic
		this.bPause = new JButton("Pause");
		this.bPause.setPreferredSize(new Dimension(140, 25));
		this.bPause.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				leModele.pause();
			}
		});
		// Bouton initialiser, qui d�clenche la fonction init() du mod�le au
		// clic
		this.bInit = new JButton("Initialiser");
		this.bInit.setPreferredSize(new Dimension(140, 25));
		this.bInit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				leModele.init();
			}
		});
		// Bouton au hasard, qui d�clenche la fonction random() du mod�le au
		// clic
		this.bRandom = new JButton("Au hasard");
		this.bRandom.setPreferredSize(new Dimension(140, 25));
		this.bRandom.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				leModele.random();
			}
		});
		// Ajout des boutons dans le conteneur de la region EAST
		this.pDroite.add(this.bDemarrer);
		this.pDroite.add(this.bPause);
		this.pDroite.add(this.bRandom);
		this.pDroite.add(this.bInit);
		// Ajout de la grille
		this.pCentre = new JPanel();
		this.pCentre.setLayout(new GridLayout(uneGrilleDimension,
				uneGrilleDimension));
		this.pPrincipal.add(this.pCentre, BorderLayout.CENTER);
		// Remplissage de la grille, sauvegarde des r�f�rences cases cr�ent dans
		// lCases
		this.lCasesVues = new CaseVue[uneGrilleDimension][uneGrilleDimension];
		for (int caseX = 0; caseX < uneGrilleDimension; caseX++) {
			for (int caseY = 0; caseY < uneGrilleDimension; caseY++) {
				lCasesVues[caseX][caseY] = new CaseVue(caseX, caseY);
				// On transmet au mod�le l'�v�nement "clic sur la case" pour
				// qu'il mette � jour les donn�es
				lCasesVues[caseX][caseY].addMouseListener(new MouseAdapter() {
					//Si la souris rentrer dans la zone de la case
					public void mouseEntered(MouseEvent event) {
						//Si le bouton gauche de la souris est press�
						if (isMouseDown) {
							CaseVue laCaseVueClic = (CaseVue) event.getSource();
							leModele.updateCase(laCaseVueClic.getPosX(),
									laCaseVueClic.getPosY());
						}
					}
					//Si le bouton gauche de la souris est press� sur la case
					public void mousePressed(MouseEvent event){
						isMouseDown = true;
						CaseVue laCaseVueClic = (CaseVue) event.getSource();
						leModele.updateCase(laCaseVueClic.getPosX(),
								laCaseVueClic.getPosY());
					}
					//Si le bouton gauche de la souris est rel�ch� sur la case
					public void mouseReleased(MouseEvent event){
						isMouseDown = false;
					}
				});
				// On ajoute la CaseVue dans le GridLayout pCentre
				this.pCentre.add(lCasesVues[caseX][caseY]);
			}
		}
	}

	/**
	 * Mise � jour de la grille selon le mod�le
	 */
	@Override
	public void update(Observable Modele, Object arg1) {
		CaseModele[][] grille = (CaseModele[][]) arg1;
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
