package ConwayGame;

public class GameOfLife {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Instance de notre vue
		FenetrePrincipale laFenetre = new FenetrePrincipale(
				Constantes.DIMENSION_GRILLE);
		laFenetre.setVisible(true);

		// Instance de notre modèle
		Modele leModele = new Modele(laFenetre);
		laFenetre.setLeModele(leModele);
		leModele.setEnMarche(true);
		Thread t = new Thread(leModele);
		t.start();
	}

}
