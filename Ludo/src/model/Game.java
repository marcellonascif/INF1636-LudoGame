package model;
import java.awt.Color;

import controller.TabuleiroObservado;
class Game extends TabuleiroObservado {
	private Tabuleiro tabuleiro;
	private Dado dado;
	private Jogador jogadores[];
	private Jogador jVerde;
	private Jogador jAmarelo;
	private Jogador jAzul;
	private Jogador jVermelho;

	//private boolean podeJogar;
	private int turn;
	private Jogador currentP;
	private int idxPecaMover;

	private boolean win;
	private Jogador winner;
	

	public Game(){
		this.tabuleiro = new Tabuleiro();
		this.dado = new Dado();
		this.jogadores = new Jogador[4];

		jVerde = new Jogador(tabuleiro, Cor.VERDE);
		jAmarelo = new Jogador(tabuleiro, Cor.AMARELO);
		jAzul = new Jogador(tabuleiro, Cor.AZUL);
		jVermelho = new Jogador(tabuleiro, Cor.VERMELHO);

		jogadores[0] = jVerde;
		jogadores[1] = jAmarelo;
		jogadores[2] = jAzul;
		jogadores[3] = jVermelho;

		turn = 0;
		currentP = jogadores[turn];

		win = false;
	}

	public void roll(){
		this.dado.rolar();
		notifyObservers();
	}
	
	public void setResultado(int n){
		this.dado.setResultado(n);
	}

	public int getResultado(){
		return this.dado.getResultado();
	}

	public boolean checkMove(){

		if (currentP.podeJogar(tabuleiro, this.getResultado())){
			System.out.println("pode mover");
			
			return true;
		}

		System.out.println("nao pode mover");

		currentP.setSeis(0);
		turn = (turn + 1) % 4;

		return false;
	}

	public boolean move() {

		if (checkMove()){

			System.out.println("index da peca escolhida: " + idxPecaMover);

			if (idxPecaMover == -100){
				return false;
			}

			System.out.println("move peça");
			currentP.moverPeca(tabuleiro, idxPecaMover, this.getResultado());

			if (this.getResultado() == 6){
				currentP.addSeis();
			}
			
			else{
				currentP.setSeis(0);
				turn = (turn + 1) % 4;
			}
			
			return true;
		}

		System.out.println("nao move peça");

		return false;
	}

	public void play(){
		
		System.out.println(currentP.getCor());
		System.out.println("dado: " + this.getResultado());
		// System.out.println("Pecas disponives: " + currentP.getPecasDisponiveis());
		// System.out.println("Posicao peça 0 antes de mover: " + currentP.getLastPeca().getPosition());
		// System.out.println("Tirou 6: " + currentP.getSeis());


		if (move()){
			if (currentP.isWinner()){
					winner = currentP;
					win = true;
					System.out.println(currentP.getCor() + " ganhou");
			}

			notifyObservers();
		}
		
		// System.out.println("Posicao peça depois de mover: " + posPecaMover + "\n");
		currentP = jogadores[turn];
	}

	public int[][] getEveryPosition(){
		int p[][] = new int[4][4];
		for(int i = 0; i < 4; i++){
			
			p[i] = jogadores[i].getPosicoes();
		}

		return p;
	}

	public void setPosPecaMover(int pos){
		this.idxPecaMover = pos;
	}

	public int getPecaFromMouse(int n){ // Se olhar pra posicao de cada peca disponivel e uma delas fizer parte, retorna a peca
		if (n >= -1 && n <= 105){
			for (int i = 0; i < currentP.getPecasDisponiveis().size(); i++){
				Peca pc = currentP.getPecaDisponivel(i);
				if (pc.getPosition() == n){
					// play();
					return i;
				}
			}
		}
		return -100;
	}

	public Color getCurrentColor(){ 
		switch(currentP.getCor()){
			case VERDE:
				return Color.GREEN;
			case AMARELO:
				return Color.YELLOW;
			case AZUL:
				return Color.BLUE;
			case VERMELHO:
				return Color.RED;
		}
		return Color.WHITE;//Caso erro

	}

	public boolean getWin(){
		return this.win;
	}
}