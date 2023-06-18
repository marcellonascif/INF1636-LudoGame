package model;
import java.awt.Color;

import controller.TabuleiroObservador;
public class ModelFacade{
	private static ModelFacade facade = null;
	private Game game;
	
	private ModelFacade() {
		ModelFacade.facade = this;
		this.game = new Game();
	}

	public static ModelFacade getFacade() {
		if (facade == null) {
			facade = new ModelFacade();
		}
		return facade;
	}

	// public void createGame(){
	// 	this.game = new Game();
	// }

	public void roll(){
		game.roll();
		
	}
	public void play(){
		game.play();
	}

	public void setResultado(int n){
		game.setResultado(n);
	}

	public int getResultado(){
		return this.game.getResultado();
	}
	public Color getCor(){
		return game.getCurrentColor();
	}
	
	public void addObserver(TabuleiroObservador observer) {
		game.addObserver(observer);
	}
	
	public int[][] getPosicaoPecas(){
		return game.getPosicaoPecas();
    }
	public void setIdxPecaMover(int idx){
		game.setIdxPecaMover(idx);
	}
	public int getIdxFromMouse(int idx){
		return game.getIdxFromMouse(idx);
	}
	public boolean getcanRollAgain(){
		return game.getcanRollAgain();
	}
	public void setcanRollAgain(boolean b){
		game.setcanRollAgain(b);
	}
	public void setValorDado(String Num){
		game.getDado().setResultadoManual(Num);
	}
}
