package edu.grafos;

/**
 * Classe representando um grafo ponderado e suas operações
 *
 * @author Icaro D. Temponi
 */
public class GrafoPonderado extends Grafo {
	/**
	 * Construir o grafo ponderado
	 *
	 * @param matrizDeAdjacencia Matriz que gerará o grafo
	 * @param direcionado        Se o grafo é ou não direcionado
	 */
	public GrafoPonderado( int[][] matrizDeAdjacencia, boolean direcionado ) {
		super( matrizDeAdjacencia, direcionado );
	}
	
	public GrafoPonderado( int[][] matrizDeAdjacencia ) {
		super( matrizDeAdjacencia, false );
	}
	
	/**
	 * Executar a busca de menor caminho em um grafo ponderado partindo de um vértice origem em direção a um vértice destino
	 *
	 * @param origem  O vértice de origem
	 * @param destino O vértice que se deseja alcançar apartir da origem
	 *
	 * @return A tabela de menores caminhos do grafo de acordo com o algoritmo de dijkstra
	 */
	protected int[] dijkstra( int origem, int destino ) {
		return null;
	}
}
