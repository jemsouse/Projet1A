package ConwayGame;
import java.util.*;

public class Modele extends Observable implements Runnable {
	private boolean enMarche;

	private boolean premierTour;

	private Case[][] lCases;

	private boolean[][] grille;

	// Constructeurs
	public Modele(Observer unObserver) {
		addObserver(unObserver);
		lCases = new Case[Constantes.DIMENSION_GRILLE][Constantes.DIMENSION_GRILLE];

		for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
			for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
				lCases[caseX][caseY] = new Case(caseX, caseY, false);
			}
		}

		donneCasesAdjacentes();

		premierTour = true;
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
	public Case[][] getCases() {
		return lCases;
	}

	/**
	 * @param lCases
	 *            the lCases to set
	 */
	public void setCases(Case[][] lCases) {
		this.lCases = lCases;
	}

	// Calcule une nouvelle génération aléatoirement
	private void newRandomGeneration() {
		Random rand = new Random();

		for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
			for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
				Case c = lCases[caseX][caseY];

				c.setAllumee(rand.nextBoolean());
			}
		}
		premierTour = false;
	}

	// Calcule une nouvelle génération
	private void newGeneration() {
		// donneCasesAdjacentes();
		for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
			for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
				Case c = lCases[caseX][caseY];
				c.calculeNbCasesAdjAllumee();

				if (c.getNbCasesAdjacentesAllumees() == 0
						|| c.getNbCasesAdjacentesAllumees() == 1) {
					c.setAllumee(false);
				} else if (c.getNbCasesAdjacentesAllumees() > 3) {
					c.setAllumee(false);
				} else if (c.getNbCasesAdjacentesAllumees() == 3) {
					c.setAllumee(true);
				}
			}
		}
	}

	private void donneCasesAdjacentes() {
		Case[] lCasesAdjacentes = new Case[8];
		Case c = new Case(-1, -1);

		for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
			for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
				c = lCases[caseX][caseY];
				lCasesAdjacentes = new Case[8];
				if (caseX == 0) {
					if (caseY == 0) {
						lCasesAdjacentes[4] = lCases[caseX + 1][caseY];
						lCasesAdjacentes[6] = lCases[caseX][caseY + 1];
						lCasesAdjacentes[7] = lCases[caseX + 1][caseY + 1];
					} else if (caseY == Constantes.DIMENSION_GRILLE - 1) {
						lCasesAdjacentes[1] = lCases[caseX][caseY - 1];
						lCasesAdjacentes[2] = lCases[caseX + 1][caseY - 1];
						lCasesAdjacentes[4] = lCases[caseX + 1][caseY];
					} else {
						lCasesAdjacentes[1] = lCases[caseX][caseY - 1];
						lCasesAdjacentes[2] = lCases[caseX + 1][caseY - 1];
						lCasesAdjacentes[4] = lCases[caseX + 1][caseY];
						lCasesAdjacentes[6] = lCases[caseX][caseY + 1];
						lCasesAdjacentes[7] = lCases[caseX + 1][caseY + 1];
					}
				} else if (caseX == Constantes.DIMENSION_GRILLE - 1) {
					if (caseY == 0) {
						lCasesAdjacentes[3] = lCases[caseX - 1][caseY];
						lCasesAdjacentes[5] = lCases[caseX - 1][caseY];
						lCasesAdjacentes[6] = lCases[caseX][caseY + 1];
					} else if (caseY == Constantes.DIMENSION_GRILLE - 1) {
						lCasesAdjacentes[0] = lCases[caseX - 1][caseY - 1];
						lCasesAdjacentes[1] = lCases[caseX][caseY - 1];
						lCasesAdjacentes[3] = lCases[caseX - 1][caseY];
					} else {
						lCasesAdjacentes[0] = lCases[caseX - 1][caseY - 1];
						lCasesAdjacentes[1] = lCases[caseX][caseY - 1];
						lCasesAdjacentes[3] = lCases[caseX - 1][caseY];
						lCasesAdjacentes[5] = lCases[caseX - 1][caseY];
						lCasesAdjacentes[6] = lCases[caseX][caseY + 1];
					}
				} else {
					if (caseY == 0) {
						lCasesAdjacentes[3] = lCases[caseX - 1][caseY];
						lCasesAdjacentes[4] = lCases[caseX + 1][caseY];
						lCasesAdjacentes[5] = lCases[caseX - 1][caseY];
						lCasesAdjacentes[6] = lCases[caseX][caseY + 1];
						lCasesAdjacentes[7] = lCases[caseX + 1][caseY + 1];
					} else if (caseY == Constantes.DIMENSION_GRILLE - 1) {
						lCasesAdjacentes[0] = lCases[caseX - 1][caseY - 1];
						lCasesAdjacentes[1] = lCases[caseX][caseY - 1];
						lCasesAdjacentes[2] = lCases[caseX + 1][caseY - 1];
						lCasesAdjacentes[3] = lCases[caseX - 1][caseY];
						lCasesAdjacentes[4] = lCases[caseX + 1][caseY];
					} else {
						lCasesAdjacentes[0] = lCases[caseX - 1][caseY - 1];
						lCasesAdjacentes[1] = lCases[caseX][caseY - 1];
						lCasesAdjacentes[2] = lCases[caseX + 1][caseY - 1];
						lCasesAdjacentes[3] = lCases[caseX - 1][caseY];
						lCasesAdjacentes[4] = lCases[caseX + 1][caseY];
						lCasesAdjacentes[5] = lCases[caseX - 1][caseY];
						lCasesAdjacentes[6] = lCases[caseX][caseY + 1];
						lCasesAdjacentes[7] = lCases[caseX + 1][caseY + 1];
					}
				}
				c.setlCasesAdjacentes(lCasesAdjacentes);
			}
		}
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
					setChanged();
					notifyObservers(lCases);
				}
				try {
					wait(1000);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				} catch (IllegalMonitorStateException e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}

}
