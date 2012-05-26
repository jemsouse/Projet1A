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
		// Ajout de la grille
		this.pCentre = new JPanel();
		this.pPrincipal.add(this.pCentre, BorderLayout.CENTER);
		this.pCentre.setLayout(new GridLayout(uneGrilleDimension,
				uneGrilleDimension));
		// Remplissage de la grille, sauvegarde des références cases créent dans
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
						// On transmet les informations de la case cliquée
					}
				});

				this.pCentre.add(lCases[caseY][caseX]);
			}
		}
		// Ajout d'un conteneur à droite de type FlowLayout
		this.pDroite = new JPanel(new FlowLayout());
		this.pPrincipal.add(this.pDroite, BorderLayout.EAST);
		// Ajout des boutons dans le conteneur à droite
		this.pDroite.add(new JButton("Démarrer"));
		this.pDroite.add(new JButton("Bouton 2"));
		this.pDroite.add(new JButton("Bouton 3"));
	}

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
