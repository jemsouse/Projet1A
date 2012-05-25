import java.util.List;

public class Case {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Position de la case sur la grille
	private int posX;
	private int posY;

	// Case est allumée (true) ou éteinte (false)
	private boolean allumee;

	/*
	 * Liste des cases adjacentes Numéro des cases dans la liste : 0 1 2 3 X 4 5
	 * 6 7
	 */
	private List<Case> lCasesAdjacentes;

	// Constructeurs
	public Case(int X, int Y) {
		posX = X;
		posY = Y;
	}

	public Case(int X, int Y, boolean onOff) {
		this(X, Y);
		allumee = onOff;
	}

}
