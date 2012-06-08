package ConwayGame;

public class CaseModele {

	private static final long serialVersionUID = 1L;

	// Position de la case sur la grille
	private int posX;
	private int posY;
	private transient String couleur;

	// Case est allumée (true) ou éteinte (false).
	private boolean allumee;

	// Prochain état (allumée ou éteinte) de la case.
	private transient boolean nextEtat;

	// Variable conservant le nombre de cases vivantes dans l'entourage de la case courante.
	private transient int nbCasesAdjacentesAllumees;

	/*
	 * Liste des cases adjacentes Numéro des cases dans la liste : 0 1 2 3 X 4 5
	 * 6 7
	 */
	// transient permet que l'attribut n'est pas sérialisé.
	private transient CaseModele[] lCasesAdjacentes;

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

	// Méthodes
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
	 *  Calcule le prochain état de la case et défini si la case doit vivre ou mourir.
	 *  C'est en quelque sorte là où les règles sont définies.
	 */
	public void calculeNextEtat() {
		calculeNbCasesAdjAllumee();
		if (allumee
				&& (nbCasesAdjacentesAllumees < 2 || nbCasesAdjacentesAllumees > 3)) {
			// Mourante
			nextEtat = false;
			couleur = Constantes.COULEUR_MOURANTE;
		} else if (allumee
				&& (nbCasesAdjacentesAllumees == 2 || nbCasesAdjacentesAllumees == 3)) {
			// Reste en vie
			nextEtat = true;
			couleur = Constantes.COULEUR_VIVANTE;
		} else if (!allumee && nbCasesAdjacentesAllumees == 3) {
			// Naissante
			nextEtat = true;
			couleur = Constantes.COULEUR_NAISSANTE;
		} else {
			// Morte
			nextEtat = false;
			couleur = Constantes.COULEUR_MORTE;
		}
	}

	// Getters et Setters
	/**
	 * @return allumee
	 */
	public boolean isAllumee() {
		return allumee;
	}

	public CaseModele() {
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

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	@Override
	public String toString() {
		return ("[" + posX + "," + posY + "," + allumee + "," + nextEtat + "]");
	}

}
