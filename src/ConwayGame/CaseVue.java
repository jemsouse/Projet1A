package ConwayGame;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class CaseVue extends JPanel{
	/**
	 * Attributs
	 */
	private int posX;
	private int posY;

	/**
	 * Constructeur
	 */
	public CaseVue(int unePosX,int unePosY){
		super();
		this.posX = unePosX;
		this.posY = unePosY;
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Accesseurs
	 */
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
