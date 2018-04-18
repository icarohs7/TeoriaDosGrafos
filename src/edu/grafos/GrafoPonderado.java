package edu.grafos;

import edu.grafos.metodosdebusca.ponderado.Dijkstra;
import edu.grafos.metodosdebusca.ponderado.ResultadoDijkstra;

/**
 * Classe representando um grafo ponderado e suas operações
 *
 * @author Icaro D. Temponi
 */
public class GrafoPonderado extends Grafo {
	public final int INFINITO = Integer.MAX_VALUE / 2;
	/**
	 * Tabela de distâncias que guarda a menor distância da origem ao vértice v
	 */
	public int[] distancia;
	
	/**
	 * Tabela de predecessores do vértice v, onde o valor de cada elemento p[v]
	 * é o vértice que antecede v no menor caminho de s a v
	 */
	public int[] precedentes;
	
	/**
	 * Construir o grafo ponderado utilizando o valor não direcionado como padrão
	 *
	 * @param matrizDeAdjacencia A matriz representando o grafo
	 */
	public GrafoPonderado( int[][] matrizDeAdjacencia ) {
		this( matrizDeAdjacencia, false );
	}
	
	/**
	 * Construir o grafo ponderado
	 *
	 * @param matrizDeAdjacencia Matriz que gerará o grafo
	 * @param direcionado        Se o grafo é ou não direcionado
	 */
	public GrafoPonderado( int[][] matrizDeAdjacencia, boolean direcionado ) {
		super( matrizDeAdjacencia, direcionado );
		distancia = new int[matrizDeAdjacencia.length];
		precedentes = new int[matrizDeAdjacencia.length];
	}
	
	/**
	 * Realiza a busca em um grafo ponderado
	 *
	 * @param origem  O vértice de origem
	 * @param destino O vértice que se deseja alcançar apartir da origem
	 * @param metodo  O método de busca utilizado
	 *
	 * @return Retorna um conjunto resultado referente ao método de busca utilizado que deve ser
	 * 		convertido através de typecasting antes de ser utilizado
	 * 		<br/>
	 * 		Tipos:
	 * 		<br/>
	 * 		Dijkstra utiliza a classe <strong>ResutadoDijkstra</strong>
	 */
	public Object buscar( int origem, int destino, MetodoDeBusca metodo ) {
		switch ( metodo ) {
			case DIJKSTRA:
				return dijkstra( origem );
			case BELLMAN_FORD:
				return bellmanford( origem );
			default:
				return null;
		}
	}
	
	/**
	 * Executar a busca de menor caminho em um grafo ponderado utilizando o algoritmo de Dijkstra
	 *
	 * @param origem O vértice de origem
	 *
	 * @return A tabela de menores caminhos do grafo de acordo com o algoritmo de dijkstra
	 */
	private ResultadoDijkstra dijkstra( int origem ) {
		return Dijkstra.Companion.buscar( origem, this );
	}
	
	/**
	 * Executar a busca de menor caminho em um grafo ponderado utilizando o algoritmo de Bellman-Ford
	 *
	 * @param origem
	 *
	 * @return
	 */
	private Object bellmanford( int origem ) {
		return null;
	}
	
	/**
	 * Inicializa ou Reinicializa o array de predecessores com o valor desejado
	 *
	 * @param valor O valor a ser atribuído a cada elemento do array
	 */
	public void resetPrecedentes( int valor ) {
		for ( int i = 0; i < precedentes.length; i++ ) {
			precedentes[i] = valor;
		}
	}
	
	/**
	 * Inicializa ou Reinicializa o array de distâncias
	 */
	public void resetDistancias( int valor ) {
		for ( int i = 0; i < distancia.length; i++ ) {
			distancia[i] = valor;
		}
	}
	
	/**
	 * Tipos de busca disponíveis para um grafo ponderado
	 */
	public enum MetodoDeBusca {
		DIJKSTRA, BELLMAN_FORD
	}
}
