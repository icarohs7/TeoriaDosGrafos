package edu.grafos;

import edu.grafos.metodosdebusca.ponderado.BellmanFord;
import edu.grafos.metodosdebusca.ponderado.Dijkstra;
import edu.grafos.metodosdebusca.ponderado.FloydWarshall;
import edu.grafos.metodosdebusca.ponderado.ResultadoPonderado;
import edu.grafos.metodosdebusca.ponderado.ResultadoWarshall;

/**
 * Classe representando um grafo ponderado e suas operações
 *
 * @author Icaro D. Temponi
 */
public class GrafoPonderado extends Grafo {
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
	 * @param origem O vértice de origem
	 * @param metodo O método de busca utilizado
	 *
	 * @return Retorna um conjunto resultado referente ao método de busca utilizado que deve ser
	 * 		convertido através de typecasting antes de ser utilizado
	 * 		<br/>
	 * 		Tipos:
	 * 		<br/>
	 * 		Dijkstra utiliza a classe <strong>ResutadoDijkstra</strong>
	 */
	public Object buscar( int origem, MetodoDeBusca metodo ) {
		origem--;
		switch ( metodo ) {
			case DIJKSTRA:
				return dijkstra( origem );
			case BELLMAN_FORD:
				return bellmanford( origem );
			case FLOYD_WARSHALL:
				return floydwarshall();
			default:
				return null;
		}
	}
	
	/**
	 * Executar a busca de menor caminho em um grafo ponderado utilizando o algoritmo de Dijkstra
	 *
	 * @param origem O vértice de origem
	 *
	 * @return O objeto resultado contendo a tabela de menores distâncias e predecessores
	 */
	private ResultadoPonderado dijkstra( int origem ) {
		return Dijkstra.Companion.buscar( origem, this );
	}
	
	/**
	 * Executar a busca de menor caminho em um grafo ponderado utilizando o algoritmo de Bellman-Ford
	 *
	 * @param origem O vértice de origem
	 *
	 * @return O objeto resultado contendo a tabela de menores distâncias e predecessores
	 */
	private ResultadoPonderado bellmanford( int origem ) {
		return BellmanFord.Companion.buscar( origem, this );
	}
	
	/**
	 * Executar a busca de menor caminho em um grafo ponderado utilizando o algoritmo de Floyd-Warshall
	 *
	 * @return O objeto resultado contendo a tabela de menores distâncias e predecessores
	 */
	private ResultadoWarshall floydwarshall() {
		return FloydWarshall.Companion.buscar( this );
	}
	
	/**
	 * Inicializar o grafo
	 */
	@Override
	public void inicializar() {
		//Inicializar todas as distâncias como infinitas, com exceção da distância s até s, que será 0
		resetDistancias( INFINITO );
		
		//Inicializar todos os precedentes como -1 para representar sem precedente
		resetPrecedentes( -1 );
		
		//Marcar todos os vértices como abertos ou seja, não visitados
		resetVisitados();
	}
	
	/**
	 * Inicializa ou Reinicializa o array de distâncias
	 */
	private void resetDistancias( int valor ) {
		for ( int i = 0; i < distancia.length; i++ ) {
			distancia[i] = valor;
		}
	}
	
	/**
	 * Inicializa ou Reinicializa o array de predecessores com o valor desejado
	 *
	 * @param valor O valor a ser atribuído a cada elemento do array
	 */
	private void resetPrecedentes( int valor ) {
		for ( int i = 0; i < precedentes.length; i++ ) {
			precedentes[i] = valor;
		}
	}
	
	/**
	 * Retorna o vértice contendo a menor distância até uma dada origem
	 *
	 * @param origem A origem
	 *
	 * @return O índice do vértice aberto mais próximo à origem ou infinito caso não haja vértice aberto
	 */
	public int menorDistancia( int origem ) {
		int menor = INFINITO;
		
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			if ( !visitados[i] ) {
				if ( distancia[i] < menor ) {
					menor = i;
				}
			}
		}
		
		return menor;
	}
	
	/**
	 * Tipos de busca disponíveis para um grafo ponderado
	 */
	public enum MetodoDeBusca {
		DIJKSTRA, BELLMAN_FORD, FLOYD_WARSHALL
	}
}
