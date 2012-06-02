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

		for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
			for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
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
		for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
			for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
				CaseModele c = lCases[caseX][caseY];
				c.calculeNextEtat();
			}
		}

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

		for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
			for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
				c = lCases[caseX][caseY];
				lCasesAdjacentes = new CaseModele[8];
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

	public void init() {
		for (int caseX = 0; caseX < Constantes.DIMENSION_GRILLE; caseX++) {
			for (int caseY = 0; caseY < Constantes.DIMENSION_GRILLE; caseY++) {
				lCases[caseX][caseY].setAllumee(false);
			}
		}
		notifyVue();
	}

	
	public void random(){
		premierTour = true;
	}
	public void play() {
		enMarche = true;
	}

	public void pause() {
		enMarche = false;
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
