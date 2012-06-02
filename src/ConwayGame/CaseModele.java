package ConwayGame;

public class CaseModele {

	private static final long serialVersionUID = 1L;

	// Position de la case sur la grille
	private int posX;
	private int posY;

	// Case est allum�e (true) ou �teinte (false).
	private boolean allumee;

	// Prochain �tat (allum�e ou �teinte) de la case.
	private boolean nextEtat;

	// Variable conservant le nombre de cases vivantes dans l'entourage de la case courante.
	private int nbCasesAdjacentesAllumees;

	/*
	 * Liste des cases adjacentes Num�ro des cases dans la liste : 0 1 2 3 X 4 5
	 * 6 7
	 */
	private CaseModele[] lCasesAdjacentes;

	// Constructeurs
	public CaseModele(int X, int Y) {
		posX = X;
		posY = Y;
		nbCasesAdjacentesAllumees = 0;
		lCasesAdjacentes = new CaseModele[8];
	}

	public CaseModele(int X, int Y, boolean onOff) {
		this(X, Y);
		allumee = onOff;
	}

	// M�thodes
	/**
	 * Ici, on calcule le nombre de cases vivantes dans l'entourage de la case courante.
	 */
	public void calculeNbCasesAdjAllumee() {
		nbCasesAdjacentesAllumees = 0;
		for (int i = 0; i < 8; i++) {
			if (lCasesAdjacentes[i] != null && lCasesAdjacentes[i].allumee) {
				nbCasesAdjacentesAllumees++;
			}
		}
	}

	/**
	 *  Calcule le prochain �tat de la case et d�fini si la case doit vivre ou mourir.
	 *  C'est en quelque sorte l� o� les r�gles sont d�finies.
	 */
	public void calculeNextEtat() {
		calculeNbCasesAdjAllumee();
		if (allumee
				&& (nbCasesAdjacentesAllumees < 2 || nbCasesAdjacentesAllumees > 3)) {
			nextEtat = false;
		} else if (allumee
				&& (nbCasesAdjacentesAllumees == 2 || nbCasesAdjacentesAllumees == 3)) {
			nextEtat = true;
		} else if (!allumee && nbCasesAdjacentesAllumees == 3) {
			nextEtat = true;
		} else {
			nextEtat = false;
		}
	}

	// Getters et Setters
	/**
	 * @return allumee
	 */
	public boolean isAllumee() {
		return allumee;
	}

	/**
	 * @param allumee
	 *            to set
	 */
	public void setAllumee(boolean allumee) {
		this.allumee = allumee;
	}

	/**
	 * @return the nextEtat
	 */
	public boolean isNextEtat() {
		return nextEtat;
	}

	/**
	 * @param nextEtat
	 *            the nextEtat to set
	 */
	public void setNextEtat(boolean nextEtat) {
		this.nextEtat = nextEtat;
	}

	/**
	 * @return the nbCasesAdjacentesAllumees
	 */
	public int getNbCasesAdjacentesAllumees() {
		return nbCasesAdjacentesAllumees;
	}

	/**
	 * @param nbCasesAdjacentesAllumees
	 *            the nbCasesAdjacentesAllumees to set
	 */
	public void setNbCasesAdjacentesAllumees(int nbCasesAdjacentesAllumees) {
		this.nbCasesAdjacentesAllumees = nbCasesAdjacentesAllumees;
	}

	public CaseModele[] getlCasesAdjacentes() {
		return lCasesAdjacentes;
	}

	public void setlCasesAdjacentes(CaseModele[] lCasesAdjacentes) {
		this.lCasesAdjacentes = lCasesAdjacentes;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public String toString() {
		return ("[" + posX + "," + posY + "," + allumee + "," + nextEtat + "]");
	}

}