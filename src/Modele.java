import java.util.*;

public class Modele extends Observable implements Runnable {
	private boolean enMarche;

	private List<Case> lCases;

	// Constructeurs
	public Modele(Observer unObserver) {
		addObserver(unObserver);
		lCases = new ArrayList<Case>();
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

	// Calcul une nouvelle génération
	private void newGeneration() {

	}

	// Méthodes de Runnable
	@Override
	public void run() {
		while (true) {
			if (enMarche) {
				newGeneration();
				setChanged();
				notifyObservers();
			}
		}

	}

}
