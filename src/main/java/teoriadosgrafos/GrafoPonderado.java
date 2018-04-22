package teoriadosgrafos;

import teoriadosgrafos.arvoregeradora.Kruskal;
import teoriadosgrafos.arvoregeradora.MST;
import teoriadosgrafos.arvoregeradora.Prim;
import teoriadosgrafos.excecoes.ForaDoGrafoException;
import teoriadosgrafos.metodosdebusca.ponderado.BellmanFord;
import teoriadosgrafos.metodosdebusca.ponderado.Buscavel;
import teoriadosgrafos.metodosdebusca.ponderado.Dijkstra;
import teoriadosgrafos.metodosdebusca.ponderado.FloydWarshall;
import teoriadosgrafos.metodosdebusca.ponderado.ResultadoPonderado;
import teoriadosgrafos.metodosdebusca.ponderado.ResultadoWarshall;

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
	 * Construir um grafo ponderado vazio
	 *
	 * @param tamanho     A quantidade de vértices contidas no grafo
	 * @param direcionado Se o grafo é ou não direcionado
	 */
	public GrafoPonderado( int tamanho, boolean direcionado ) {
		this( new int[tamanho][tamanho], direcionado );
		resetMatrizDeAdjacencia();
	}
	
	/**
	 * Reset matriz de adjacencia.
	 */
	public void resetMatrizDeAdjacencia() {
		for ( int i = 0; i < matrizDeAdjacencia.length; i++ ) {
			for ( int j = 0; j < matrizDeAdjacencia[0].length; j++ ) {
				/* Definir todos os pesos da matriz de adjacência para infinitos */
				matrizDeAdjacencia[i][j] = INFINITO;
			}
		}
	}
	
	/**
	 * Realiza a busca em um grafo ponderado
	 *
	 * @param origem O vértice de origem
	 * @param metodo O método de busca utilizado
	 *
	 * @return Retorna um conjunto resultado referente ao método de busca utilizado que deve ser 		convertido através de
	 * 		typecasting antes de ser utilizado 		<br/> 		Tipos: 		<br/> 		Dijkstra utiliza a classe
	 * 		<strong>ResutadoDijkstra</strong>
	 */
	public Buscavel buscar( int origem, MetodoDeBusca metodo ) {
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
	 *
	 * @param valor the valor
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
	 * Adicionar uma aresta ao grafo
	 *
	 * @param origem  Vértice de origem da aresta
	 * @param destino Vértice de destino da aresta
	 * @param peso    Peso da aresta
	 *
	 * @throws ForaDoGrafoException the fora do grafo exception
	 */
	public void addAresta( int origem, int destino, int peso ) throws ForaDoGrafoException {
		addAresta( new Aresta( origem - 1, destino - 1, peso ) );
	}
	
	/**
	 * Adicionar uma aresta ao grafo
	 *
	 * @param aresta A aresta a ser adicionadad
	 *
	 * @throws ForaDoGrafoException the fora do grafo exception
	 */
	public void addAresta( Aresta aresta ) {
		int u = aresta.getOrigem();
		int v = aresta.getDestino();
		
		try {
			matrizDeAdjacencia[u][v] = aresta.getPeso();
			if ( !direcionado ) {
				matrizDeAdjacencia[v][u] = aresta.getPeso();
			}
		} catch ( IndexOutOfBoundsException e ) {
			throw new ForaDoGrafoException( "Tentou acessar uma aresta fora do grafo" );
		}
	}
	
	/**
	 * Gerar mst mst.
	 *
	 * @param metodo the metodo
	 *
	 * @return the mst
	 */
	public MST gerarMST( MetodoGeradorDeArvore metodo ) {
		switch ( metodo ) {
			case KRUSKAL:
				return Kruskal.Companion.gerar( this );
			case PRIM:
			default:
				return Prim.Companion.gerar( this );
		}
	}
	
	/**
	 * Tipos de busca disponíveis para um grafo ponderado
	 */
	public enum MetodoDeBusca {
		/**
		 * Dijkstra metodo de busca.
		 */
		DIJKSTRA, /**
		 * Bellman ford metodo de busca.
		 */
		BELLMAN_FORD, /**
		 * Floyd warshall metodo de busca.
		 */
		FLOYD_WARSHALL
	}
	
	/**
	 * The enum Metodo gerador de arvore.
	 */
	public enum MetodoGeradorDeArvore {
		/**
		 * Kruskal metodo gerador de arvore.
		 */
		KRUSKAL, /**
		 * Prim metodo gerador de arvore.
		 */
		PRIM
	}
}
