package edu.grafos;

import java.util.LinkedList;

/**
 * Classe representando um grafo e suas operações
 *
 * @author Icaro D. Temponi
 */
public abstract class Grafo {
	/**
	 * Valor representanto infinito, sua função pode variar de acordo com o grafo e algoritmo usado
	 */
	public static final int INFINITO = Integer.MAX_VALUE / 2;
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
	 * 		adjacencia vértice x vértice
	 */
	public int[][] getMatrizDeAdjacencia() {
		return matrizDeAdjacencia;
	}
	
	/**
	 * Inicializar o grafo
	 */
	public void inicializar() {
		//Marcar todos os vértices como abertos ou seja, não visitados
		resetVisitados();
	}
	
	/**
	 * Desmarcar todos os vértices visitados
	 */
	public void resetVisitados() {
		visitados = new boolean[matrizDeAdjacencia.length];
	}
	
	/**
	 * Retorna a lista de arestas contidas no grafo utilizando o valor
	 * infinito para definir a não adjacência de vértices
	 *
	 * @return A lista contendo todas as arestas adjacentes no grafo
	 */
	public LinkedList<Aresta> getArestas() {
		LinkedList<Aresta> arestas = new LinkedList<>();
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			for ( int j = 0; j < matrizDeAdjacencia[0].length; j++ ) {
				if ( matrizDeAdjacencia[i][j] != INFINITO ) {
					arestas.add( new Aresta( i, j, matrizDeAdjacencia[i][j] ) );
				}
			}
		}
		return arestas;
	}
	
	/**
	 * Retorna verdadeiro se ainda existe algum elemento aberto no grafo
	 *
	 * @return True se existir algum elemento não visitado ou false do contrário
	 */
	public boolean existeAberto() {
		for ( boolean vertice : visitados ) {
			if ( !vertice ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retorna uma lista contendo os vizinhos abertos do vértice informado
	 *
	 * @param vertice       O vértice do qual se deseja obter os vizinhos
	 * @param naoAdjacencia O valor utilizado para marcar um vértice como não adjacente a outro
	 *
	 * @return Uma lista contendo os vizinhos abertos do vértice informado
	 */
	public LinkedList<Integer> getVizinhos( int vertice, int naoAdjacencia ) {
		LinkedList<Integer> vizinhos = new LinkedList<>();
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			if ( matrizDeAdjacencia[vertice][i] != naoAdjacencia ) {
				vizinhos.add( i );
			}
		}
		return vizinhos;
	}
}
