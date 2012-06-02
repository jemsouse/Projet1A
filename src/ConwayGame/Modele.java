package ConwayGame;

import java.util.*;

public class Modele extends Observable implements Runnable {
	private boolean enMarche;

	private boolean premierTour;

	private CaseModele[][] lCases;

	private int waitingTime;

	// Constructeurs
	public Modele(Observer unObserver) {
		addObserver(unObserver);
		lCases = new CaseModele[Constantes.DIMENSION_GRILLE][Constantes.DIMENSION_GRILLE];

		for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
			for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
				lCases[caseX][caseY] = new CaseModele(caseX, caseY, false);
			}
		}

		donneCasesAdjacentes();

		premierTour = true;

		waitingTime = 1000;
	}

	/**
	 * @return the enMarche
	 */
	public boolean isEnMarche() {
		return enMarche;
	}

	/**
	 * @param enMarche
	 *            the enMarche to set
	 */
	public void setEnMarche(boolean enMarche) {
		this.enMarche = enMarche;
	}

	/**
	 * @return the lCases
	 */
	public CaseModele[][] getCases() {
		return lCases;
	}

	/**
	 * @param lCases
	 *            the lCases to set
	 */
	public void setCases(CaseModele[][] lCases) {
		this.lCases = lCases;
	}

	/**
	 * @return the waitingTime
	 */
	public int getWaitingTime() {
		return waitingTime;
	}

	/**
	 * @param waitingTime
	 *            the waitingTime to set
	 */
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	// Calcule une nouvelle génération aléatoirement
	private void newRandomGeneration() {
		Random rand = new Random();

		for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
			for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
				CaseModele c = lCases[caseX][caseY];

				c.setAllumee(rand.nextBoolean());
			}
		}
		premierTour = false;
	}

	// Calcule une nouvelle génération
	private void newGeneration() {
		// Pour chaque case, on va calculer l'état à la prochaine génération.
		for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
			for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
				CaseModele c = lCases[caseX][caseY];
				c.calculeNextEtat();
			}
		}

		// Une fois la grille calculée intégralement, on la valide en passant le
		// prochain état (nextEtat) dans l'état courant.
		for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
			for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
				CaseModele c = lCases[caseX][caseY];
				c.setAllumee(c.isNextEtat());
			}
		}
	}

	private void donneCasesAdjacentes() {
		CaseModele[] lCasesAdjacentes = new CaseModele[8];
		CaseModele c = new CaseModele(-1, -1);
		int X, Y;

		for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
			for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
				c = lCases[caseX][caseY];
				X = c.getPosX();
				Y = c.getPosY();

				/**
				 * Les numéros des cases dans le tableau permet de connaitre la
				 * position relative de la case courante par rapport à ses
				 * voisines. 
				 * 0 1 2
				 * 3 C 4
				 * 5 6 7
				 * Ceci permet d'éviter les OutOfBoundsException
				 */
				lCasesAdjacentes = new CaseModele[8];
				if (X == 0) { 
					if (Y == 0) { // Coin supérieur gauche
						lCasesAdjacentes[4] = lCases[X + 1][Y];
						lCasesAdjacentes[6] = lCases[X][Y + 1];
						lCasesAdjacentes[7] = lCases[X + 1][Y + 1];
					} else if (Y == Constantes.DIMENSION_GRILLE - 1) { // Coin supérieur droit
						lCasesAdjacentes[1] = lCases[X][Y - 1];
						lCasesAdjacentes[2] = lCases[X + 1][Y - 1];
						lCasesAdjacentes[4] = lCases[X + 1][Y];
					} else { // Ligne supérieure
						lCasesAdjacentes[1] = lCases[X][Y - 1];
						lCasesAdjacentes[2] = lCases[X + 1][Y - 1];
						lCasesAdjacentes[4] = lCases[X + 1][Y];
						lCasesAdjacentes[6] = lCases[X][Y + 1];
						lCasesAdjacentes[7] = lCases[X + 1][Y + 1];
					}
				} else if (X == Constantes.DIMENSION_GRILLE - 1) { 
					if (Y == 0) { // Coin inférieur gauche
						lCasesAdjacentes[3] = lCases[X - 1][Y];
						lCasesAdjacentes[5] = lCases[X - 1][Y + 1];
						lCasesAdjacentes[6] = lCases[X][Y + 1];
					} else if (Y == Constantes.DIMENSION_GRILLE - 1) { // Coin inférieur droit
						lCasesAdjacentes[0] = lCases[X - 1][Y - 1];
						lCasesAdjacentes[1] = lCases[X][Y - 1];
						lCasesAdjacentes[3] = lCases[X - 1][Y];
					} else { // Ligne inférieure
						lCasesAdjacentes[0] = lCases[X - 1][Y - 1];
						lCasesAdjacentes[1] = lCases[X][Y - 1];
						lCasesAdjacentes[3] = lCases[X - 1][Y];
						lCasesAdjacentes[5] = lCases[X - 1][Y + 1];
						lCasesAdjacentes[6] = lCases[X][Y + 1];
					}
				} else {
					if (Y == 0) { // Colonne de gauche
						lCasesAdjacentes[3] = lCases[X - 1][Y];
						lCasesAdjacentes[4] = lCases[X + 1][Y];
						lCasesAdjacentes[5] = lCases[X - 1][Y + 1];
						lCasesAdjacentes[6] = lCases[X][Y + 1];
						lCasesAdjacentes[7] = lCases[X + 1][Y + 1];
					} else if (Y == Constantes.DIMENSION_GRILLE - 1) { // Colonne de droite
						lCasesAdjacentes[0] = lCases[X - 1][Y - 1];
						lCasesAdjacentes[1] = lCases[X][Y - 1];
						lCasesAdjacentes[2] = lCases[X + 1][Y - 1];
						lCasesAdjacentes[3] = lCases[X - 1][Y];
						lCasesAdjacentes[4] = lCases[X + 1][Y];
					} else { // "Centre" de la grille
						lCasesAdjacentes[0] = lCases[X - 1][Y - 1];
						lCasesAdjacentes[1] = lCases[X][Y - 1];
						lCasesAdjacentes[2] = lCases[X + 1][Y - 1];
						lCasesAdjacentes[3] = lCases[X - 1][Y];
						lCasesAdjacentes[4] = lCases[X + 1][Y];
						lCasesAdjacentes[5] = lCases[X - 1][Y + 1];
						lCasesAdjacentes[6] = lCases[X][Y + 1];
						lCasesAdjacentes[7] = lCases[X + 1][Y + 1];
					}
				}
				c.setlCasesAdjacentes(lCasesAdjacentes);
			}
		}
	}

	// Vide la grille de toute les cellules vivantes
	public void init() {
		for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
			for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
				lCases[caseX][caseY].setAllumee(false);
			}
		}
		notifyVue();
	}

	// Défini si la première itération doit générer une grille aléatoire
	public void random() {
		premierTour = true;
	}

	public void play() {
		enMarche = true;
	}

	public void pause() {
		enMarche = false;
	}
	
	public void augmenterVitesse() {
		waitingTime = waitingTime / 2;
	}
	
	public void diminuerVitesse() {
		waitingTime = waitingTime * 2;
	}

	private void notifyVue() {
		setChanged();
		notifyObservers(lCases);
	}

	public void updateCase(int posX, int posY) {
		lCases[posX][posY].setAllumee(!lCases[posX][posY].isAllumee());
		notifyVue();
	}

	// Méthodes de Runnable
	@Override
	public void run() {

		while (true) {
			synchronized (this) {
				if (enMarche) {
					if (premierTour) {
						newRandomGeneration();
					} else {
						newGeneration();
					}
					notifyVue();
				}
				try {
					wait(waitingTime);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				} catch (IllegalMonitorStateException e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}
}
