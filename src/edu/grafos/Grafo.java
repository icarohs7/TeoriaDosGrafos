package edu.grafos;

/**
 * Classe representando um grafo e suas operações
 *
 * @author Icaro D. Temponi
 */
public abstract class Grafo {
	/**
	 * Tabela de vértices que foram visitados, ou vértices abertos
	 */
	public boolean[] visitados;
	
	/**
	 * Matriz de adjacência utilizada para construir o grafo
	 */
	int[][] matrizDeAdjacencia;
	
	/**
	 * Se o grafo é ou não direcionado
	 */
	boolean direcionado;
	
	
	/**
	 * Modelo padrão para construção de um grafo
	 *
	 * @param matrizDeAdjacencia Matriz que compõe o grafo
	 * @param direcionado        Se o grafo é ou não direcionado
	 */
	protected Grafo( int[][] matrizDeAdjacencia, boolean direcionado ) {
		this.matrizDeAdjacencia = matrizDeAdjacencia;
		this.direcionado = direcionado;
	}
	
	
	/**
	 * Retorna a matriz de adjacência do grafo instanciado
	 *
	 * @return um array bidimensional de valores inteiros referentes à
	 * adjacencia vértice x vértice
	 */
	public int[][] getMatrizDeAdjacencia() {
		return matrizDeAdjacencia;
	}
	
	/**
	 * Desmarcar todos os vértices visitados
	 */
	public void resetVisitados() {
		visitados = new boolean[matrizDeAdjacencia.length];
	}
}
