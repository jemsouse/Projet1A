package ConwayGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	private JButton bInit;
	private JButton bDemarrer;
	private JButton bPause;
	private JButton bRandom;
	private JButton bSpeedUp, bSpeedDown;
	private JButton bPhasesVie;
	private boolean isMouseDown = false;
	private boolean phaseVie;

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

		// Création des boutons
		creationBoutons(this.pDroite);

		// Ajout de la grille
		this.pCentre = new JPanel();
		this.pCentre.setLayout(new GridLayout(uneGrilleDimension,
				uneGrilleDimension));
		this.pPrincipal.add(this.pCentre, BorderLayout.CENTER);
		// Remplissage de la grille, sauvegarde des références cases créent dans
		// lCases
		this.lCasesVues = new CaseVue[uneGrilleDimension][uneGrilleDimension];
		for (int caseX = 0; caseX < uneGrilleDimension; caseX++) {
			for (int caseY = 0; caseY < uneGrilleDimension; caseY++) {
				lCasesVues[caseX][caseY] = new CaseVue(caseX, caseY);
				// On transmet au modèle l'évènement "clic sur la case" pour
				// qu'il mette à jour les données
				lCasesVues[caseX][caseY].addMouseListener(new MouseAdapter() {
					// Si la souris rentrer dans la zone de la case
					public void mouseEntered(MouseEvent event) {
						// Si le bouton gauche de la souris est pressé
						if (isMouseDown) {
							CaseVue laCaseVueClic = (CaseVue) event.getSource();
							leModele.updateCase(laCaseVueClic.getPosX(),
									laCaseVueClic.getPosY());
						}
					}

					// Si le bouton gauche de la souris est pressé sur la case
					public void mousePressed(MouseEvent event) {
						isMouseDown = true;
						CaseVue laCaseVueClic = (CaseVue) event.getSource();
						leModele.updateCase(laCaseVueClic.getPosX(),
								laCaseVueClic.getPosY());
					}

					// Si le bouton gauche de la souris est relâché sur la case
					public void mouseReleased(MouseEvent event) {
						isMouseDown = false;
					}
				});
				// On ajoute la CaseVue dans le GridLayout pCentre
				this.pCentre.add(lCasesVues[caseX][caseY]);
			}
		}

		phaseVie = false;
	}

	// Méthode de création des différents boutons.
	private void creationBoutons(JPanel conteneur) {
		// Bouton démarrer, qui déclenche la fonction play() du modèle au clic
		this.bDemarrer = new JButton("Démarrer");
		this.bDemarrer.setPreferredSize(new Dimension(140, 25));
		this.bDemarrer.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				leModele.play();
			}
		});
		// Bouton pause, qui déclenche la fonction pause() du modèle au clic
		this.bPause = new JButton("Pause");
		this.bPause.setPreferredSize(new Dimension(140, 25));
		this.bPause.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				leModele.pause();
			}
		});
		// Bouton initialiser, qui déclenche la fonction init() du modèle au
		// clic
		this.bInit = new JButton("Initialiser");
		this.bInit.setPreferredSize(new Dimension(140, 25));
		this.bInit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				leModele.init();
				phaseVie = false;
			}
		});
		// Bouton au hasard, qui déclenche la fonction random() du modèle au
		// clic
		this.bRandom = new JButton("Au hasard");
		this.bRandom.setPreferredSize(new Dimension(140, 25));
		this.bRandom.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				leModele.random();
				phaseVie = false;
			}
		});

		// Bouton Vitesse +, diminue le temps entre deux générations.
		this.bSpeedUp = new JButton("Vitesse +");
		this.bSpeedUp.setPreferredSize(new Dimension(140, 25));
		this.bSpeedUp.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				leModele.augmenterVitesse();
			}
		});

		// Bouton Vitesse -, diminue le temps entre deux générations.
		this.bSpeedDown = new JButton("Vitesse -");
		this.bSpeedDown.setPreferredSize(new Dimension(140, 25));
		this.bSpeedDown.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				leModele.diminuerVitesse();
			}
		});

		// Bouton Vitesse -, diminue le temps entre deux générations.
		this.bPhasesVie = new JButton("Phase de la vie");
		this.bPhasesVie.setPreferredSize(new Dimension(140, 25));
		this.bPhasesVie.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				phaseVie = !phaseVie;
			}
		});

		// Ajout des boutons dans le conteneur de la region EAST
		conteneur.add(this.bDemarrer);
		conteneur.add(this.bPause);
		conteneur.add(this.bRandom);
		conteneur.add(this.bInit);
		conteneur.add(this.bSpeedUp);
		conteneur.add(this.bSpeedDown);
		conteneur.add(this.bPhasesVie);
	}

	private Color donneCouleur(String couleur) {
		Color c = null;
		if (Constantes.COULEUR_MORTE.equals(couleur)) {
			c = Color.white;
		} else if (Constantes.COULEUR_VIVANTE.equals(couleur)) {
			c = Color.blue;
		} else if (Constantes.COULEUR_NAISSANTE.equals(couleur)) {
			c = Color.green;
		} else if (Constantes.COULEUR_MOURANTE.equals(couleur)) {
			c = Color.red;
		} else {
			c = Color.black;
		}
		return c;
	}

	/**
	 * Mise à jour de la grille selon le modèle
	 */
	@Override
	public void update(Observable Modele, Object arg1) {
		CaseModele[][] grille = (CaseModele[][]) arg1;
		for (int caseX = 0; caseX < cDimension; caseX++) {
			for (int caseY = 0; caseY < cDimension; caseY++) {
				if (!phaseVie) {
					if ((grille[caseX][caseY]).isAllumee()) {
						lCasesVues[caseX][caseY].setBackground(Color.blue);
					} else {
						lCasesVues[caseX][caseY].setBackground(Color.white);
					}
				} else {
					lCasesVues[caseX][caseY]
							.setBackground(donneCouleur((grille[caseX][caseY])
									.getCouleur()));
				}
			}
		}
	}
}
