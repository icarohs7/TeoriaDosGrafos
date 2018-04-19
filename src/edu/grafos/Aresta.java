package edu.grafos;

/**
 * Classe representando a aresta de um grafo
 */
public class Aresta {
	private final int origem;
	private final int destino;
	private final int peso;
	
	Aresta( int origem, int destino, int peso ) {
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}
	
	public int getOrigem() {
		return origem;
	}
	
	public int getDestino() {
		return destino;
	}
	
	public int getPeso() {
		return peso;
	}
}
