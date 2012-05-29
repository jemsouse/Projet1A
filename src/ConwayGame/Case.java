package ConwayGame;
public class Case {

	private static final long serialVersionUID = 1L;

	// Position de la case sur la grille
	private int posX;
	private int posY;

	// Case est allumée (true) ou éteinte (false)
	private boolean allumee;

	private int nbCasesAdjacentesAllumees;

	/*
	 * Liste des cases adjacentes Numéro des cases dans la liste : 0 1 2 3 X 4 5
	 * 6 7
	 */
	private Case[] lCasesAdjacentes;

	// Constructeurs
	public Case(int X, int Y) {
		posX = X;
		posY = Y;
		nbCasesAdjacentesAllumees = 0;
		lCasesAdjacentes = new Case[8];
	}

	public Case(int X, int Y, boolean onOff) {
		this(X, Y);
		allumee = onOff;
	}

	// Méthodes
	public void calculeNbCasesAdjAllumee() {
		nbCasesAdjacentesAllumees = 0;
		for (int i = 0; i < 8; i++) {
			if (lCasesAdjacentes[i] != null && lCasesAdjacentes[i].allumee) {
				nbCasesAdjacentesAllumees++;
			}
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

	public Case[] getlCasesAdjacentes() {
		return lCasesAdjacentes;
	}

	public void setlCasesAdjacentes(Case[] lCasesAdjacentes) {
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

}
